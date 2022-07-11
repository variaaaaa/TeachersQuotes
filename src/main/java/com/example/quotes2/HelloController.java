package com.example.quotes2;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button guestButton;

    @FXML
    private TextField loginField;

    @FXML
    private Button loginsignUpButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label welcomeText;

    public static User user = new User();

    @FXML
    void setAuthSignInButton() {
        String loginText = loginField.getText().trim();
        String loginPassword = passwordField.getText().trim();

        if (!loginText.equals("") && !loginPassword.equals("")) {
            try {
                loginUser(loginText, loginPassword);
            } catch (SQLException | ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Error. ");
        }
    }

    @FXML
    void setLoginsignUpButton() throws IOException {
        HelloApplication.openNewScene(loginsignUpButton,"signup.fxml");
    }


    private void loginUser(String loginText, String loginPassword) throws SQLException, ClassNotFoundException, IOException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);
        dbHandler.getAllQuotes();
        dbHandler.getAllUsers();

        while (result.next()) {
            this.user = user;
            user.setId(result.getInt(1));
            user.setStudy_group(result.getString(4));
            user.setRole(result.getString(5));
            authSignInButton.getScene().getWindow().hide();
            HelloApplication.openNewScene(authSignInButton,"previewQuotes.fxml");
        }
    }

    @FXML
    public void asGuest() throws IOException {
        user.setRole("Guest");
        HelloApplication.openNewScene(guestButton,"previewQuotes.fxml");
    }

}