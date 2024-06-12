package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Objets.Projectile.Projectile;
import javafx.scene.layout.Pane;

public class VueCocktailMolotov extends VueProjectile {

    public VueCocktailMolotov(Pane projectilePane, Projectile projectile) {
        super(projectilePane, projectile);
    }

    @Override
    public String nomFichierVue() {
        return "file:src/main/resources/com/example/dernierespoirsae/images/cocktailMolotov.png";
    }
}
