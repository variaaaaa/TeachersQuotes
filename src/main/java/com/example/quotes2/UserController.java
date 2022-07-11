package com.example.quotes2;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
    void addQuote() throws IOException {
        Add.getScene().getWindow().hide();
        HelloApplication.openNewScene("AddQuote.fxml");
    }

    @FXML
    void deleteInfo(){
        System.out.println("delete?");
    }

    @FXML
    public void viewALlQs() throws IOException {
        HelloApplication.openNewScene("previewQuotes.fxml");
    }

    @FXML
    public void viewMyQs() throws IOException {
        HelloApplication.openNewScene("myQuotes.fxml");
    }

}