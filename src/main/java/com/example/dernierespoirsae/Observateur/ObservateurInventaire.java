package com.example.dernierespoirsae.Observateur;

import com.example.dernierespoirsae.Vue.VueInventaire;
import com.example.dernierespoirsae.modele.Armes.Arme;
import com.example.dernierespoirsae.modele.Inventaire;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.VBox;

public class ObservateurInventaire implements ListChangeListener<Arme> {

    private VBox inventaireVBox;
    public Inventaire inventaire;

    public ObservateurInventaire(VBox inventaireVBox,Inventaire inventaire) {
        this.inventaireVBox = inventaireVBox;
        this.inventaire = inventaire;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Arme> Arme) {

        while (Arme.next()){ //Tant qu'il y a un acteur dans la liste des armes ayant été ajouté/supprimé
            for(int i = 0; i < Arme.getAddedSize(); i++){

                //Incremete la quantitée de l'arme
                Arme.getAddedSubList().get(i).incremeterDecremeterQuantiteInventaire(1);
                //Affiche l'arme dans la vueInventaire
                new VueInventaire(inventaireVBox, Arme.getAddedSubList().get(i), inventaire);
            }

            for(int i = 0; i < Arme.getRemovedSize(); i++){

                //Decremete la quantitée l'arme d'un
                Arme.getRemoved().get(i).incremeterDecremeterQuantiteInventaire(-1);

                //Si le joueur n'a plus d'arme de ce type dans l'inventaire alors le Pane associé est suprimée
                if(Arme.getRemoved().get(i).getQuantite()==0){

                    inventaireVBox.getChildren().remove(inventaireVBox.lookup("#"+Arme.getRemoved().get(i).getType()));
                } //Sinon On créer un objet VueInventaire qui va s'occuper de decremeter le label
                else new VueInventaire(inventaireVBox, Arme.getRemoved().get(i), inventaire);

            }
        }
    }
}