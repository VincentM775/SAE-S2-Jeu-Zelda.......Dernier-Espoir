package com.example.dernierespoirsae.modele;

import com.example.dernierespoirsae.modele.Objets.Armes.Arme;
import com.example.dernierespoirsae.modele.Objets.Objets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {

    private ObservableList<Objets> listeObjetsInventaire;
    private Environnement environnement;

    public Inventaire(Environnement environnement) {
        this.listeObjetsInventaire = FXCollections.observableArrayList();
        this.environnement = environnement;
    }

    public Environnement getEnvironnement() {
        return this.environnement;
    }

    public ObservableList<Objets> getListeObjetsInventaire() {
        return this.listeObjetsInventaire;
    }
}
