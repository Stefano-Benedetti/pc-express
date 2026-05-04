package it.progettosiw.pcexpress.controller;

import it.progettosiw.pcexpress.service.PCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PCController {

    @Autowired
    private PCService pcService;

    public PCController(PCService pcService) {
        this.pcService = pcService;
    }

    @GetMapping("/pc/catalog")
    public String list(Model model){
        model.addAttribute("pcs",this.pcService.getAllPCs());
        return "pc/catalog";
    }

    @GetMapping("/pcs/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("pc", this.pcService.getPCById(id));
        return "pc/show";
    }

}
