package com.example.dernierespoirsae.Observateur;

import com.example.dernierespoirsae.Vue.VueBalle;
import com.example.dernierespoirsae.Vue.VueBave;
import com.example.dernierespoirsae.modele.Armes.Balle;
import com.example.dernierespoirsae.modele.Armes.BalleBave;
import com.example.dernierespoirsae.modele.Armes.Projectile;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.beans.binding.Bindings;

public class ObservateurProjectile implements ListChangeListener<Projectile> {
    private Pane projectilePane;

    public ObservateurProjectile(Pane projectilePane) {
        this.projectilePane = projectilePane;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Projectile> projectile) {

        while (projectile.next()){
            for(int i = 0; i < projectile.getAddedSize(); i++){
                if (projectile.getAddedSubList().get(i)instanceof BalleBave) {
                    new VueBave(projectilePane,projectile.getAddedSubList().get(i));
                }
                if (projectile.getAddedSubList().get(i)instanceof Balle) {
                    new VueBalle(projectilePane,projectile.getAddedSubList().get(i));
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
