package it.progettosiw.pcexpress.service;

import it.progettosiw.pcexpress.exceptions.NonPositiveQuantityException;
import it.progettosiw.pcexpress.model.*;
import it.progettosiw.pcexpress.exceptions.EmptyCartDuringSaleCreationException;
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

    @Transactional(readOnly = true)
    public Sale getSaleById(Long sale_id){
        Optional<Sale> optSale = saleRepository.findById(sale_id);
        if(!optSale.isPresent()){
            logger.error("sale non presente");
            return null;
        }
        return optSale.get();
    }

    public Boolean isCurrentUserBuyerOfSale(Sale sale){
        User user = userService.getCurrentUser();
        if(sale.getUser().equals(user))
            return true;
        return false;
    }

    @Transactional(readOnly = true)
    public List<Sale> getAllSales(){
        List<Sale> sales = (List<Sale>) saleRepository.findAllByOrderByDateOfSaleDesc();
        return sales;
    }

    @Transactional(readOnly = true) //perchè fa una query per prendere gli acquisti (sono lazy per l'utente)
    public List<Sale> getCurrentUserPurchases(){
        return userService.getCurrentUser().getPurchases();
    }

//    @Transactional(readOnly = true)
//    public SoldItem createSoldItem(Long pc_id, Integer quantity) throws NonPositiveQuantityException{
//        if (quantity<1)
//            throw new NonPositiveQuantityException();
//        Optional<PC> optPC = pcRepository.findById(pc_id);
//        if(!optPC.isPresent()){
//            logger.error("pc non trovatoooooo durante sale");
//            return null;
//        }
//        PC pc = optPC.get();
//
//        return new SoldItem(quantity, pc.getPrezzo(), pc);
//    }
//
//    //è chaiamato quando si acquista dalla pagina di un pc
//    @Transactional(isolation = Isolation.SERIALIZABLE)  //necessario a causa della riduzione della disponibilità
//    public Sale createSaleFromPC(Long pc_id, Integer quantity) throws NonPositiveQuantityException{
//        SoldItem soldItem = createSoldItem(pc_id, quantity);
//        Float totalSoldItemPrice = soldItem.getPaidMoney()*quantity;
//
//        soldItem.getPc().reduceAvailability(quantity);
//
//        List<SoldItem> soldItems = new ArrayList<>();
//        soldItems.add(soldItem);
//
//        User currentUser = userService.getCurrentUser();
//
//        Sale sale = new Sale(LocalDateTime.now(), totalSoldItemPrice, soldItems, currentUser);
//
//        currentUser.getPurchases().add(sale);  //serve a mantenere aggiornato lo user in locale
//
//        saleRepository.save(sale);
//        return sale;
//    }
//
//    //è chiamato quando si acquista dal carrello
//    @Transactional(isolation = Isolation.SERIALIZABLE)
//    public Sale createSaleFromCart() throws NonPositiveQuantityException, EmptyCartDuringSaleCreationException{
//        User currentUser = userService.getCurrentUser();
//        Cart currentCart = currentUser.getCart();
//
//        if (currentCart.getCartItems().isEmpty()){
//            throw new EmptyCartDuringSaleCreationException();
//        }
//
//        List<SoldItem> soldItems = new ArrayList<>();
//        Float totalSoldItemPrice = 0F;
//
//        for(CartItem cartItem : currentCart.getCartItems()){
//            Integer quantity = cartItem.getQuantity();
//
//            if (quantity<1)
//                throw new NonPositiveQuantityException();
//
//            PC pc = cartItem.getPc();
//            pc.reduceAvailability(quantity);
//
//            SoldItem soldItem = new SoldItem(quantity, pc.getPrezzo(), pc);
//
//            totalSoldItemPrice += soldItem.getPaidMoney()*quantity;
//            soldItems.add(soldItem);
//        }
//
//        Sale sale = new Sale(LocalDateTime.now(), totalSoldItemPrice, soldItems, currentUser);
//
//        currentUser.getPurchases().add(sale);  //serve a mantenere aggiornato lo user in locale
//
//        saleRepository.save(sale);
//        cartService.emptyCurrentUserCart();
//        return sale;
//    }



