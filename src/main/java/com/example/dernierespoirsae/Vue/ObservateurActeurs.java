package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteur;
import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Joueur;
import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ObservateurActeurs implements ListChangeListener<Acteur> {
    private VueActeur vueActeur;
    private Acteur acteur;
    private Pane persoPane;
    private HBox barreVieBox;

    public ObservateurActeurs(Pane pane, HBox barreVieBox) {
        this.persoPane = pane;
        this.barreVieBox = barreVieBox;
    }

    @Override
    public void onChanged(Change<? extends Acteur> c) {

        while (c.next()){
            for(int i = 0; i < c.getAddedSize(); i++){
                new VueActeur(c.getAddedSubList().get(i), persoPane, barreVieBox);
            }

            for(int i = 0; i < c.getRemovedSize(); i++){
                suprimerSprite(c.getRemoved().get(i));
                persoPane.getChildren().remove(this.persoPane.lookup("#"+c.getRemoved().get(i).getId())); //supprime la barre de vie

            }
        }
    }

    public void suprimerSprite(Acteur acteur){
        this.persoPane.getChildren().remove(this.persoPane.lookup("#"+acteur.getId()));
    }
}
