package it.progettosiw.pcexpress.controller;

import it.progettosiw.pcexpress.model.User;
import it.progettosiw.pcexpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginForm(Model model){
        return "/login.html";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        model.addAttribute("user", new User());
        return "/register.html";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, @RequestParam String password, Model model){
        userService.register(user, password);
        return "/";
    }
}
