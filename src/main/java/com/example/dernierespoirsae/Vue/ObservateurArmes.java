package com.example.dernierespoirsae.Vue;
import com.example.dernierespoirsae.modele.Acteur;
import com.example.dernierespoirsae.modele.Armes.Arme;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class ObservateurArmes implements ListChangeListener<Arme> {

    @FXML
    public Pane armePaneMap;
    @FXML
    private Pane hache;
    private Acteur joueur;

    public ObservateurArmes(Pane paneHache, Acteur joueur, Pane armePaneMap) {
        this.armePaneMap = armePaneMap;
        this.joueur = joueur;
        this.hache = hache;
    }

    public void onChanged(ListChangeListener.Change<? extends Arme> Arme) {

        while (Arme.next()){
            for(int i = 0; i < Arme.getAddedSize(); i++){
                new VueArmes(hache, Arme.getAddedSubList().get(i), this.joueur, this.joueur.getInventaire(), armePaneMap);
            }
            for(int i = 0; i < Arme.getRemovedSize(); i++){
                new VueArmes(Arme.getRemoved().get(i), this.joueur, hache, armePaneMap);

            }
        }
    }
}