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
        return "/index.html";
    }

    @GetMapping("/user/personal_area")
    public String getPersonalArea(Model model){
        model.addAttribute("user", userService.getCurrentUser());
        return "/user/personal_area.html";
    }

    @GetMapping("/user/modify_user_info_form")
    public String getModifyUserInfoForm(Model model){
        model.addAttribute("user", userService.getCurrentUser());
        return "/user/modify_user_info_form.html";
    }

    @PostMapping("/user/modify_user_info")
    public String modifyUserInfo(@ModelAttribute("user") User user ,Model model){
        System.out.println(user.getDateOfBirth());
        userService.updateCurrentUserInfo(user.getFirstName(), user.getLastName(), user.getDateOfBirth(), user.getPhoneNumber());
        return "redirect:/user/personal_area";
    }
}
