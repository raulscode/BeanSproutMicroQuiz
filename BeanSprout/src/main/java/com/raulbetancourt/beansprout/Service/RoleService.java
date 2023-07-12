package com.raulbetancourt.beansprout.Service;

import com.raulbetancourt.beansprout.model.Role;
import org.springframework.stereotype.Service;
import java.util.List;

//Interface for services for Role entity, implemented by RoleServiceImpl
@Service
public interface RoleService {

    public void saveRole(Role role);
    public Role findRoleByRoleName(String name);
    public List<Role> getAllRoles();
    public List<Role> getRolesByUser(long id);

}
