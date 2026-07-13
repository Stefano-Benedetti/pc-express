package it.progettosiw.pcexpress.controller;

import it.progettosiw.pcexpress.exceptions.SaleNotFoundException;
import it.progettosiw.pcexpress.exceptions.TooLowAvailabilityException;
import it.progettosiw.pcexpress.model.Sale;
import it.progettosiw.pcexpress.service.SaleService;
import it.progettosiw.pcexpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/sale/single_item/makeNewSale")
    public String createSaleFromCatalog(@RequestParam Long pcId, @RequestParam Integer quantity, Model model){
        Sale sale = saleService.createSaleFromPC(pcId, quantity);
        return "redirect:/sale/single_sale/"+sale.getId().toString();
    }

    @PostMapping("/sale/cart/makeNewSale")
    public String createSaleFromCart(Model model){
        Sale sale = saleService.createSaleFromCart();
        return "redirect:/sale/single_sale/"+sale.getId().toString();
    }

    @GetMapping("/sale/single_sale/{sale_id}")
    public String showForSingleSale(@PathVariable("sale_id") Long sale_id, Model model){
        Sale sale = saleService.getSaleById(sale_id);
        if(!saleService.isCurrentUserBuyerOfSale(sale))
            throw new SaleNotFoundException(sale_id);
        model.addAttribute("sale", sale);
        return "/sale/single_sale";
    }

    @GetMapping("/sale/user_sales")
    public String showUserSales(Model model){
        model.addAttribute("purchases", saleService.getCurrentUserPurchases());
        return "/sale/user_sales";
    }

    @GetMapping("/admin/sale/all_sales")
    public String showAllSales(Model model){
        model.addAttribute("sales", saleService.getAllSales());
        return "/admin/sale/all_sales";
    }

    @GetMapping("/admin/sale/{sale_id}")
    public String showSingeUserSale(@PathVariable("sale_id") Long sale_id, Model model){
        model.addAttribute("sale", saleService.getSaleById(sale_id));
        return "/admin/sale/single_sale";
    }
}

