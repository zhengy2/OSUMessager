package com.example.ramak.myapplication;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ramak on 10/17/2017.
 */

public class User {

    private String userId;
    private String userName;
    private String userPhone;
    private String userMajor;
    private String userYear;
    private Date userDOB;
    private String password;

    public User(String userId, String userName, String userPhone, String userMajor, String userYear, Date userDOB, String password) {
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userMajor = userMajor;
        this.userYear = userYear;
        this.userDOB = userDOB;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserMajor() {
        return userMajor;
    }

    public void setUserMajor(String userMajor) {
        this.userMajor = userMajor;
    }

    public String getUserYear() {
        return userYear;
    }

    public void setUserYear(String userYear) {
        this.userYear = userYear;
    }

    public Date getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(Date userDOB) {
        this.userDOB = userDOB;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
