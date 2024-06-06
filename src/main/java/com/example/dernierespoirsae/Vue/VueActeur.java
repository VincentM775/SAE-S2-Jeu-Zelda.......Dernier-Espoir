package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteurs.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VueActeur {
    private Pane persoPane, barreViePane;

    public VueActeur(Pane persoPane, Acteur acteur ,Pane barreViePane) {
        this.persoPane = persoPane;
        this.barreViePane = barreViePane;
        creerRectangle(acteur);
        creerBarreVie(acteur);
    }

    public void creerRectangle(Acteur acteur){

        Rectangle rectangle = new Rectangle(15, 15);
        rectangle.translateXProperty().bind(acteur.xProperty());
        rectangle.translateYProperty().bind(acteur.yProperty());
        rectangle.setId(String.valueOf(acteur.getId()));
        persoPane.getChildren().add(rectangle);

        //Modifie la couleur du rectangle
        if(acteur instanceof Joueur){
            rectangle.setFill(Color.BLUE);

        }
        else if(acteur instanceof Ennemi){
            rectangle.setFill(Color.BLACK);
        }
    }

    public void creerBarreVie(Acteur acteur) {

        ProgressBar barreVie = new ProgressBar();

        NumberBinding progressBinding = Bindings.createDoubleBinding(
                () -> acteur.getVieProperty().get() / (double) acteur.getMaxVie(),
                acteur.getVieProperty(), acteur.maxVie()
        );

        barreVie.progressProperty().bind(progressBinding);

        barreVie.setId("barre"+acteur.getId());

        if (acteur instanceof Ennemi) {

            barreVie.getStyleClass().add("health-bar-ennemi");
            barreVie.translateXProperty().bind(acteur.xProperty().subtract(20 - acteur.getHitBox().getLongueur() / 2));
            barreVie.translateYProperty().bind(acteur.yProperty().subtract(15));
            persoPane.getChildren().add(barreVie);

        }
        else if(acteur instanceof Joueur) {

            barreVie.getStyleClass().add("health-bar-joueur");
            this.barreViePane.getChildren().add(barreVie);
            barreVie.translateXProperty().setValue(0);
            barreVie.translateYProperty().setValue(0);
        }
    }
}
