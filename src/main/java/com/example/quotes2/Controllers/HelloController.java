package com.example.quotes2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.example.quotes2.DatabaseHandler;
import com.example.quotes2.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSignInButton;

    @FXML
    private TextField loginField;

    @FXML
    private Button loginsignUpButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label welcomeText;

    @FXML
    void onHelloButtonClick(ActionEvent event) {

    }

    @FXML
    void initialize() {

        authSignInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();

            if (!loginText.equals("") && !loginPassword.equals("")){
                try {
                    loginUser(loginText,loginPassword);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                System.out.println("Error. ");
            }
        });
        loginsignUpButton.setOnAction(event -> {
            openNewScene("signup.fxml");
        });
    }

    private void loginUser(String loginText, String loginPassword) throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(loginPassword);
       // user.setRole(role);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;
        while (result.next()){
            counter++;
        }
        if (counter >= 1){
            openNewScene("forUser.fxml");
        }
    }

    public void openNewScene(String window){
        loginsignUpButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try{
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}