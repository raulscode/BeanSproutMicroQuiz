package com.raulbetancourt.beansprout.repository;

import com.raulbetancourt.beansprout.model.Role;
import com.raulbetancourt.beansprout.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Repository for role entity.
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);

    @Query(value = "select * from roles where roleid= (select roleid from user_role where userid = :id)", nativeQuery = true)
    public List<Role> findRoleByUser(@Param("id") long id);

}
