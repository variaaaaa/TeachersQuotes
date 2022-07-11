package com.example.quotes2;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class editQuote {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addQuoteButton;

    @FXML
    private DatePicker dataField;

    @FXML
    private TextField quoteField;

    @FXML
    private TextField quoteField1;

    @FXML
    private TextField subjectField;

    @FXML
    private TextField teacherField;

    @FXML
    private Label welcomeText;

    PreparedStatement preparedStatement;

    @FXML
    public void updateQuote() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1920_quotes",
                "std_1920_quotes", "passwordpassword");

        String query = "UPDATE Quotes SET quote =?, teacher=?, subject=?, date=? WHERE id LIKE "+  DBviewController.currentQuoteId;
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, quoteField.getText());
        preparedStatement.setString(2, teacherField.getText());
        preparedStatement.setString(3, subjectField.getText());
        preparedStatement.setDate(4,Date.valueOf(dataField.getValue()));
        preparedStatement.execute();
    }
}

