package it.progettosiw.pcexpress.controller;

import it.progettosiw.pcexpress.model.Sale;
import it.progettosiw.pcexpress.service.PCService;
import it.progettosiw.pcexpress.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SaleController {

    @Autowired
    private SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/sale/makeNewSale")
    public String createSaleForSingleItem(@RequestParam Long pcId, @RequestParam Integer quantity, Model model){
        Sale sale = saleService.createSaleFromPC(pcId, quantity);
        return "redirect:/sale/single_sale/"+sale.getId().toString();
    }

    @GetMapping("/sale/single_sale/{sale_id}")
    public String showForSingleSale(@PathVariable("sale_id") Long sale_id, Model model){
        model.addAttribute("sale", saleService.getSaleById(sale_id));
        return "/sale/single_sale";
    }
}

