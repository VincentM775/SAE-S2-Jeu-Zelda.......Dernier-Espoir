package com.example.dernierespoirsae.modele;
import com.example.dernierespoirsae.Vue.ObservateurActeurs;
import com.example.dernierespoirsae.modele.Armes.Armes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.example.dernierespoirsae.algo.BFS;

import java.util.ArrayList;

public class Environnement{

    private Acteur Joueur;
    private Terrain terrain;
    private BFS bfs;

    private int[] infoTuile;
    private ObservableList<Acteur> acteurs;


    public Environnement(int tailleTuile,int nombreDeTuileLongueur,int nombreDeTuileLargeur){
        this.infoTuile = new int[3];
        this.infoTuile[0] = tailleTuile;
        this.infoTuile[1] = nombreDeTuileLongueur; //nombre de colonnes
        this.infoTuile[2] = nombreDeTuileLargeur; //nombre de lignes
        this.terrain = new Terrain();
        this.acteurs = FXCollections.observableArrayList();
        this.Joueur = null;
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
    public Terrain getMap() {
        return this.terrain;
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

    public void setBfs(BFS bfs) {
        this.bfs = bfs;
    }

    public BFS getBfs() {
        return bfs;
    }
}