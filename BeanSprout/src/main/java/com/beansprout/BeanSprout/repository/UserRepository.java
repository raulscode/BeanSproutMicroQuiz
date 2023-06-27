package com.beansprout.BeanSprout.repository;

import com.beansprout.BeanSprout.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

    User findByUsername(String username);

}
