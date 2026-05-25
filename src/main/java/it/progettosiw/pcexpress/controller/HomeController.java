package it.progettosiw.pcexpress.controller;

import it.progettosiw.pcexpress.model.PC;
import it.progettosiw.pcexpress.model.User;
import it.progettosiw.pcexpress.service.PCService;
import it.progettosiw.pcexpress.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    public HomeController(){
    }

    @GetMapping("/")
    public String getHome(Model model){
        return "index.html";
    }

    @GetMapping("/admin/")
    public String getAdminHome(Model model){
        return "/admin/index.html";
    }


}