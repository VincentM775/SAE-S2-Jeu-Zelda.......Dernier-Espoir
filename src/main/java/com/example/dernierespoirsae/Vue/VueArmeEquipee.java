package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteurs.Joueur;
import com.example.dernierespoirsae.modele.Armes.Arme;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueArmeEquipee extends VueArme{

    private Joueur joueur;
    private  Pane armePaneEquipee;

    public VueArmeEquipee(Arme arme, Joueur joueur, Pane armePaneEquipee) {
        super(arme);
        this.joueur = joueur;
        this.armePaneEquipee = armePaneEquipee;
        creeViewArme();
    }

    @Override
    public void setPotitionArme(ImageView imageView){
        imageView.xProperty().bind(joueur.xProperty());
        imageView.yProperty().bind(joueur.yProperty());
    }

    @Override
    public void ajoutImagePane(ImageView imageView){
        if(this.armePaneEquipee.getChildren().size()==1){
            this.armePaneEquipee.getChildren().remove(0);
        }
        this.armePaneEquipee.getChildren().add(imageView);
    }
}