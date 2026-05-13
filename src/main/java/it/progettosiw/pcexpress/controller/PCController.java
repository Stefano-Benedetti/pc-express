package it.progettosiw.pcexpress.controller;

import it.progettosiw.pcexpress.model.PC;
import it.progettosiw.pcexpress.service.PCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String newPc(@ModelAttribute("pc") PC pc, Model model){
        this.pcService.save(pc);
        return "redirect:/pc/"+pc.getId().toString();
    }

    @GetMapping("/admin/pc/{id}/modify")
    public String modifyForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("pc", this.pcService.getPCById(id));
        return "/admin/pc/modify_form";
    }
    @PostMapping("/admin/pc/modify")
    public String modifyPc(@ModelAttribute("pc") PC pc, Model model){
        this.pcService.update(pc.getId(), pc.getNome(), pc.getPrezzo(),pc.getDisponibilita());
        return "redirect:/pc/"+pc.getId().toString();
    }

    @GetMapping("/admin/pc/{id}/clone")
    public String cloneForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("pc", this.pcService.getPCById(id));
        return "/admin/pc/clone_form";
    }
    @PostMapping("/admin/pc/clone")
    public String clonePc(@ModelAttribute("pc") PC pc, @RequestParam(defaultValue="false") boolean toZero, Model model){
        Long newPcId = this.pcService.cloneWithChanges(pc, toZero);
        return "redirect:/pc/"+newPcId.toString();
    }

}
