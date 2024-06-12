package com.example.dernierespoirsae.Observateur;

import com.example.dernierespoirsae.Vue.VueObjetTerrain;
import com.example.dernierespoirsae.modele.Objets.Objets;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurObjets implements ListChangeListener<Objets> {

    private Pane objetPaneMap;

    public ObservateurObjets(Pane armePaneMap) {
        this.objetPaneMap = armePaneMap;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Objets> objet) {

        while (objet.next()){ //Tant qu'il y a un acteur dans la liste des armes ayant été ajouté/supprimé
            for(int i = 0; i < objet.getAddedSize(); i++){
                //Crée l'affichage de l'objet i ajouté
                new VueObjetTerrain(objet.getAddedSubList().get(i), this.objetPaneMap);
            }
            for(int i = 0; i < objet.getRemovedSize(); i++){

                //Supprime l'affichage de l'objet
                this.objetPaneMap.getChildren().remove(this.objetPaneMap.lookup("#"+objet.getRemoved().get(i).getId()));
            }
        }
    }
}