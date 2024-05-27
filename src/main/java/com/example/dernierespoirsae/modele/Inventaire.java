package com.example.dernierespoirsae.modele;

import com.example.dernierespoirsae.modele.Armes.Armes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {
    private ObservableList<Armes> armes;
    private Acteur joueur;
    public Inventaire(ObservableList<Armes> armes, Acteur joueur) {
        this.armes = FXCollections.observableArrayList();;
        this.joueur = joueur;
    }

    public ObservableList<Armes> getArmes() {
        return armes;
    }
}
