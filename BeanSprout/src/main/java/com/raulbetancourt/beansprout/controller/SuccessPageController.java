package com.raulbetancourt.beansprout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Controller for the success page after performing an editing or deleting operation.
@Controller
public class SuccessPageController {

    //Receives String called "operation" which says whether item was edited or deleted so that
    //user can see appropriate confirmation.
    @GetMapping("/successpage")
    public String showSuccessPage(Model model, @RequestParam(name = "operation", required = false) String operation) {

        return "success_page";

    }

}
