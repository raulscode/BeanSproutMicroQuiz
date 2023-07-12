package com.raulbetancourt.beansprout.dataservice;

import com.raulbetancourt.beansprout.Service.FieldMatch;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@FieldMatch.List( { @FieldMatch(first = "password", second = "secondPassword" , message = "Passwords must match!") } )
public class UserDTO {

    @NotEmpty
    private String userName;

    //Require password match
    @NotEmpty(message = "Required")
    private String password;

    @NotEmpty(message = "Required")
    private String secondPassword;

    //Empty constructor
    public UserDTO(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword;
    }

    //Disturbingly long constructor, but at least there's no regex in my case
    public UserDTO(@NotEmpty String userName, @NotEmpty(message = "Required") String password, @NotEmpty(message = "Required") String secondPassword)
    {

        this.userName = userName;
        this.password = password;
        this.secondPassword = secondPassword;

    }

}
