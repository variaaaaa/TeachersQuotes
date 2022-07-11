package com.example.quotes2;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddQuote {

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
    private TextField subjectField;

    @FXML
    private TextField teacherField;

    @FXML
    private Label welcomeText;
    @FXML
    public void setQuote() throws SQLException, ClassNotFoundException, IOException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        int id = dbHandler.QID();
        String quote = quoteField.getText();
        String teacher = teacherField.getText();
        String subject = subjectField.getText();
        Date date = Date.valueOf(dataField.getValue());
        int userID = HelloController.user.getId();

        Quote newQuote = new Quote(id, quote, teacher, subject, date, userID);
        dbHandler.addQuote(newQuote);
        HelloApplication.openNewScene(addQuoteButton,"previewQuotes.fxml");
    }
}


