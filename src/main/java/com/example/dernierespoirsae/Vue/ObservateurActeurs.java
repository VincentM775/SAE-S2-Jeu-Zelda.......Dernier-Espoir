package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteur;
import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Joueur;
import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
public class ObservateurActeurs implements ListChangeListener<Acteur> {
    private VueActeur vueActeur;
    private Pane persoPane;
    public ObservateurActeurs(Pane pane) {
        this.persoPane = pane;
    }

    @Override
    public void onChanged(Change<? extends Acteur> c) {

        while (c.next()){
            VueActeur vueActeur;
            for(int i = 0; i < c.getAddedSize(); i++){
                vueActeur = new VueActeur(c.getAddedSubList().get(i), persoPane);
            }
            for(int i = 0; i < c.getRemovedSize(); i++){
                suprimerSprite(c.getRemoved().get(i));
            }
        }
    }

    public void suprimerSprite(Acteur acteur){
        this.persoPane.getChildren().remove(this.persoPane.lookup("#"+acteur.getId()));
    }
}
