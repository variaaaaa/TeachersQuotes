package com.example.quotes2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DBviewController implements Initializable {
    ObservableList<Quote> quotesData = FXCollections.observableArrayList();
    @FXML
    private Button DeleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button BackToMenu;

    @FXML
    private Button Update;

    @FXML
    private Button addButton;

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

    @FXML
    private Button viewMyQ;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet;


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

    @FXML
    private void AddQuote() throws IOException {
        HelloApplication.openNewScene("AddQuote.fxml");
    }

    @FXML
    private void DeleteQuote() throws IOException {
        HelloApplication.openNewScene("DeleteQuote.fxml");
    }

    private void updatingInfo() throws SQLException {
        query = "SELECT * FROM `Quotes`";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            quotesData.add(new Quote(resultSet.getInt(1),
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

    @FXML
    private void viewOnlyMine() {
        if (HelloController.user.getRole().equals("User")) {
            addButton.setDisable(false);
            DeleteButton.setDisable(false);
            editButton.setDisable(false);
            quotesTable.setOnMousePressed(event -> {
                if ((HelloController.user.getId() == quotesTable.getSelectionModel().getSelectedItem().id)) {
                    DeleteButton.setDisable(false);
                    editButton.setDisable(false);
                } else {
                    DeleteButton.setDisable(true);
                    editButton.setDisable(true);

                }
            });
        }
    }

    @FXML // -- Удалить выделенный элемент
    public void deleteInfo() throws SQLException, ClassNotFoundException {
        connection = DriverManager.getConnection("jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1920_quotes",
                "std_1920_quotes", "passwordpassword");

        int id = quotesTable.getSelectionModel().getSelectedItem().id;
        query = "DELETE FROM Quotes WHERE Quotes.id =" + id;
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
        refreshTable();
        System.out.println("Success!");
    }

    @FXML
    public void backToMenu() throws IOException {
        BackToMenu.getScene().getWindow().hide();
        HelloApplication.openNewScene("hello-view.fxml");
    }
}




