package com.raulbetancourt.beansprout.repository;

import com.raulbetancourt.beansprout.model.FlashCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashCardRepository extends CrudRepository<FlashCard, Integer> {

}
