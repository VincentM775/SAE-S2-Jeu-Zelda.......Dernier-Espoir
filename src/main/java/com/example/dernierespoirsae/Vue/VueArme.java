package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Armes.Arme;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public abstract class VueArme {
    private Arme arme;

    public VueArme(Arme arme) {
        this.arme = arme;
    }

    public void creeViewArme() {

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
        setPotitionArme(imageView);

        //Ajoute l'image a la Pane armePane qui sera affiché dans la fenêtre
        ajoutImagePane(imageView);
    }

    public abstract void ajoutImagePane(ImageView imageView);
    public abstract void setPotitionArme(ImageView imageView);

    public Arme getArme() {
        return arme;
    }
}
