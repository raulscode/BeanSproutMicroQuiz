package com.raulbetancourt.beansprout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Hypothetical profile page controller, would display user's saved quizzes.
@Controller
public class ProfilePageController {

    @GetMapping("/profile")
    public String profile() {

        return "profile";
    }

}
