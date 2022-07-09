package com.example.quotes2;

import java.util.ArrayList;

public class Users {

    private ArrayList<User> users;


    public Users() {
        this.users = new ArrayList<User>();

    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        users.add(user);
    }

}
