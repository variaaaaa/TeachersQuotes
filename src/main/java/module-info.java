module com.example.quotes2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.quotes2 to javafx.fxml;
    exports com.example.quotes2;
}