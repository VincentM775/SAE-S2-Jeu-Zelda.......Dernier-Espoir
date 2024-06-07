package com.example.dernierespoirsae.modele;

import com.example.dernierespoirsae.Observateur.ObservateurInventaire;
import com.example.dernierespoirsae.modele.Armes.Arme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

public class Inventaire {

    private ObservableList<Arme> armes;

    public Inventaire(VBox inventairePane) {
        this.armes = FXCollections.observableArrayList();
        ObservateurInventaire observateurInventaire =new ObservateurInventaire(inventairePane, this);
        armes.addListener(observateurInventaire);
    }

    public boolean exisite(Arme arme){
        boolean existe = false;
        if(this.armes.contains(arme)){
            System.out.println("caca");
            existe = true;
        }
        return existe;
    }

    public ObservableList<Arme> getArmes() {
        return armes;
    }
}
