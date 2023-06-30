package com.beansprout.BeanSprout.controller;

import com.beansprout.BeanSprout.model.User;
import com.beansprout.BeanSprout.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model)
    {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("This login was successful.");
            return "redirect:/profile";
        }
        else
        {
            System.out.println("This login was unsuccessful.");
            model.addAttribute("errorMessage", true);
            return "login";
        }
    }

}
