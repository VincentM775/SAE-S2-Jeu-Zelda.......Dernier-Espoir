package com.example.dernierespoirsae.Vue;
import com.example.dernierespoirsae.modele.Acteur;
import com.example.dernierespoirsae.modele.Armes.Armes;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ObservateurInventaire implements ListChangeListener<Armes> {

    @FXML
    public Pane armePaneMap;
    @FXML
    private Pane paneHache;
    private Acteur joueur;

    public ObservateurInventaire(Pane paneHache, Acteur joueur, Pane armePaneMap) {
        this.armePaneMap = armePaneMap;
        this.joueur = joueur;
        this.paneHache = paneHache;
    }

    public void onChanged(ListChangeListener.Change<? extends Armes> Arme) {

        while (Arme.next()){
            for(int i = 0; i < Arme.getAddedSize(); i++){

            }
            for(int i = 0; i < Arme.getRemovedSize(); i++){
            }
        }
    }
}