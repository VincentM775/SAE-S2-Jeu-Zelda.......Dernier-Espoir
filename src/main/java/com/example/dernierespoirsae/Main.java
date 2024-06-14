package com.example.dernierespoirsae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static int longeur = 1100;
    public static int largeur = 600;
    @Override
    public void start(Stage stage) throws IOException{
        Image tileset = new Image("file:src/main/resources/com/example/dernierespoirsae/tiles.png");
        double originalWidth = tileset.getWidth();
        double originalHeight = tileset.getHeight();
        System.out.println("Original Width: " + originalWidth); //A LAISSER
        System.out.println("Original Height: " + originalHeight); //A LAISSER

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/dernierespoirsae/vueDernierEspoir.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),longeur,largeur);
//        stage.setMaxHeight(largeur + 37);
//        stage.setMaxWidth(longeur);
        scene.getStylesheets().add(getClass().getResource("dernierEspoir.css").toExternalForm());
        stage.setTitle("Dernier Espoir");
        stage.setScene((scene));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}