//    @Transactional(readOnly = true)
//    public SoldItem createSoldItem(Long pc_id, Integer quantity) throws NonPositiveQuantityException{
//        if (quantity<1)
//            throw new NonPositiveQuantityException();
//        Optional<PC> optPC = pcRepository.findById(pc_id);
//        if(!optPC.isPresent()){
//            logger.error("pc non trovatoooooo durante sale");
//            return null;
//        }
//        PC pc = optPC.get();
//
//        return new SoldItem(quantity, pc.getPrezzo(), pc);
//    }
//
//
//    //è chaiamato quando si acquista dalla pagina di un pc
//    @Transactional(isolation = Isolation.SERIALIZABLE)  //necessario a causa della riduzione della disponibilità
//    public Sale createSaleFromPC(Long pc_id, Integer quantity) throws NonPositiveQuantityException{
//        User currentUser = userService.getCurrentUser();
//        List<SoldItem> soldItems = new ArrayList<>();
//        Float totalSoldItemPrice = 0F;
//
//        SoldItem soldItem = createSoldItem(pc_id, quantity);
//        totalSoldItemPrice = soldItem.getPaidMoney()*quantity;
//        soldItems.add(soldItem);
//
//        soldItem.getPc().reduceAvailability(quantity);
//
//        Sale sale = new Sale(LocalDateTime.now(), totalSoldItemPrice, soldItems, currentUser);
//        currentUser.getPurchases().add(sale);  //serve a mantenere aggiornato lo user in locale
//
//        saleRepository.save(sale);
//        return sale;
//    }
//
//    //è chiamato quando si acquista dal carrello
//    @Transactional(isolation = Isolation.SERIALIZABLE)
//    public Sale createSaleFromCart() throws NonPositiveQuantityException, EmptyCartDuringSaleCreationException{
//        User currentUser = userService.getCurrentUser();
//        Cart currentCart = currentUser.getCart();
//        if (currentCart.getCartItems().isEmpty()){
//            throw new EmptyCartDuringSaleCreationException();
//        }
//        List<SoldItem> soldItems = new ArrayList<>();
//        Float totalSoldItemPrice = 0F;
//
//        for(CartItem cartItem : currentCart.getCartItems()){
//            Integer quantity = cartItem.getQuantity();
//            if (quantity<1)
//                throw new NonPositiveQuantityException();
//            PC pc = cartItem.getPc();
//            SoldItem soldItem = new SoldItem(quantity, pc.getPrezzo(), pc);
//            totalSoldItemPrice += soldItem.getPaidMoney()*quantity;
//            soldItems.add(soldItem);
//
//            pc.reduceAvailability(quantity);
//        }
//
//        Sale sale = new Sale(LocalDateTime.now(), totalSoldItemPrice, soldItems, currentUser);
//        currentUser.getPurchases().add(sale);  //serve a mantenere aggiornato lo user in locale
//
//        saleRepository.save(sale);
//        cartService.emptyCurrentUserCart();
//        return sale;
//    }



    @Transactional(readOnly = true)
    public SoldItem createSoldItem(Long pc_id, Integer quantity) throws NonPositiveQuantityException{
        if (quantity<1)
            throw new NonPositiveQuantityException();
        Optional<PC> optPC = pcRepository.findById(pc_id);
        if(!optPC.isPresent()){
            logger.error("pc non trovatoooooo durante sale");
            return null;
        }
        PC pc = optPC.get();

        return new SoldItem(quantity, pc.getPrezzo(), pc);
    }

    private SoldItem createSoldItem(CartItem cartItem) throws NonPositiveQuantityException {
        Integer quantity = cartItem.getQuantity();
        if (quantity < 1)
            throw new NonPositiveQuantityException();
        return new SoldItem(quantity, cartItem.getPc().getPrezzo(), cartItem.getPc());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)  //necessario a causa della riduzione della disponibilità
    public Sale createAndSaveSale(User user, List<SoldItem> soldItems){
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
    public Sale createSaleFromPC(Long pc_id, Integer quantity) throws NonPositiveQuantityException{
        User currentUser = userService.getCurrentUser();
        SoldItem soldItem = createSoldItem(pc_id, quantity);
        return createAndSaveSale(currentUser, List.of(soldItem));
    }

    //è chiamato quando si acquista dal carrello
    @Transactional //come gestisco il fatto che il carrello viene svuotato??????????
    public Sale createSaleFromCart() throws NonPositiveQuantityException, EmptyCartDuringSaleCreationException{
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
