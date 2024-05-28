package com.example.dernierespoirsae.modele;

import com.example.dernierespoirsae.algo.BFS;

import java.util.ArrayList;

public class Environnement{

    private ArrayList<Acteur> acteurs;
    private Acteur Joueur;
    private Terrain terrain;
    private BFS bfs;

    private int[] infoTuile;


    public Environnement(int tailleTuile,int nombreDeTuileLongueur,int nombreDeTuileLargeur){
        this.infoTuile = new int[3];
        this.infoTuile[0] = tailleTuile;
        this.infoTuile[1] = nombreDeTuileLongueur; //nombre de colonnes
        this.infoTuile[2] = nombreDeTuileLargeur; //nombre de lignes
        this.terrain = new Terrain();
        this.acteurs = new ArrayList<>();
        this.Joueur = null;
    }

    public void addActeurs(Acteur acteur) {
        this.acteurs.add(acteur);
    }

    public ArrayList<Acteur> getActeurs(){
        return this.acteurs;
    }
    public Acteur getJoueur() {
        return Joueur;
    }
    public Terrain getMap() {
        return this.terrain;
    }

    public void setJoueur(Acteur joueur) {
        Joueur = joueur;
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