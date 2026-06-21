package it.progettosiw.pcexpress.controller;

import it.progettosiw.pcexpress.model.Credentials;
import it.progettosiw.pcexpress.model.User;
import it.progettosiw.pcexpress.service.UserService;
import it.progettosiw.pcexpress.validation.AgeValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private AgeValidator ageValidator;

    public UserController(UserService userService) {
        this.userService = userService;
        ageValidator = new AgeValidator();
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
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bUser, Model model){
        this.ageValidator.validate(user, bUser);
        if (bUser.hasErrors()) {
            System.out.println(bUser.getAllErrors().toString());
            return "/register.html";
        }
        userService.register(user);
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
    public String modifyUserInfo(@Valid @ModelAttribute("user") User user, BindingResult b,Model model){
        this.ageValidator.validate(user, b);
        if (b.hasErrors())
            return "/user/modify_user_info_form.html";
        userService.updateCurrentUserInfo(user.getFirstName(), user.getLastName(), user.getDateOfBirth(), user.getPhoneNumber());
        return "redirect:/user/personal_area";
    }

    @GetMapping("/admin/users/{user_id}")
    public String getUserInfo(@PathVariable("user_id") Long user_id , Model model){
        model.addAttribute("user", userService.getUserById(user_id));
        return "/admin/users/user_info";
    }

    @GetMapping("/admin/users/all_users")
    public String getUserInfo(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "/admin/users/all_users";
    }
}
