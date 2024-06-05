package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteur;
import com.example.dernierespoirsae.modele.Ennemi;
import com.example.dernierespoirsae.modele.Joueur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VueActeur {
    private Pane persoPane;

    public VueActeur(Pane persoPane, Acteur acteur) {
        this.persoPane = persoPane;
        creerSprite(acteur);
    }

    public void creerSprite(Acteur acteur){

        Rectangle rectangle = new Rectangle(15, 15);
        rectangle.translateXProperty().bind(acteur.xProperty());
        rectangle.translateYProperty().bind(acteur.yProperty());
        persoPane.getChildren().add(rectangle);
        rectangle.setId(String.valueOf(acteur.getId()));


        if(acteur instanceof Joueur){
            rectangle.setFill(Color.BLUE);
        } else if(acteur instanceof Ennemi){
            rectangle.setFill(Color.BLACK);
        }
    }
}
