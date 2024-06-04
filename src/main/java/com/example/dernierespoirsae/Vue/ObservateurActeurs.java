package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteur.Acteur;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurActeurs implements ListChangeListener<Acteur> {
    private VueActeur vueActeur;
    private Acteur acteur;
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
