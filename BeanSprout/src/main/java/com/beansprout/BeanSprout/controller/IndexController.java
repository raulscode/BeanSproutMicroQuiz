package com.beansprout.BeanSprout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/home")
    public String indexPage() {

        return "index";

    }

}
