package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Objets.Armes.Projectile;
import javafx.scene.layout.Pane;

public class VueBalle extends VueProjectile {


    public VueBalle(Pane projectilePane, Projectile projectile) {
        super(projectilePane, projectile);
    }

    @Override
    public String nomFichierVue() {
        return "file:src/main/resources/com/example/dernierespoirsae/images/bullet.png";
    }
}
