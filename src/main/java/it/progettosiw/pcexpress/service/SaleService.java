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


    private UserService userService;
    private SaleRepository saleRepository;
    private SoldItemRepository soldItemRepository;
    private PCRepository pcRepository;

    public SaleService(SaleRepository saleRepository, SoldItemRepository soldItemRepository, PCRepository pcRepository, UserService userService) {
        this.saleRepository = saleRepository;
        this.soldItemRepository = soldItemRepository;
        this.pcRepository=pcRepository;
        this.userService = userService;
    }

    public Sale getSaleById(Long sale_id){
        Optional<Sale> optSale = saleRepository.findById(sale_id);
        if(!optSale.isPresent()){
            logger.error("sale non presente");
            return null;
        }
        return optSale.get();
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

    //è chiamato quando si acquista dal carrello
    public void createSale(Cart cart){

    }
    //è chaiamto quando si acquista dalla pagina di un pc
    public Sale createSaleFromPC(Long pc_id, Integer quantity){
        SoldItem soldItem = createSoldItem(pc_id, quantity);
        Float totalSoldItemPrice = soldItem.getPaidMoney()*quantity;

        List<SoldItem> soldItems = new ArrayList<>();
        soldItems.add(soldItem);

        Sale sale = new Sale(LocalDateTime.now(), totalSoldItemPrice, soldItems, userService.getCurrentUser());
        saleRepository.save(sale);
        return sale;
    }

}
