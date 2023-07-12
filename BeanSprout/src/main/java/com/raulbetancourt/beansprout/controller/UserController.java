package com.raulbetancourt.beansprout.controller;

import com.raulbetancourt.beansprout.Service.UserServiceImpl;
import com.raulbetancourt.beansprout.dataservice.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }

    private UserServiceImpl userDetailsService;

    @Autowired
    public UserController(UserServiceImpl userDetailsService)
    {
        this.userDetailsService = userDetailsService;
    }

    //user sign up page controller
    @GetMapping("/signup")
    public String signUp(Model model)
    {
        model.addAttribute("userDTO", new UserDTO());
        return "signup";
    }

    //User login controller
    @GetMapping("/generatequiz")
    private String loginRedirect()
    {
        return "redirect:/login";
    }

    @PostMapping("/usersignup")
    public String signupProcess(@Valid @ModelAttribute ("userDTO") UserDTO userDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes)
    {
        String operation = "signed up with";
        String entityType = "new account";

        if(bindingResult.hasErrors())
        {
            System.out.println("We encountered an error signing up!");
            return "sign-up";
        }
        userDetailsService.createUserMap(userDTO);

        redirectAttributes.addFlashAttribute("operation", operation);
        redirectAttributes.addFlashAttribute("entitytype", entityType);

        return "redirect:/successpage";
    }

    //Displays login page
    @GetMapping("/login")
    public String getLogin()
    {
        return "login";
    }


}
