package com.beansprout.BeanSprout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class GradePageController {

    @GetMapping("/gradepage")
    public String getGradePage(Model model, @ModelAttribute("quizGrade") Double quizGrade) {



        model.addAttribute(quizGrade);

        return "QuizGradePage";

    }
}
