package com.example.quotes2;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
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
    public void setQuote() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        int id = dbHandler.QID();
        String quote = quoteField.getText();
        String teacher = teacherField.getText();
        String subject = subjectField.getText();
        Date date = Date.valueOf(dataField.getValue());

        Quote newQuote = new Quote(id, quote, teacher, subject, date);
        dbHandler.addQuote(newQuote);
        User user = new User();

    }

    @FXML
    void initialize() {

    }

}


