package com.example.dernierespoirsae.Observateur;

import com.example.dernierespoirsae.Vue.VueBalle;
import com.example.dernierespoirsae.Vue.VueBave;
import com.example.dernierespoirsae.Vue.VueCocktailMolotov;
import com.example.dernierespoirsae.modele.Objets.Projectile.Balle;
import com.example.dernierespoirsae.modele.Objets.Projectile.BalleBave;
import com.example.dernierespoirsae.modele.Objets.Projectile.CocktailMolotov;
import com.example.dernierespoirsae.modele.Objets.Projectile.Projectile;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurProjectile implements ListChangeListener<Projectile> {
    private Pane projectilePane;

    public ObservateurProjectile(Pane projectilePane) {
        this.projectilePane = projectilePane;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Projectile> projectile) {

        while (projectile.next()){
            for(int i = 0; i < projectile.getAddedSize(); i++){
                if (projectile.getAddedSubList().get(i) instanceof BalleBave) {
                    new VueBave(projectilePane,projectile.getAddedSubList().get(i));
                }
                if (projectile.getAddedSubList().get(i) instanceof Balle) {
                    new VueBalle(projectilePane,projectile.getAddedSubList().get(i));
                }
                if (projectile.getAddedSubList().get(i) instanceof CocktailMolotov) {
                    new VueCocktailMolotov(projectilePane,projectile.getAddedSubList().get(i));
                }
            }
            for(int i = 0; i < projectile.getRemovedSize(); i++){
                suprimerSprite(projectile.getRemoved().get(i));
            }
        }
    }

    public void suprimerSprite(Projectile projectile){
        this.projectilePane.getChildren().remove(this.projectilePane.lookup("#"+projectile.getId()));
    }

}
