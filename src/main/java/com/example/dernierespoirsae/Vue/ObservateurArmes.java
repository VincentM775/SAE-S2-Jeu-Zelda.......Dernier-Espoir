package com.example.dernierespoirsae.Vue;
import com.example.dernierespoirsae.modele.Armes.Arme;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class ObservateurArmes implements ListChangeListener<Arme> {

    @FXML
    public Pane armePaneMap;

    public ObservateurArmes(Pane armePaneMap) {
        this.armePaneMap = armePaneMap;
    }

    public void onChanged(ListChangeListener.Change<? extends Arme> Arme) {

        while (Arme.next()){
            for(int i = 0; i < Arme.getAddedSize(); i++){
                new VueArmes( Arme.getAddedSubList().get(i), armePaneMap);
            }
            for(int i = 0; i < Arme.getRemovedSize(); i++){
                this.armePaneMap.getChildren().remove(this.armePaneMap.lookup("#"+Arme.getRemoved().get(i).getId()));
            }
        }
    }
}