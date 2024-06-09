package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteurs.Joueur;
import com.example.dernierespoirsae.modele.Armes.Arme;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueArmeEquipee {
    private Arme arme;
    private Pane armePaneEquipee;
    private Joueur joueur;
    public VueArmeEquipee(Arme arme, Pane armePaneEquipee, Joueur joueur) {
        this.arme = arme;
        this.armePaneEquipee = armePaneEquipee;
        this.joueur = joueur;
        creeViewArmeEquipe();
    }

    public void creeViewArmeEquipe() {

        //Recuperation de l'image de l'arme a afficher
        Image imageArme = new Image("file:src/main/resources/com/example/dernierespoirsae/images/"+arme.getType()+".png");

        //Ajout de cette image a imageView pour pouvoir l'afficher
        ImageView imageView = new ImageView(imageArme);

        //Definition des dimentions
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        //Associe l'ID de l'arme réprensetée à l'image
        imageView.setId(String.valueOf(this.arme.getId()));

        //Associe la position de l'arme a l'image
        imageView.xProperty().bind(joueur.xProperty());
        imageView.yProperty().bind(joueur.yProperty());

        //Ajoute l'image a la Pane armePane qui sera affiché dans la fenêtre
        if(this.armePaneEquipee.getChildren().size()==1){
            this.armePaneEquipee.getChildren().remove(0);
        }
        this.armePaneEquipee.getChildren().add(imageView);
    }
}