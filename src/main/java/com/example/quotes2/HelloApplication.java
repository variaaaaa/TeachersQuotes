package com.example.quotes2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage.setTitle("Teachers' quotes");
        stage.setScene(new Scene(root, 300,400) );
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void openNewScene(Button button, String window) throws IOException {
        button.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(HelloApplication.class.getResource(window));
        stage.setScene(new Scene(root));
        stage.setTitle("Teachers' quotes");
        stage.show();
    }
}