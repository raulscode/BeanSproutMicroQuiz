package com.beansprout.BeanSprout.repository;

import com.beansprout.BeanSprout.model.Role;

public interface RoleRepository {

    Role findByRoleId(Integer roleID);

}
