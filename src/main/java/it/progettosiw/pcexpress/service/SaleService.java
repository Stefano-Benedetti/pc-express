package it.progettosiw.pcexpress.service;

import it.progettosiw.pcexpress.model.*;
import it.progettosiw.pcexpress.repository.PCRepository;
import it.progettosiw.pcexpress.repository.SaleRepository;
import it.progettosiw.pcexpress.repository.SoldItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    private CartService cartService;
    private UserService userService;
    private SaleRepository saleRepository;
    private SoldItemRepository soldItemRepository;
    private PCRepository pcRepository;

    public SaleService(SaleRepository saleRepository, SoldItemRepository soldItemRepository, PCRepository pcRepository, UserService userService, CartService cartService) {
        this.saleRepository = saleRepository;
        this.soldItemRepository = soldItemRepository;
        this.pcRepository = pcRepository;
        this.userService = userService;
        this.cartService = cartService;
    }

    public Sale getSaleById(Long sale_id){
        Optional<Sale> optSale = saleRepository.findById(sale_id);
        if(!optSale.isPresent()){
            logger.error("sale non presente");
            return null;
        }
        return optSale.get();
    }

    public Sale getSaleOfCurrentUserById(Long saleId){
        Long userId = userService.getCurrentUser().getId();
        Sale sale = saleRepository.findById(saleId).get();
        if(sale.getUser().getId().equals(userId))
            return sale;
        return null;//exception
    }

    public List<Sale> getAllSales(){
        List<Sale> sales = (List<Sale>) saleRepository.findAllByOrderByDateOfSaleDesc();
        return sales;
    }

    public List<Sale> getCurrentUserPurchases(){
        return userService.getCurrentUser().getPurchases();
    }

    public SoldItem createSoldItem(Long pc_id, Integer quantity){
        Optional<PC> optPC = pcRepository.findById(pc_id);
        if(!optPC.isPresent()){
            logger.error("pc non trovatoooooo durante sale");
            return null;
        }
        PC pc = optPC.get();

        return new SoldItem(quantity, pc.getPrezzo(), pc);
    }


    //è chaiamto quando si acquista dalla pagina di un pc
    public Sale createSaleFromPC(Long pc_id, Integer quantity){
        SoldItem soldItem = createSoldItem(pc_id, quantity);
        Float totalSoldItemPrice = soldItem.getPaidMoney()*quantity;

        soldItem.getPc().reduceAvailability(quantity);

        List<SoldItem> soldItems = new ArrayList<>();
        soldItems.add(soldItem);

        Sale sale = new Sale(LocalDateTime.now(), totalSoldItemPrice, soldItems, userService.getCurrentUser());
        saleRepository.save(sale);
        return sale;
    }

    //è chiamato quando si acquista dal carrello
    public Sale createSaleFromCart(){
        User currentUser = userService.getCurrentUser();
        Cart currentCart = currentUser.getCart();

        List<SoldItem> soldItems = new ArrayList<>();
        Float totalSoldItemPrice = 0F;

        for(CartItem cartItem : currentCart.getCartItems()){
            Integer quantity = cartItem.getQuantity();
            PC pc = cartItem.getPc();
            pc.reduceAvailability(quantity);

            SoldItem soldItem = new SoldItem(quantity, pc.getPrezzo(), pc);

            totalSoldItemPrice += soldItem.getPaidMoney()*quantity;
            soldItems.add(soldItem);
        }

        Sale sale = new Sale(LocalDateTime.now(), totalSoldItemPrice, soldItems, currentUser);
        saleRepository.save(sale);
        cartService.emptyCurrentUserCart();
        return sale;
    }

}
