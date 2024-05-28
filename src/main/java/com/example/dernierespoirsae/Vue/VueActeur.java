package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteur;
import com.example.dernierespoirsae.modele.Joueur;
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
}
