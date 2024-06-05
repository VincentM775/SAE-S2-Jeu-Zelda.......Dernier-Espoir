package com.example.dernierespoirsae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vueDernierEspoir.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1100,600);
        stage.setTitle("Dernier Espoir");
        stage.setScene((scene));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

