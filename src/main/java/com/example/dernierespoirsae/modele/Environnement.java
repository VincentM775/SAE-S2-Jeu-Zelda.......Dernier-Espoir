package com.example.dernierespoirsae.modele;
import com.example.dernierespoirsae.Vue.ObservateurActeurs;
import com.example.dernierespoirsae.modele.Armes.Armes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Environnement{

    private ObservableList<Acteur> acteurs;
    private Acteur joueur;
    private Map map;
    private int[] infoTuile;

    public Environnement(int tailleTuile,int nombreDeTuileLongueur,int nombreDeTuileLargeur){
        this.infoTuile = new int[3];
        this.infoTuile[0] = tailleTuile;
        this.infoTuile[1] = nombreDeTuileLongueur;
        this.infoTuile[2] = nombreDeTuileLargeur;
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

    public int[] getInfoTuile() {
        return this.infoTuile;
    }
}