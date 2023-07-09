package com.raulbetancourt.beansprout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//Just a controller to display the main index page of the site.
@Controller
public class IndexController {

    @GetMapping("/")
    public String indexPage() {

        return "index";

    }

}
