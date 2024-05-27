package com.example.dernierespoirsae.Vue;
import com.example.dernierespoirsae.modele.Acteur;
import com.example.dernierespoirsae.modele.Armes.Armes;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurArmes implements ListChangeListener<Armes> {


    private VueArmes vueArmes;
    private Acteur joueur;
    private Pane armePane;

    public ObservateurArmes(Pane pane, Acteur joueur) {
        this.armePane = pane;
        this.joueur = joueur;
    }

    public void onChanged(ListChangeListener.Change<? extends Armes> c) {

        while (c.next()){
            VueArmes vueArmes;
            for(int i = 0; i < c.getAddedSize(); i++){
                vueArmes = new VueArmes(armePane, c.getAddedSubList().get(i), this.joueur, this.joueur.getInventaire());
            }
            for(int i = 0; i < c.getRemovedSize(); i++){
                suprimerArme(c.getRemoved().get(i));
            }
        }
    }
    public void suprimerArme(Armes arme){
        this.armePane.getChildren().remove(this.armePane.lookup("#"+arme.getId()));
    }
}
