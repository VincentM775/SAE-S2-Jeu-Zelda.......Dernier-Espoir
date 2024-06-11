package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Objets.Armes.Projectile;
import javafx.scene.layout.Pane;

public class VueBave extends VueProjectile{

    public VueBave(Pane projectilePane, Projectile projectile) {
        super(projectilePane, projectile);
    }

    @Override
    public String nomFichierVue() {
        return "file:src/main/resources/com/example/dernierespoirsae/images/bave_projectile.png";
    }
}
