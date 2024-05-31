package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteur;
import com.example.dernierespoirsae.modele.Joueur;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VueActeur {
    private Pane persoPane;

    private Acteur acteur;

    public VueActeur(Acteur acteur, Pane persoPane) {
        this.persoPane = persoPane;
        this.acteur = acteur;
        creerSprite(acteur);
        creerBarreVie(acteur);
    }

    public void creerSprite(Acteur acteur){

            if(acteur instanceof Joueur){
                Rectangle rectangle = new Rectangle(15, 15);
                rectangle.setFill(Color.BLUE);
                rectangle.translateXProperty().bind(acteur.xProperty());
                rectangle.translateYProperty().bind(acteur.yProperty());
                persoPane.getChildren().add(rectangle);
                rectangle.setId(""+acteur.getId());
            }
            else {
                Rectangle rectangle = new Rectangle(15, 15);
                rectangle.setFill(Color.BLACK);
                rectangle.translateXProperty().bind(acteur.xProperty());
                rectangle.translateYProperty().bind(acteur.yProperty());
                persoPane.getChildren().add(rectangle);
                rectangle.setId(""+acteur.getId());
            }
        }

    public void creerBarreVie(Acteur acteur){
        ProgressBar barreVie = new ProgressBar();
        barreVie.setId(String.valueOf(acteur.getId()));
        barreVie.getStyleClass().add("health-bar");
        barreVie.setProgress(1);
        barreVie.translateXProperty().bind(acteur.xProperty());
        barreVie.translateYProperty().bind(acteur.yProperty().subtract(30));
        barreVie.progressProperty().bind(acteur.vieProperty().divide(100));
        System.out.println();
        persoPane.getChildren().add(barreVie);
    }


}
