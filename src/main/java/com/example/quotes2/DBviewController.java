package com.example.quotes2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DBviewController implements Initializable {
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
    private TableColumn<Quote, Integer> userIDColumn;

    @FXML
    private Button viewMyQ;

    ObservableList<Quote> quotesData = FXCollections.observableArrayList();
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Quote quote = null;
    DatabaseHandler db = new DatabaseHandler();

    public static int currentQuoteId;
    public static int currentQuoteUserId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (HelloController.user.getRole().equals("Guest")) {
            DeleteButton.setDisable(true);
            editButton.setDisable(true);
            addButton.setDisable(true);
            viewMyQ.setDisable(true);
        }
        if (HelloController.user.getRole().equals("User")) {
            DeleteButton.setDisable(true);
            editButton.setDisable(true);
            quotesTable.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if ((HelloController.user.getId() == quotesTable.getSelectionModel().getSelectedItem().userID)) {
                        DeleteButton.setDisable(false);
                        editButton.setDisable(false);
                    } else {
                        DeleteButton.setDisable(true);
                        editButton.setDisable(true);
                    }
                }
            });
        }

        if (HelloController.user.getRole().equals("Superuser")) {
            DeleteButton.setDisable(true);
            editButton.setDisable(true);
        }
        if (HelloController.user.getRole().equals("Verificator")) {
            DeleteButton.setDisable(true);
            editButton.setDisable(true);
            quotesTable.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if ((HelloController.user.getId() == quotesTable.getSelectionModel().getSelectedItem().userID)) {
                        DeleteButton.setDisable(false);
                        editButton.setDisable(false);
                    } else {
                        DeleteButton.setDisable(true);
                        editButton.setDisable(true);
                    }
                }
            });
        }
        try {
            loadData();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

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
        HelloApplication.openNewScene(addButton,"AddQuote.fxml");
    }

    @FXML
    private void editInfo() throws IOException {
        currentQuoteId = quotesTable.getSelectionModel().getSelectedItem().id;
        currentQuoteUserId = quotesTable.getSelectionModel().getSelectedItem().userID;
        Parent parent = FXMLLoader.load(getClass().getResource("editQuote.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
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
                    resultSet.getDate("date"),
                    resultSet.getInt(6)));
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            quoteColumn.setCellValueFactory(new PropertyValueFactory<>("quote"));
            teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
            subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));

            quotesTable.setItems(quotesData);
        }
    }

    @FXML
    private void viewOnlyMine() throws SQLException {
//        if (HelloController.user.getRole().equals("User")) {
//            addButton.setDisable(false);
//            DeleteButton.setDisable(false);
//            editButton.setDisable(false);
//            quotesTable.setOnMousePressed(event -> {
//                if ((HelloController.user.getId() == quotesTable.getSelectionModel().getSelectedItem().id)) {
//                    DeleteButton.setDisable(false);
//                    editButton.setDisable(false);
//                } else {
//                    DeleteButton.setDisable(true);
//                    editButton.setDisable(true);
//
//                }
//            });
//        }

        connection = DriverManager.getConnection("jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1920_quotes",
                "std_1920_quotes", "passwordpassword");

        if (HelloController.user.getRole().equals("User")) {
            query = "SELECT * FROM Quotes WHERE id_user = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, HelloController.user.getId());
        } else if (HelloController.user.getRole().equals("Verificator")) {
            query = "SELECT * FROM Quotes WHERE id_user IN (SELECT id FROM users WHERE study_group = ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, HelloController.user.getStudy_group());
        } else if (HelloController.user.getRole().equals("Superuser")) {
            query = "SELECT * FROM quotes_teachers";
            preparedStatement = connection.prepareStatement(query);
        }
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            String quote = result.getString("quote");
            String teacher = result.getString("teacher");
            String subject = result.getString("subject");
            Date date = result.getDate("date");

            quotesTable.getItems().addAll(new Quote(currentQuoteId, quote, teacher, subject, date, currentQuoteUserId));
        }

        connection.close();

        quoteColumn.setCellValueFactory(new PropertyValueFactory<>("quote"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    @FXML
    public void deleteInfo() throws SQLException {
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
        HelloApplication.openNewScene(BackToMenu,"hello-view.fxml");
    }

    @FXML
    public void viewALLQs() throws SQLException, ClassNotFoundException {
        loadData();
        DeleteButton.setDisable(true);
        editButton.setDisable(true);
        addButton.setDisable(true);
        viewMyQ.setDisable(true);
        quotesData.clear();
    }
}




