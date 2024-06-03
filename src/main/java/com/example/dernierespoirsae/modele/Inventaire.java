package com.example.dernierespoirsae.modele;

import com.example.dernierespoirsae.modele.Armes.Arme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class Inventaire {
    private ObservableList<Arme> armes;
    private Acteur joueur;
    public Inventaire(ObservableList<Arme> armes, Acteur joueur) {
        this.armes = FXCollections.observableArrayList();;
        this.joueur = joueur;
    }

    public ObservableList<Arme> getArmes() {
        return armes;
    }

    public boolean existe(Arme arme){

        for(int i = 0 ; i< armes.size(); i++){

            if(armes.get(i).getType().equals(arme.getType())){
                return true;
            }
        }
        return false;
    }

}
