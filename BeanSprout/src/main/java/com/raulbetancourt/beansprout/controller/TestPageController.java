package com.raulbetancourt.beansprout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//Just a controller for the test page
@Controller
public class TestPageController {

    //Grabs an HTML page with a few lines of text.
    @GetMapping({"/test"})
    public String sendData(Model model)
    {
        String str = "test";
        model.addAttribute("testWord", str);
        return "test_page";
    }

}
