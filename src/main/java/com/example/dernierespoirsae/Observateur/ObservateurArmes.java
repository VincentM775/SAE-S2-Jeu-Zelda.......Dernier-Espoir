package com.example.dernierespoirsae.Observateur;

import com.example.dernierespoirsae.Vue.VueArmeTerrain;
import com.example.dernierespoirsae.modele.Objets.Armes.Arme;
import com.example.dernierespoirsae.modele.Objets.Objets;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurArmes implements ListChangeListener<Objets> {

    private Pane armePaneMap;

    public ObservateurArmes(Pane armePaneMap) {
        this.armePaneMap = armePaneMap;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Objets> objet) {

        while (objet.next()){ //Tant qu'il y a un acteur dans la liste des armes ayant été ajouté/supprimé
            for(int i = 0; i < objet.getAddedSize(); i++){
                //Crée l'affichage de l'arme i ajouté
                new VueArmeTerrain(objet.getAddedSubList().get(i), this.armePaneMap);
            }
            for(int i = 0; i < objet.getRemovedSize(); i++){

                //Supprime l'affiche de l'arme
                this.armePaneMap.getChildren().remove(this.armePaneMap.lookup("#"+objet.getRemoved().get(i).getId()));
            }
        }
    }
}