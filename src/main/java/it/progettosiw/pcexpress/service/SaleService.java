package it.progettosiw.pcexpress.service;

import it.progettosiw.pcexpress.exceptions.*;
import it.progettosiw.pcexpress.model.*;
import it.progettosiw.pcexpress.repository.PCRepository;
import it.progettosiw.pcexpress.repository.SaleRepository;
import it.progettosiw.pcexpress.repository.SoldItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
    private final PCService pCService;

    private CartService cartService;
    private UserService userService;
    private SaleRepository saleRepository;
    private SoldItemRepository soldItemRepository;
    private PCRepository pcRepository;

    public SaleService(SaleRepository saleRepository, SoldItemRepository soldItemRepository, PCRepository pcRepository, UserService userService, CartService cartService, PCService pCService) {
        this.saleRepository = saleRepository;
        this.soldItemRepository = soldItemRepository;
        this.pcRepository = pcRepository;
        this.userService = userService;
        this.cartService = cartService;
        this.pCService = pCService;
    }

    @Transactional(readOnly = true)
    public Sale getSaleById(Long sale_id){
        return saleRepository.findById(sale_id).orElseThrow(()-> new SaleNotFoundException(sale_id));
    }

    public Boolean isCurrentUserBuyerOfSale(Sale sale){
        User user = userService.getCurrentUser();
        if(sale.getUser().equals(user))
            return true;
        return false;
    }

    @Transactional(readOnly = true)
    public List<Sale> getAllSales(){
        return saleRepository.findAllByOrderByDateOfSaleDesc();
    }

    @Transactional(readOnly = true) //perchè fa una query per prendere gli acquisti (sono lazy per l'utente)
    public List<Sale> getCurrentUserPurchases(){
        return userService.getCurrentUser().getPurchases();
    }


    private SoldItem createSoldItem(Long pc_id, Integer quantity){
        if (quantity<1)
            throw new NonPositiveQuantityException();
        PC pc = pcRepository.findById(pc_id).orElseThrow(() -> new PCDoesNotExistException(pc_id));
        if (pc.getDisponibilita()<quantity)
            throw new TooLowAvailabilityException();
        return new SoldItem(quantity, pc.getPrezzo(), pc);
    }

    private SoldItem createSoldItem(CartItem cartItem){
        Integer quantity = cartItem.getQuantity();
        if (quantity < 1)
            throw new NonPositiveQuantityException();
        if (cartItem.getPc().getDisponibilita()<quantity)
            throw new TooLowAvailabilityException();
        return new SoldItem(quantity, cartItem.getPc().getPrezzo(), cartItem.getPc());
    }

    private Sale createAndSaveSale(User user, List<SoldItem> soldItems){
        Float totalPrice = 0F;
        for (SoldItem item : soldItems){
            Integer quantity = item.getQuantity();
            totalPrice += item.getPaidMoney()*quantity;
            item.getPc().reduceAvailability(quantity);
        }
        Sale sale = new Sale(LocalDateTime.now(), totalPrice, soldItems, user);
        user.getPurchases().add(sale);  //serve a mantenere aggiornato lo user in locale
        saleRepository.save(sale);
        return sale;
    }

    //è chaiamato quando si acquista dalla pagina di un pc
    @Transactional (isolation = Isolation.SERIALIZABLE)
    public Sale createSaleFromPC(Long pc_id, Integer quantity) throws NonPositiveQuantityException, TooLowAvailabilityException, PCDoesNotExistException{
        User currentUser = userService.getCurrentUser();
        SoldItem soldItem = createSoldItem(pc_id, quantity);
        return createAndSaveSale(currentUser, List.of(soldItem));
    }

    //è chiamato quando si acquista dal carrello
    @Transactional (isolation = Isolation.SERIALIZABLE)
    public Sale createSaleFromCart() throws NonPositiveQuantityException, TooLowAvailabilityException{
        User currentUser = userService.getCurrentUser();
        Cart currentCart = currentUser.getCart();
        if (currentCart.getCartItems().isEmpty()){
            throw new EmptyCartDuringSaleCreationException();
        }
        List<SoldItem> soldItems = new ArrayList<>();

        for(CartItem cartItem : currentCart.getCartItems()){
            SoldItem soldItem = createSoldItem(cartItem);
            soldItems.add(soldItem);
        }

        Sale sale = createAndSaveSale(currentUser, soldItems);
        cartService.emptyCurrentUserCart();
        return sale;
    }

}
