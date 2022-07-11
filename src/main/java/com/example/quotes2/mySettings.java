package com.example.quotes2;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class mySettings {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addQuoteButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField groupField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label welcomeText;
    PreparedStatement preparedStatement;
    @FXML
    public void updateQuote() throws SQLException, IOException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1920_quotes",
                "std_1920_quotes", "passwordpassword");

        String query = "UPDATE Users SET login =?, password=?, study_group=? WHERE id LIKE "+  HelloController.user.getId();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, loginField.getText());
        preparedStatement.setString(2, passwordField.getText());
        preparedStatement.setString(3, groupField.getText());
        preparedStatement.execute();

        HelloApplication.openNewScene(addQuoteButton, "previewQuotes.fxml");
    }

}
