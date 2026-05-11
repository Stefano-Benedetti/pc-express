package it.progettosiw.pcexpress.controller;

import it.progettosiw.pcexpress.model.CartItem;
import it.progettosiw.pcexpress.model.PC;
import it.progettosiw.pcexpress.service.PCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/pc/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("pc", this.pcService.getPCById(id));
        return "pc/show";
    }

    @GetMapping("/admin/pc/newpc_form")
    public String newPcForm(Model model){
        model.addAttribute("pc", new PC());
        return "admin/pc/newpc_form";
    }
    @PostMapping("/admin/pc/newpc_form")
    public String newpc(@ModelAttribute("pc") PC pc, Model model){
        this.pcService.save(pc);
        model.addAttribute("pc", pc);
        return "redirect:/pc/"+pc.getId().toString();
    }

}
