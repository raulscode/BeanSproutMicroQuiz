package com.raulbetancourt.beansprout.configuration;

import com.raulbetancourt.beansprout.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailHandler implements UserDetails {

    private User user;
    private List<Roles> roles;

}
