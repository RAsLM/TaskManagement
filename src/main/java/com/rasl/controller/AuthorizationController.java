package com.rasl.controller;

import com.rasl.Validator.UserValidator;
import com.rasl.pojo.User;
import com.rasl.pojo.dto.UserDTO;
import com.rasl.services.SecurityServiceImpl;
import com.rasl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizationController {
    private static final int WEAK_STRENGTH = 1;
    private static final int FEAR_STRENGTH = 5;
    private static final int STRONG_STRENGTH = 7;


    private UserService userService;
    private UserValidator userValidator;
    private SecurityServiceImpl securityServiceImpl;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @Autowired
    public void setSecurityServiceImpl(SecurityServiceImpl securityServiceImpl) {
        this.securityServiceImpl = securityServiceImpl;
    }

    @RequestMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model){
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "/auth/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "auth/register";
    }


    @RequestMapping(value = "/checkStrength", method = RequestMethod.GET, produces = {"text/html; charset=UtF-8"})
    String checkStrength(@RequestParam String password){
        if(password.length() >= WEAK_STRENGTH & password.length() < FEAR_STRENGTH){
            return "Слабый";
        }else if(password.length() >= FEAR_STRENGTH & password.length() < STRONG_STRENGTH){
            return "Средний";
        } else if (password.length() >= STRONG_STRENGTH){
            return "Сильный";
        }
        return "";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        userService.createUser(userForm);

        securityServiceImpl.autoLogin(userForm.getUsername(), userForm.getMatchingPassword());

        return "redirect:/home";
    }

}
