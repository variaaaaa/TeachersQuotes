package com.example.quotes2;

import java.sql.SQLException;
import java.util.ArrayList;

public class User {

    private String login,password,role, study_group;
    private int id = -1;

    public User(int id,String login,String password, String role, String study_group) {
        this.login = login;
        this.password = password;
        this.id = id;
        this.role = role;
        this.study_group = study_group;
    }
    public User(){

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getStudy_group() {
        return study_group;
    }

    public void setStudy_group(String study_group) {
        this.study_group = study_group;
    }

    public void setId(int id) {
        this.id = id;
    }
}
