package it.progettosiw.pcexpress.controller;

import it.progettosiw.pcexpress.service.PCService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private PCService pcService;

    public HomeController(PCService pcService){
        this.pcService = pcService;
    }

    @GetMapping("/")
    public String getHome(Model model){
        return "index.html";
    }

}