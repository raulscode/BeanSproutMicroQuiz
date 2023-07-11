package com.raulbetancourt.beansprout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Error controller
@Controller
public class ErrorController {

    //Mapping for a custom error page where error message can be included with extra information
    @GetMapping("/customerror")
    public String customError(Model model, @RequestParam("errormessage") String errorMessage){

        model.addAttribute("errormessage", errorMessage);

        return "error_custom";
    }

}
