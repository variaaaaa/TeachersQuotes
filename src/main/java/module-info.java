module com.example.quotes2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.quotes2 to javafx.fxml;
    exports com.example.quotes2;
    exports com.example.quotes2.Controllers;
    opens com.example.quotes2.Controllers to javafx.fxml;
}