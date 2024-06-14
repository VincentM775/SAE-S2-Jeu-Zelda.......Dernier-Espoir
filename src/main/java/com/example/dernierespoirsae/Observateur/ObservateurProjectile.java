package com.example.dernierespoirsae.Observateur;

import com.example.dernierespoirsae.Vue.VueBalle;
import com.example.dernierespoirsae.Vue.VueBave;
import com.example.dernierespoirsae.Vue.VueCocktailMolotov;
import com.example.dernierespoirsae.Vue.VueTerrain;
import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Objets.Projectile.Balle;
import com.example.dernierespoirsae.modele.Objets.Projectile.BalleBave;
import com.example.dernierespoirsae.modele.Objets.Projectile.CocktailM;
import com.example.dernierespoirsae.modele.Objets.Projectile.Projectile;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.text.CollationKey;

public class ObservateurProjectile implements ListChangeListener<Projectile> {
    private Pane projectilePane;
    private Pane persoPane;
    private Environnement environnement;
    private VueTerrain vueTerrain;
    private TilePane terrainPane;
    public ObservateurProjectile(Pane projectilePane, Pane persoPane,Environnement environnement, VueTerrain vueTerrain, TilePane terrainPane) {
        this.projectilePane = projectilePane;
        this.persoPane = persoPane;
        this.environnement = environnement;
        this.vueTerrain = vueTerrain;
        this.terrainPane = terrainPane;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Projectile> projectile) {

        while (projectile.next()){
            for(int i = 0; i < projectile.getAddedSize(); i++){
                if (projectile.getAddedSubList().get(i) instanceof BalleBave) {
                    new VueBave(this.projectilePane,projectile.getAddedSubList().get(i),this.persoPane,this.environnement,this.vueTerrain,this.terrainPane);
                }
                if (projectile.getAddedSubList().get(i) instanceof Balle) {
                    new VueBalle(this.projectilePane,projectile.getAddedSubList().get(i),this.persoPane,this.environnement,this.vueTerrain,this.terrainPane);
                }
                if (projectile.getAddedSubList().get(i) instanceof CocktailM) {
                    new VueCocktailMolotov(this.projectilePane,(CocktailM) projectile.getAddedSubList().get(i),this.persoPane,this.environnement,this.vueTerrain,this.terrainPane);
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
