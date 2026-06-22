package it.progettosiw.pcexpress.controller;

import it.progettosiw.pcexpress.model.PC;
import it.progettosiw.pcexpress.service.PCService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String newPc(@Valid @ModelAttribute("pc") PC pc, BindingResult b, Model model){
        if(b.hasErrors())
            return "admin/pc/newpc_form";
        this.pcService.save(pc);
        return "redirect:/pc/"+pc.getId().toString();
    }

    @GetMapping("/admin/pc/{id}/modify")
    public String modifyForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("pc", this.pcService.getPCById(id));
        return "/admin/pc/modify_form";
    }
    @PostMapping("/admin/pc/modify")
    public String modifyPc(@Valid @ModelAttribute("pc") PC pc, BindingResult b, Model model){
        if(b.hasErrors()) {
            System.out.println(b.getAllErrors().toString());//////////////////////////
            return "/admin/pc/modify_form";
        }
        this.pcService.update(pc.getId(), pc.getNome(), pc.getPrezzo(),pc.getDisponibilita());
        return "redirect:/pc/"+pc.getId().toString();
    }

    @GetMapping("/admin/pc/{id}/clone")
    public String cloneForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("pc", this.pcService.getPCById(id));
        return "/admin/pc/clone_form";
    }
    @PostMapping("/admin/pc/clone")
    public String clonePc(@Valid @ModelAttribute("pc") PC pc, BindingResult b, @RequestParam(defaultValue="false") boolean toZero, Model model){
        if(b.hasErrors()) {
            model.addAttribute("toZero", toZero);
            return "/admin/pc/clone_form";
        }
        Long newPcId = this.pcService.cloneWithChanges(pc, toZero);
        return "redirect:/pc/"+newPcId.toString();
    }

}
