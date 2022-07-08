package com.example.quotes2.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.quotes2.DatabaseHandler;
import com.example.quotes2.User;
import javafx.event.ActionEvent;
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
    void onHelloButtonClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        signUpButton.setOnAction(event -> {
            try {
                signUpNewUser();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void signUpNewUser() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String login = signupField.getText();
        String password = passwordsuField.getText();
        String role = this.role.getText();
        int id = DatabaseHandler.getId();


        User user = new User(id, login,password,role);
        dbHandler.signUpUser(user);
    }

}
