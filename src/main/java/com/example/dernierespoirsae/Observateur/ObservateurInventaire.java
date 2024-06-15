package com.example.dernierespoirsae.Observateur;

import com.example.dernierespoirsae.Vue.VueInventaireObjets;
import com.example.dernierespoirsae.modele.Acteurs.Joueur;
import com.example.dernierespoirsae.modele.Inventaire;
import com.example.dernierespoirsae.modele.Objets.Objets;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.VBox;

public class ObservateurInventaire implements ListChangeListener<Objets> {

    private VBox inventaireVBox;
    private Inventaire inventaire;
    private Joueur joueur;

    public ObservateurInventaire(VBox inventaireVBox,Inventaire inventaire, Joueur joueur) {
        this.inventaireVBox = inventaireVBox;
        this.inventaire = inventaire;
        this.joueur = joueur;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Objets> objet) {

        while (objet.next()){ //Tant qu'il y a un acteur dans la liste des armes ayant été ajouté/supprimé
            for(int i = 0; i < objet.getAddedSize(); i++){

                //Incremete la quantitée de l'Objet
                objet.getAddedSubList().get(i).incremeterDecremeterQuantiteInventaire(1);

                //Affiche l'arme dans la vueInventaire
                new VueInventaireObjets(inventaireVBox,objet.getAddedSubList().get(i), inventaire, joueur);
            }

            for(int i = 0; i < objet.getRemovedSize(); i++){

                //Decremete la quantitée l'objet
                objet.getRemoved().get(i).incremeterDecremeterQuantiteInventaire(-1);

            }
        }
    }
}