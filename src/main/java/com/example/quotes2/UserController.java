package com.example.quotes2;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Add;

    @FXML
    private Button Delete;

    @FXML
    private Button Edit;

    @FXML
    private Button lookAll;

    @FXML
    private Button lookMine;

    @FXML
    private Label welcomeText;

    @FXML
    void addInfo() {
        System.out.println("added!");

    }

    @FXML
    void deleteInfo(){
        System.out.println("delete?");
    }

}