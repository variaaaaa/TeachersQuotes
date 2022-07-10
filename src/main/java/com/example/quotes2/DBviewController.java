package com.example.quotes2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DBviewController implements Initializable {
    ObservableList<Quote>  quotesDB = FXCollections.observableArrayList();



    @FXML
    private TableView<Quote> quotesTable;

    @FXML
    private TableColumn<Quote, Integer> id;

    @FXML
    private TableColumn<Quote, String> quote;

    @FXML
    private TableColumn<Quote, String> teacher;

    @FXML
    private TableColumn<Quote, String> subject;

    @FXML
    private TableColumn<Quote, Date> date;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Quote quoteobj = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // loadDate();
    }

    public void loadDate() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbhandler = new DatabaseHandler();
        dbhandler.makeConnection();
        QuotesDB quotesDB1 = dbhandler.getAllQuotes();
        // создаем список объектов
        ObservableList<Quote>  quotesDB = FXCollections.observableArrayList();
        quotesDB.add(quotesDB1);

        
        // определяем таблицу и устанавливаем данные
        TableView<Quote> table = new TableView<Quote>(quotesDB);


        // столбец для вывода имени
        TableColumn<Quote, Integer> idColumn = new TableColumn<Quote, Integer  >("id");
        // определяем фабрику для столбца с привязкой к свойству name
        idColumn.setCellValueFactory(new PropertyValueFactory<Quote, Integer>("id"));
        // добавляем столбец
        table.getColumns().add(idColumn);

        // столбец для вывода возраста
        TableColumn<Quote, Date> quoteColumn = new TableColumn<>("Quote");
        quoteColumn.setCellValueFactory(new PropertyValueFactory<>("Quote"));
        table.getColumns().add(quoteColumn);

        TableColumn<Quote, String> teacherColumn = new TableColumn<Quote, String>("Teacher");
        quoteColumn.setCellValueFactory(new PropertyValueFactory<>("Teacher"));
        table.getColumns().add(teacherColumn);

        TableColumn<Quote, String> subjectColumn = new TableColumn<Quote, String>("Subject");
        quoteColumn.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        table.getColumns().add(subjectColumn);

        TableColumn<Quote, Date> dateColumn = new TableColumn<Quote, Date>("Date");
        quoteColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        table.getColumns().add(dateColumn);




//        DatabaseHandler dbhandler = new DatabaseHandler();
//        dbhandler.makeConnection();
//
//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
//
//        quote.setCellValueFactory(new PropertyValueFactory<>("quote"));
//        teacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
//        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
//        date.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

//    @FXML
//    private void viewAllQuotes() {
//        try {
//            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("previewQuotes.fxml")));
//            Scene scene = new Scene(parent);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException ex) {
//        }
//
//    }

    @FXML
    private void refreshTable() throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        QuotesDB forRefresh = databaseHandler.getAllQuotes();
//        quotesTable.setItems((ObservableList<Quote>) forRefresh);

        query = "SELECT * FROM `Quotes`";
        connection = DriverManager.getConnection("jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1920_quotes",
                "std_1920_quotes", "passwordpassword");
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            quotesDB.add(new Quote(resultSet.getInt("id"),
                    resultSet.getString("quote"),
                    resultSet.getString("teacher"),
                    resultSet.getString("subject"),
                    resultSet.getDate("date")));
            //quotesTable.setItems(quotesDB);
            quotesTable.getColumns().add(id);
            quotesTable.getColumns().add(quote);
            quotesTable.getColumns().add(teacher);
            quotesTable.getColumns().add(subject);
            quotesTable.getColumns().add(date);
        }



    }


}
