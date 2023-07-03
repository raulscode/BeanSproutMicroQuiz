package com.beansprout.BeanSprout.repository;

import com.beansprout.BeanSprout.model.FlashCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashCardRepository extends CrudRepository<FlashCard, Integer> {

}
