package com.example.quotes2;

import java.sql.SQLException;
import java.util.ArrayList;

public class User {

    private String login,password,role;
    private int id = -1;

    public User(int id,String login,String password, String role) {
        this.login = login;
        this.password = password;
        this.id = id;
        this.role = role;
    }
    public User(){

    }

//    public static ArrayList<User> getAllusers(){
//        try {
//            return DatabaseHandler.getAllusers();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public void save(){
//        if (this.id == -1 ){
//            try {
//               // id =  DatabaseHandler.createUser(this);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }else {
//            try {
//                //DatabaseHandler.updateuser(this);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
 //   }

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

    public void setId(int id) {
        this.id = id;
    }
}
