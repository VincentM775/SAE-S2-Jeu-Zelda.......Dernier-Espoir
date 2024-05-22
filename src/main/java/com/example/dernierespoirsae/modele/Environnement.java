package com.example.dernierespoirsae.modele;
import com.example.dernierespoirsae.Vue.ObservateurActeurs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Environnement{

    private ObservableList<Acteur> acteurs;
    private Acteur joueur;

    private Map map;


    public Environnement(int n){
//        this.map = new Map(n);
        this.map = new Map();
        this.acteurs = FXCollections.observableArrayList();
        this.joueur = null;
    }
    public void addActeurs(Acteur acteur) {
        this.acteurs.add(acteur);
    }

    public ObservableList<Acteur> getListActeurs(){
        //ArrayList<Acteur> acteurs = new ArrayList<>();
        //acteurs.addAll(this.acteurs);
        return this.acteurs;
    }
    public Acteur getJoueur() {
        return joueur;
    }
    public Map getMap() {
        return this.map;
    }

    public void setJoueur(Acteur joueur) {
        this.joueur = joueur;
    }

    public void setListenerActeurs(ObservateurActeurs acteursObserve){
        acteurs.addListener(acteursObserve);
    }
}