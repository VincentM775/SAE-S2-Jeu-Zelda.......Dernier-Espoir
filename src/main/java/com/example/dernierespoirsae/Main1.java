package com.example.dernierespoirsae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Main1 extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main1.class.getResource("vueDernierEspoir.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1001,601);
        stage.setTitle("Dernier Espoir");
        stage.setScene((scene));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

