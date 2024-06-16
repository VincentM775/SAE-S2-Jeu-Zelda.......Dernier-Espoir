package com.example.dernierespoirsae.Observateur;

import com.example.dernierespoirsae.Vue.VueObjetTerrain;
import com.example.dernierespoirsae.Vue.VuePiedDeBiche;
import com.example.dernierespoirsae.Vue.VueTerrain;
import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Objets.AutreObjets.PiedDeBiche;
import com.example.dernierespoirsae.modele.Objets.Objets;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class ObservateurObjets implements ListChangeListener<Objets> {

    private Pane objetPaneMap;
    private Environnement environnement;
    private VueTerrain vueTerrain;
    private Pane terrainPane;

    public ObservateurObjets(Pane armePaneMap, Environnement environnement,VueTerrain vueTerrain,Pane terrainPane) {
        this.objetPaneMap = armePaneMap;
        this.environnement = environnement;
        this.vueTerrain = vueTerrain;
        this.terrainPane = terrainPane;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Objets> objet) {

        while (objet.next()){ //Tant qu'il y a un acteur dans la liste des armes ayant été ajouté/supprimé
            for(int i = 0; i < objet.getAddedSize(); i++){
                //Crée l'affichage de l'objet i ajouté
                new VueObjetTerrain(objet.getAddedSubList().get(i), this.objetPaneMap);
                if (objet.getAddedSubList().get(i) instanceof PiedDeBiche){
                    new VuePiedDeBiche((PiedDeBiche) objet.getAddedSubList().get(i),this.environnement,this.vueTerrain,this.terrainPane);
                }
            }
            for(int i = 0; i < objet.getRemovedSize(); i++){

                //Supprime l'affichage de l'objet
                this.objetPaneMap.getChildren().remove(this.objetPaneMap.lookup("#"+objet.getRemoved().get(i).getId()));
            }
        }
    }
}