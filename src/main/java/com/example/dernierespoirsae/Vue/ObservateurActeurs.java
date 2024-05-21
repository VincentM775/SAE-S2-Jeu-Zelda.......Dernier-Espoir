package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteur;
import com.example.dernierespoirsae.modele.Joueur;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class ObservateurActeurs implements ListChangeListener<Acteur> {

    private Pane persoPane;

    public ObservateurActeurs(Pane pane) {
        this.persoPane = pane;

    }

    @Override
    public void onChanged(Change<? extends Acteur> c) {
        while (c.next()){
            for(int i = 0; i < c.getAddedSize(); i++){
                creerSprite(c.getAddedSubList().get(i));
            }
            for(int i = 0; i < c.getRemovedSize(); i++){
                suprimerSprite(c.getRemoved().get(i));
            }
        }
    }

    public void creerSprite(Acteur acteur){
        if (acteur instanceof Joueur){
            Circle cercle = new Circle(10 );
            cercle.setFill(Color.BLUE);
            cercle.translateXProperty().bind(acteur.xProperty());
            cercle.translateYProperty().bind(acteur.yProperty());
            persoPane.getChildren().add(cercle);
            cercle.setId(""+acteur.getId());
        }
        else{
            Circle cercle = new Circle(8 );
            cercle.setFill(Color.RED);
            cercle.translateXProperty().bind(acteur.xProperty());
            cercle.translateYProperty().bind(acteur.yProperty());
            persoPane.getChildren().add(cercle);
            cercle.setId(""+acteur.getId());
        }
    }

    public void suprimerSprite(Acteur acteur){
        this.persoPane.getChildren().remove(this.persoPane.lookup("#"+acteur.getId()));

    }
}
