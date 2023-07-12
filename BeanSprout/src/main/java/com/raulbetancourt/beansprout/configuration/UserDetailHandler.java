package com.raulbetancourt.beansprout.configuration;

import com.raulbetancourt.beansprout.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.raulbetancourt.beansprout.model.Role;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailHandler implements UserDetails {

    private User user;
    private List<Role> roles;

    //Main constructor
    public UserDetailHandler(User user, List<Role> roles) {
        super();
        this.user = user;
        this.roles = roles;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
