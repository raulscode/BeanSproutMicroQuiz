package com.raulbetancourt.beansprout.repository;

import com.raulbetancourt.beansprout.model.Role;
import com.raulbetancourt.beansprout.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Repository for role entity.
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    //Method to find role by its ID. (Maybe move this to service class.)
    Role findByRoleId(Integer roleID);

}
