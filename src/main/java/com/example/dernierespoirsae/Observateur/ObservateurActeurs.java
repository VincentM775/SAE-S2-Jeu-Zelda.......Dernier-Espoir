package com.example.dernierespoirsae.Observateur;

import com.example.dernierespoirsae.Vue.*;
import com.example.dernierespoirsae.modele.Acteurs.*;
import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import com.example.dernierespoirsae.modele.Acteurs.BaveZmort;
import com.example.dernierespoirsae.modele.Acteurs.Joueur;
import com.example.dernierespoirsae.modele.Acteurs.MasticatorZ;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class ObservateurActeurs implements ListChangeListener<Acteur> {

    private Pane persoPane,barreViePane;
    private TilePane terrainPane;
    private Environnement environnement;
    private VueTerrain vueTerrain;

    public ObservateurActeurs(Pane pane,Pane barreViePane,TilePane terrainPane, Environnement environnement,VueTerrain vueTerrain) {
        this.persoPane = pane;
        this.environnement = environnement;
        this.terrainPane = terrainPane;
        this.barreViePane = barreViePane;
        this.vueTerrain = vueTerrain;
    }

    @Override
    public void onChanged(Change<? extends Acteur> acteurs) {

        while (acteurs.next()){ //Tant qu'il y a un acteur dans la liste des acteurs ayant été ajouté/supprimé

            //Parcours la liste des acteurs ajoutés a la liste et le créer a l'affichage
            for(int i = 0; i < acteurs.getAddedSize(); i++){
                //Crée l'affichage de l'acteur i ajouté
                if (acteurs.getAddedSubList().get(i) instanceof Joueur)
                    new VueJoueur(this.persoPane,barreViePane,this.terrainPane, acteurs.getAddedSubList().get(i),this.environnement);

                else if (acteurs.getAddedSubList().get(i) instanceof MasticatorZ)
                    new VueMasticatorZ(this.persoPane,barreViePane,this.terrainPane, acteurs.getAddedSubList().get(i),this.environnement);

                else if (acteurs.getAddedSubList().get(i) instanceof Zamikaze)
                    new VueZamikaze(this.persoPane,barreViePane,this.terrainPane, (Zamikaze) acteurs.getAddedSubList().get(i),this.environnement,this.vueTerrain);

                else if (acteurs.getAddedSubList().get(i) instanceof BaveZmort)
                    new VueBaveZmort(this.persoPane,barreViePane,this.terrainPane, acteurs.getAddedSubList().get(i),this.environnement);

                else if (acteurs.getAddedSubList().get(i) instanceof PNJ)
                    new VuePNJ(this.persoPane,barreViePane,this.terrainPane, acteurs.getAddedSubList().get(i),this.environnement);
            }

            //Parcours la liste des acteurs supprimés a la liste et le supprime a l'affichage
            for(int i = 0; i < acteurs.getRemovedSize(); i++){

                //Supprime l'affiche de l'acteur
                this.persoPane.getChildren().remove(this.persoPane.lookup("#"+acteurs.getRemoved().get(i).getId()));
                this.persoPane.getChildren().remove(this.persoPane.lookup("#"+"barre"+acteurs.getRemoved().get(i).getId()));

            }
        }
    }
}
