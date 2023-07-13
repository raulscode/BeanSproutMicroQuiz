package com.raulbetancourt.beansprout.controller;

import com.raulbetancourt.beansprout.Service.FlashCardService;
import com.raulbetancourt.beansprout.Service.QuizService;
import com.raulbetancourt.beansprout.Service.UserServiceImpl;
import com.raulbetancourt.beansprout.model.FlashCard;
import com.raulbetancourt.beansprout.model.Quiz;
import com.raulbetancourt.beansprout.model.Role;
import com.raulbetancourt.beansprout.model.User;
import com.raulbetancourt.beansprout.repository.FlashCardRepository;
import com.raulbetancourt.beansprout.repository.QuizRepository;
import com.raulbetancourt.beansprout.repository.RoleRepository;
import com.raulbetancourt.beansprout.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoadTestCardsController {

    FlashCardRepository flashCardRepository;
    QuizRepository quizRepository;

    FlashCardService flashCardService;
    QuizService quizService;

    UserRepository userRepository;

    RoleRepository roleRepository;

    public LoadTestCardsController(FlashCardRepository flashCardRepository, QuizRepository quizRepository, FlashCardService flashCardService, QuizService quizService, RoleRepository roleRepository, UserRepository userRepository) {
        this.flashCardRepository = flashCardRepository;
        this.quizRepository = quizRepository;
        this.flashCardService = flashCardService;
        this.quizService = quizService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/loadexamples")
    public String loadExamples(Model model, RedirectAttributes redirectAttributes){

        String operation = "loaded";
        String entityType = "example";

        redirectAttributes.addFlashAttribute("operation",operation);
        redirectAttributes.addFlashAttribute("entitytype",entityType);

        quizService.createExampleModels();



        return "redirect:/successpage";

    }


}
