package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Objets.Projectile.Projectile;
import javafx.beans.binding.Bindings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public abstract class VueProjectile {
    private Pane projectilePane;
    private Pane persoPane;
    private Projectile projectile;
    private Environnement environnement;
    private VueTerrain vueTerrain;
    private TilePane terrainPane;

    public VueProjectile(Pane projectilePane, Projectile projectile, Pane persoPane, Environnement environnement, VueTerrain vueTerrain, TilePane terrainPane) {
        this.projectilePane = projectilePane;
        this.persoPane = persoPane;
        this.projectile = projectile;
        this.environnement=environnement;
        this.vueTerrain = vueTerrain;
        this.terrainPane = terrainPane;
        creerVueProjectile(projectile);
    }
    public void creerVueProjectile(Projectile projectile){
        // Définir la nouvelle image
        Image image = new Image(nomFichierVue());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(10);
        imageView.setFitHeight(15);

        // Lier les propriétés de position
        imageView.translateXProperty().bind(projectile.xProperty());
        imageView.translateYProperty().bind(projectile.yProperty());

        // Lier la propriété de rotation
        // Lier la propriété de rotation
        imageView.rotateProperty().bind(Bindings.createDoubleBinding(() -> projectile.getAngle(), projectile.xProperty(), projectile.yProperty()));

        projectilePane.getChildren().add(imageView);
        imageView.setId(""+projectile.getId());
    }
    public abstract String nomFichierVue();

    public Pane getPersoPane() {
        return persoPane;
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

    public VueTerrain getVueTerrain() {
        return vueTerrain;
    }

    public TilePane getTerrainPane() {
        return terrainPane;
    }
}
