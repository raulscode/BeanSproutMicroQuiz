package com.raulbetancourt.beansprout.repository;

import com.raulbetancourt.beansprout.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Repository for user entity.
@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    //Method to find user by username. May later move to service class for better abstraction/organization.
    User findByUsername(String username);

}
