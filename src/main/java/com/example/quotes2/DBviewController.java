package com.example.quotes2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DBviewController implements Initializable {
    ObservableList<Quote> quotesData = FXCollections.observableArrayList();


    @FXML
    private Button addQuote;

    @FXML
    private Button Update;
    @FXML
    private TableView<Quote> quotesTable;

    @FXML
    private TableColumn<Quote, Integer> idColumn;

    @FXML
    private TableColumn<Quote, String> quoteColumn;

    @FXML
    private TableColumn<Quote, String> teacherColumn;

    @FXML
    private TableColumn<Quote, String> subjectColumn;

    @FXML
    private TableColumn<Quote, Date> dateColumn;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet;
    Quote quoteobj = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadData();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadData() throws SQLException, ClassNotFoundException {
        connection = DriverManager.getConnection("jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1920_quotes",
                "std_1920_quotes", "passwordpassword");
        updatingInfo();
    }

    @FXML
    private void refreshTable() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1920_quotes",
                "std_1920_quotes", "passwordpassword");
        quotesData.clear();
        updatingInfo();
    }

    private void AddQuote() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1920_quotes",
                "std_1920_quotes", "passwordpassword");


    }

    private void updatingInfo() throws SQLException {
        query = "SELECT * FROM `Quotes`";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            quotesData.add(new Quote(resultSet.getInt("id"),
                    resultSet.getString("quote"),
                    resultSet.getString("teacher"),
                    resultSet.getString("subject"),
                    resultSet.getDate("date")));
            quoteColumn.setCellValueFactory(new PropertyValueFactory<>("quote"));
            teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
            subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

            quotesTable.setItems(quotesData);
        }
    }

    private void viewOnlyMine(){}

}




