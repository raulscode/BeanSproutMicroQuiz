package com.raulbetancourt.beansprout.repository;

import com.raulbetancourt.beansprout.model.Role;

public interface RoleRepository {

    Role findByRoleId(Integer roleID);

}
