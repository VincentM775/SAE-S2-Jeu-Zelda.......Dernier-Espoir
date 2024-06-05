package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteurs.*;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VueActeur {
    private Pane persoPane;

    public VueActeur(Pane persoPane, Acteur acteur) {
        this.persoPane = persoPane;
        creerRectangle(acteur);
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
}
