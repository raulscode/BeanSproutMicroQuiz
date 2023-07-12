package com.raulbetancourt.beansprout.repository;

import com.raulbetancourt.beansprout.model.FlashCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//Repository for flash card entity.
@Repository
public interface FlashCardRepository extends CrudRepository<FlashCard, Integer> {

    FlashCard findByCardID(Integer cardID);

}
