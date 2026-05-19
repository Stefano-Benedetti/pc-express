package it.progettosiw.pcexpress.controller;

import it.progettosiw.pcexpress.service.PCService;
import it.progettosiw.pcexpress.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SaleController {

    @Autowired
    private SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/sale/makeNewSale/item")
    public String createSaleForSingleItem(){
        return "";
    }
}

