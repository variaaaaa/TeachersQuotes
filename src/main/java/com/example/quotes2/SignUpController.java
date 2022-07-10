package com.example.quotes2;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField passwordsuField;

    @FXML
    private TextField role;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signupField;

    @FXML
    private Label welcomeText;

    @FXML
    void setSignUpButton() {
        try {
            signUpNewUser();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void signUpNewUser() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String login = signupField.getText();
        String password = passwordsuField.getText();
        String role = this.role.getText();
        int id = dbHandler.MyID();

        User user = new User(id, login,password,role);
        dbHandler.signUpUser(user);
    }

}
