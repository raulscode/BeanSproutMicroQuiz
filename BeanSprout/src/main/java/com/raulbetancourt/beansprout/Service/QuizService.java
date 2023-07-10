package com.raulbetancourt.beansprout.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raulbetancourt.beansprout.model.FlashCard;
import com.raulbetancourt.beansprout.model.Quiz;
import com.raulbetancourt.beansprout.repository.QuizRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

import java.util.List;


//Service class for quiz entity.
@Service
public class QuizService {

    private QuizRepository quizRepository;

    //Going to use this entity manager for custom query in changeQuizTitle() method.
    @PersistenceContext
    private EntityManager entityManager;

    public QuizService(QuizRepository quizRepository)
    {
        this.quizRepository = quizRepository;
    }

    //Just a method that fetches all quizzes in the quizzes table.
    public List<Quiz> getQuizzes(){

        return (List<Quiz>) quizRepository.findAll();
    }

    //Deletes a quiz after finding it by its ID and clears associations with flash cards (but does not delete flash cards).
    public void deleteQuizById(Integer quizID) {

        //Retrieve quiz.
        Quiz quiz = quizRepository.findById(quizID).orElseThrow();

        //Remove relationship in flash card entities first. (Otherwise it will not work.)
        for (FlashCard flashCard : quiz.getFlashCards())
        {
            flashCard.getQuizCollect().remove(quiz);
        }

        //Remove relationship to any related Quiz entities on FlashCard side.
        quiz.getFlashCards().clear();

        //Save changes.
        quizRepository.save(quiz);

        //Delete flash card.
        quizRepository.delete(quiz);

    }

    //Method that changes quiz title with a custom query the old-fashioned way.
    @Transactional
    public boolean changeQuizTitle(Integer quizID, String newQuizTitle){

        boolean success = false;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String dburl = "jdbc:mysql://localhost:3306/beansproutdb";
        String user = "root";
        String password = "password";
        int updatedRows = 0;

        try{

            if(quizID != null) {
                connection = DriverManager.getConnection(dburl, user, password);
                String customQuery = "UPDATE quizzes q SET q.quiz_title = " + "\"" + newQuizTitle + "\"" + " WHERE q.quizid = " + quizID + ";";
                preparedStatement = connection.prepareStatement(customQuery);
                try {
                    updatedRows = preparedStatement.executeUpdate();
                }
                catch(Exception e){
                    System.out.println(e);
                    System.out.println("Error!");
                }

                if(updatedRows > 0)
                {
                    success = true;
                }

                try{
                    preparedStatement.close();
                    connection.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }

            }
            else
            {
                //success will remain false
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return success;
    }

}
