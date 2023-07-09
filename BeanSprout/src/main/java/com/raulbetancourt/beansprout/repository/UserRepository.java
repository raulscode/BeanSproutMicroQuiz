package com.raulbetancourt.beansprout.repository;

import com.raulbetancourt.beansprout.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

    User findByUsername(String username);

}
