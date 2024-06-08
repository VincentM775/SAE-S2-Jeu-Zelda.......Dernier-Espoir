package com.example.dernierespoirsae.Observateur;

import com.example.dernierespoirsae.Vue.VueArmes;
import com.example.dernierespoirsae.modele.Acteurs.Joueur;
import com.example.dernierespoirsae.modele.Armes.Arme;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurArmes implements ListChangeListener<Arme> {

    private Pane armePaneMap;
    private Joueur joueur;

    public ObservateurArmes(Pane armePaneMap, Joueur joueur) {
        this.armePaneMap = armePaneMap;
        this.joueur = joueur;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Arme> Arme) {

        while (Arme.next()){ //Tant qu'il y a un acteur dans la liste des armes ayant été ajouté/supprimé
            for(int i = 0; i < Arme.getAddedSize(); i++){

                //Crée l'affichage de l'arme i ajouté
                new VueArmes(Arme.getAddedSubList().get(i), armePaneMap, joueur);
            }
            for(int i = 0; i < Arme.getRemovedSize(); i++){

                //Supprime l'affiche de l'arme
                this.armePaneMap.getChildren().remove(this.armePaneMap.lookup("#"+Arme.getRemoved().get(i).getId()));
            }
        }
    }
}