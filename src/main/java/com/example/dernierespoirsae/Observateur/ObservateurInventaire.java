package com.example.dernierespoirsae.Observateur;

import com.example.dernierespoirsae.Vue.VueInventaireArmes;
import com.example.dernierespoirsae.Vue.VueInventaireObjetAvecQuantite;
import com.example.dernierespoirsae.Vue.VueInventaireObjets;
import com.example.dernierespoirsae.modele.Objets.Armes.Arme;
import com.example.dernierespoirsae.modele.Inventaire;
import com.example.dernierespoirsae.modele.Objets.AutreObjets.AutreObjets;
import com.example.dernierespoirsae.modele.Objets.AutreObjets.AutreObjetsAvecQuantite;
import com.example.dernierespoirsae.modele.Objets.Objets;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.VBox;

public class ObservateurInventaire implements ListChangeListener<Objets> {

    private VBox inventaireVBox;
    public Inventaire inventaire;

    public ObservateurInventaire(VBox inventaireVBox,Inventaire inventaire) {
        this.inventaireVBox = inventaireVBox;
        this.inventaire = inventaire;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Objets> objet) {

        while (objet.next()){ //Tant qu'il y a un acteur dans la liste des armes ayant été ajouté/supprimé
            for(int i = 0; i < objet.getAddedSize(); i++){
                if (objet.getAddedSubList().get(i) instanceof Arme) {
                    //Incremete la quantitée de l'arme
                    objet.getAddedSubList().get(i).incremeterDecremeterQuantiteInventaire(1);
                    //Affiche l'arme dans la vueInventaire
                    new VueInventaireArmes(inventaireVBox, objet.getAddedSubList().get(i), inventaire);
                }
                else if (objet.getAddedSubList().get(i) instanceof AutreObjets){
                    //Incremete la quantitée de l'arme
//                    objet.getAddedSubList().get(i).incremeterDecremeterQuantiteInventaire(objet.getAddedSubList().get(i).getQuantite());
                    objet.getAddedSubList().get(i).incremeterDecremeterQuantiteInventaire(1);
                    //Affiche l'arme dans la vueInventaire
                    new VueInventaireObjets(inventaireVBox, (AutreObjets) objet.getAddedSubList().get(i), inventaire);
                }
                else if (objet.getAddedSubList().get(i) instanceof AutreObjetsAvecQuantite){
                    //Incremete la quantitée de l'arme
//                    objet.getAddedSubList().get(i).incremeterDecremeterQuantiteInventaire(objet.getAddedSubList().get(i).getQuantite());
                    objet.getAddedSubList().get(i).incremeterDecremeterQuantiteInventaire(1);
                    //Affiche l'arme dans la vueInventaire
                    new VueInventaireObjetAvecQuantite(inventaireVBox, (AutreObjetsAvecQuantite) objet.getAddedSubList().get(i), inventaire);
                }
            }

            for(int i = 0; i < objet.getRemovedSize(); i++){

                //Decremete la quantitée l'arme d'un
                objet.getRemoved().get(i).incremeterDecremeterQuantiteInventaire(-1);

                //Si le joueur n'a plus d'arme de ce type dans l'inventaire alors le Pane associé est suprimée
                if(objet.getRemoved().get(i).getQuantite()==0){

                    inventaireVBox.getChildren().remove(inventaireVBox.lookup("#"+objet.getRemoved().get(i).getType()));
                } //Sinon On créer un objet VueInventaire qui va s'occuper de decremeter le label
                else new VueInventaireArmes(inventaireVBox, objet.getRemoved().get(i), inventaire);

            }
        }
    }
}