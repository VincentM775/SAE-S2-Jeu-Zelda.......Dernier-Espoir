package com.example.dernierespoirsae.modele;

import com.example.dernierespoirsae.modele.Armes.Arme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {

    private ObservableList<Arme> armes;

    public Inventaire() {
        this.armes = FXCollections.observableArrayList();
    }

    public ObservableList<Arme> getArmes() {
        return armes;
    }
}
