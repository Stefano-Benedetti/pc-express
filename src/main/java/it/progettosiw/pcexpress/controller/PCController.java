package it.progettosiw.pcexpress.controller;

import it.progettosiw.pcexpress.dto.ModifyPCForm;
import it.progettosiw.pcexpress.exceptions.PCWithThisCodeAlreadyExistsException;
import it.progettosiw.pcexpress.model.PC;
import it.progettosiw.pcexpress.service.PCService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class PCController {

    @Autowired
    private PCService pcService;

    public PCController(PCService pcService) {
        this.pcService = pcService;
    }

    @GetMapping("/pc/catalog")
    public String list(Model model){
        return "forward:/react/index.html";
        //serviva per la pagina vecchia (thymeleaf) del catalogo
        /*model.addAttribute("pcs",this.pcService.getAllPCs());
        return "pc/catalog";*/
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
    public String newPc(@Valid @ModelAttribute("pc") PC pc, BindingResult b, @RequestParam("image") MultipartFile image, Model model) throws IOException {
        if(b.hasErrors())
            return "admin/pc/newpc_form";
        try{
            if (image != null && !image.isEmpty() && image.getContentType().startsWith("image/"))
                pc.setImmagine(image.getBytes());
            this.pcService.save(pc);
            return "redirect:/pc/"+pc.getId().toString();
        }catch (PCWithThisCodeAlreadyExistsException e){
            b.reject("PC.duplicate");
            return "admin/pc/newpc_form";
        }

    }

    @GetMapping("/admin/pc/{id}/modify")
    public String modifyForm(@PathVariable("id") Long id, Model model){
        PC pc = this.pcService.getPCById(id);
        ModifyPCForm form = new ModifyPCForm(id, pc.getNome(), pc.getPrezzo(), pc.getDisponibilita());
        model.addAttribute("pc", form);
        return "/admin/pc/modify_form";
    }
    @PostMapping("/admin/pc/modify")
    public String modifyPc(@Valid @ModelAttribute("pc") ModifyPCForm form, BindingResult b, @RequestParam("image") MultipartFile image, Model model) throws IOException{
        if(b.hasErrors()) {
            model.addAttribute("pc", form);
            return "/admin/pc/modify_form";
        }
        this.pcService.update(form.getId(), form.getNome(), form.getPrezzo(), form.getDisponibilita(), image);
        return "redirect:/pc/"+form.getId().toString();
    }

    @GetMapping("/admin/pc/{id}/clone")
    public String cloneForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("pc", this.pcService.getPCById(id));
        return "/admin/pc/clone_form";
    }
    @PostMapping("/admin/pc/clone")
    public String clonePc(@Valid @ModelAttribute("pc") PC pc, BindingResult b, @RequestParam("image") MultipartFile image,
                          @RequestParam(defaultValue="false") boolean toZero, Model model) throws IOException{
        if(b.hasErrors()) {
            model.addAttribute("toZero", toZero);
            return "/admin/pc/clone_form";
        }
        try{
            Long newPcId = this.pcService.cloneWithChanges(pc, toZero, image);
            return "redirect:/pc/"+newPcId.toString();
        } catch (PCWithThisCodeAlreadyExistsException e) {
            b.reject("PC.duplicate");
            model.addAttribute("toZero", toZero);
            return "/admin/pc/clone_form";
        }
    }


    @GetMapping("/pc/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {
        PC pc = pcService.getPCById(id);
        byte[] image = pc.getImmagine();
        if (image == null || image.length == 0) {
            InputStream is = getClass().getResourceAsStream("/static/images/no_image.jpg");
            image = is.readAllBytes();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }

}
