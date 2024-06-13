package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Objets.Projectile.Projectile;
import javafx.beans.binding.Bindings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class VueProjectile {
    private Pane projectilePane;

    public VueProjectile(Pane projectilePane, Projectile projectile) {
        this.projectilePane = projectilePane;
        creerVueProjectile(projectile);
    }
    public void creerVueProjectile(Projectile projectile){
        // Définir la nouvelle image
        Image image = new Image(nomFichierVue());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(10);
        imageView.setFitHeight(15);

        // Lier les propriétés de position
        imageView.translateXProperty().bind(projectile.getxProperty());
        imageView.translateYProperty().bind(projectile.getyProperty());

        // Lier la propriété de rotation
        // Lier la propriété de rotation
        imageView.rotateProperty().bind(Bindings.createDoubleBinding(() -> projectile.getAngle(), projectile.getxProperty(), projectile.getyProperty()));

        projectilePane.getChildren().add(imageView);
        imageView.setId(""+projectile.getId());
    }
    public abstract String nomFichierVue();

}
