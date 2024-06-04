package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteur;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;


public class ObservateurActeurs implements ListChangeListener<Acteur> {

    private Pane persoPane;
    private boolean aEteSupprime;

    public ObservateurActeurs(Pane pane) {
        this.persoPane = pane;
        aEteSupprime = false;
    }

    @Override
    public void onChanged(Change<? extends Acteur> acteurs) {

        while (acteurs.next()){

            //Parcours la liste des acteurs ajoutés a la liste et le créer a l'affichage
            for(int i = 0; i < acteurs.getAddedSize(); i++){
                new VueActeur(persoPane, acteurs.getAddedSubList().get(i));
            }

            //Parcours la liste des acteurs supprimés a la liste et le supprime a l'affichage
            for(int i = 0; i < acteurs.getRemovedSize(); i++){
                if(!aEteSupprime){
                    this.persoPane.getChildren().remove(this.persoPane.lookup("#"+acteurs.getRemoved().get(i).getId()));
                }
                aEteSupprime = true;
            }
        }
    }
}
