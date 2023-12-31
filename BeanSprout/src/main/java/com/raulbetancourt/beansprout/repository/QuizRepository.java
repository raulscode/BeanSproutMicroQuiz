package com.raulbetancourt.beansprout.repository;

import com.raulbetancourt.beansprout.model.Quiz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//Repository for quiz entity.
@Repository
public interface QuizRepository extends CrudRepository<Quiz, Integer> {

    Quiz findByQuizID(Integer quizID);

}
