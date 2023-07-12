package com.raulbetancourt.beansprout.Service;

import com.raulbetancourt.beansprout.model.Role;
import com.raulbetancourt.beansprout.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//Service class that implements RoleService interface
@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    //Constructor
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

    //Grab all roles
    @Override
    public List<Role> getAllRoles() {

        return (List<Role>) roleRepository.findAll();

    }

    //Get roles based on user ID (uses the method in RoleRepository with the custom query)
    @Override
    public List<Role> getRolesByUser(long id) {

        return roleRepository.findRoleByUser(id);

    }

    //Save a role to the repository
    @Override
    @Transactional
    public void saveRole (Role role) {

        roleRepository.save(role);

    }

    //Find the role by role name
    @Override
    @Transactional
    public Role findRoleByRoleName(String name) {

        return roleRepository.findByRoleName(name);

    }

}
