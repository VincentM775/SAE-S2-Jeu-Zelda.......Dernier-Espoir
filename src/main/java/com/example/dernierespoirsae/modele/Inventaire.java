package com.example.dernierespoirsae.modele;

import com.example.dernierespoirsae.modele.Armes.Arme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {

    private ObservableList<Arme> listeArmeInventaire;
    private Environnement environnement;

    public Inventaire(Environnement environnement) {
        this.listeArmeInventaire = FXCollections.observableArrayList();
        this.environnement = environnement;
    }

    public Environnement getEnvironnement() {
        return this.environnement;
    }

    public ObservableList<Arme> getListeArmeInventaire() {
        return this.listeArmeInventaire;
    }
}
