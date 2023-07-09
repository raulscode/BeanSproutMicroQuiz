package com.raulbetancourt.beansprout.model;

import jakarta.persistence.*;

import java.util.List;

//Model class for the Role entity.
//This would be associated with a user. It would indicate, for example, if they are an admin or a regular user.
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int roleID;

    @Column
    String roleName;

    //Maps the many-to-many relationship between roles and users.
    @ManyToMany(mappedBy="roles")
    private List<User> users;

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
