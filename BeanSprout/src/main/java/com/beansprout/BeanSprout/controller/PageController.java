package com.beansprout.BeanSprout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/"})
    public String sendData(Model model)
    {
        String str = "test";
        model.addAttribute("testWord", str);
        return "FrontPage";
    }

}
