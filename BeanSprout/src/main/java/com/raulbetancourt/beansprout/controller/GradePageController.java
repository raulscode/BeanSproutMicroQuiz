package com.raulbetancourt.beansprout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

//Controller for the page that displays the grade.
@Controller
public class GradePageController {

    //Grade is not actually calculated here, but rather in the quiz page controller and passed to this controller.
    @GetMapping("/gradepage")
    public String getGradePage(Model model, @ModelAttribute("quizGrade") Long quizGrade) {



        model.addAttribute(quizGrade);

        return "quiz_grade_page";

    }
}
