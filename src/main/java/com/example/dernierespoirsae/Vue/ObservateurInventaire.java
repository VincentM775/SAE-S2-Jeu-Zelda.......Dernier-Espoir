package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Armes.Arme;
import com.example.dernierespoirsae.modele.Inventaire;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class ObservateurInventaire implements ListChangeListener<Arme> {

    private Pane hache;

    public ObservateurInventaire(Inventaire inventaire, Label compteur) {

    }

    public void onChanged(ListChangeListener.Change<? extends Arme> Arme) {

        while (Arme.next()){
            for(int i = 0; i < Arme.getAddedSize(); i++){
               // new VueInventaire();

            }
            for(int i = 0; i < Arme.getRemovedSize(); i++){
                //new VueInventaire(paneHache, Arme.getRemoved().get(i));
            }
        }
    }
}