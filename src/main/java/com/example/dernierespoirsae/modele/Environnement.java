package com.example.dernierespoirsae.modele;

import com.example.dernierespoirsae.Observateur.ObservateurActeurs;
import com.example.dernierespoirsae.Observateur.ObservateurArmes;
import com.example.dernierespoirsae.modele.Acteurs.*;
import com.example.dernierespoirsae.modele.Armes.Arme;
import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import com.example.dernierespoirsae.modele.Armes.Bave;
import com.example.dernierespoirsae.modele.Armes.Projectile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.dernierespoirsae.algo.BFS;

public class Environnement{

    private Acteur joueur;
    private Terrain terrain;
    private BFS bfs;
    private int[] infoTuile;
    private ObservableList<Acteur> acteurs;
    private ObservableList<Projectile> listprojectile;
    private ObservableList<Bave> listBave;
    private ObservableList<Arme> listArmeEnvironnement;
    private int temps;

    public Environnement(int tailleTuile,int nombreDeTuileLongueur,int nombreDeTuileLargeur){
        this.infoTuile = new int[3];
        this.infoTuile[0] = tailleTuile;
        this.infoTuile[1] = nombreDeTuileLongueur; //nombre de colonnes
        this.infoTuile[2] = nombreDeTuileLargeur; //nombre de lignes
        this.terrain = new Terrain();
        this.listArmeEnvironnement = FXCollections.observableArrayList();
        this.acteurs = FXCollections.observableArrayList();
        this.joueur = null;
        this.listprojectile = FXCollections.observableArrayList();
        this.listBave = FXCollections.observableArrayList();
        temps=0;
    }

    public void addActeurs(Acteur acteur) {
        this.acteurs.add(acteur);
    }

    public ObservableList<Arme> getListArmeEnvironnement() {
        return this.listArmeEnvironnement;
    }
    public ObservableList<Acteur> getListActeurs(){
        return this.acteurs;
    }

    public Joueur getJoueur() {
        return (Joueur) this.joueur;
    }

    public Terrain getTerrain() {
        return this.terrain;
    }

    public void setJoueur(Acteur joueur) {
        this.joueur = joueur;
        addActeurs(this.joueur);
    }

    public void setListenerActeurs(ObservateurActeurs acteursObserve){
        acteurs.addListener(acteursObserve);
    }

    public void setListenerArmeEnvironnement(ObservateurArmes armesObserve){
        listArmeEnvironnement.addListener(armesObserve);
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

    public ObservableList<Projectile> getListProjectile() {
        return listprojectile;
    }

    public void addProjectile(Projectile projectile){
        this.listprojectile.add(projectile);
    }

    public ObservableList<Bave> getListBave() {
        return listBave;
    }

    public void addBave(Bave bave){
        this.listBave.add(bave);
    }
    public void agit() {

        for (int i = 0; i < getListActeurs().size(); i++) {
            getListActeurs().get(i).agit(); //On fait agir les acteurs

            if (getListActeurs().get(i) != getJoueur()) {
                getListActeurs().get(i).meurtOuVie(); //Supprime les acteurs qui sont morts
            }
        }
        //Faire agir les projectiles
        for(int i=0;i< getListProjectile().size();i++) {
            getListProjectile().get(i).agit();
        }
    }
    public void unTour(){
        agit();
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }
}