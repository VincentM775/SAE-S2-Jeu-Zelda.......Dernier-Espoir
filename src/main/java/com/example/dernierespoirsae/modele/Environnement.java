package com.example.dernierespoirsae.modele;

import com.example.dernierespoirsae.Vue.ObservateurActeurs;
import com.example.dernierespoirsae.Vue.ObservateurArmes;
import com.example.dernierespoirsae.modele.Armes.Arme;
import com.example.dernierespoirsae.Vue.ObservateurProjectile;
import com.example.dernierespoirsae.modele.Acteur.Acteur;
import com.example.dernierespoirsae.modele.Armes.Balle;
import com.example.dernierespoirsae.modele.Armes.Bave;
import com.example.dernierespoirsae.modele.Armes.Projectile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.dernierespoirsae.algo.BFS;

public class Environnement{

    private ObservableList<Acteur> acteurs;
    private Acteur joueur;
    private Terrain terrain;
    private BFS bfs;
    private int[] infoTuile;
    private ObservableList<Acteur> acteurs;
    private ObservableList<Projectile> Listprojectile;
    private ObservableList<Bave> ListBave;
    private  ObservableList<Arme> listArmes;


    public Environnement(int tailleTuile,int nombreDeTuileLongueur,int nombreDeTuileLargeur){
        this.infoTuile = new int[3];
        this.infoTuile[0] = tailleTuile;
        this.infoTuile[1] = nombreDeTuileLongueur; //nombre de colonnes
        this.infoTuile[2] = nombreDeTuileLargeur; //nombre de lignes
        this.terrain = new Terrain();
        this.listArmes = FXCollections.observableArrayList();
        this.acteurs = FXCollections.observableArrayList();
        this.joueur = null;
        this.Listprojectile = FXCollections.observableArrayList();
        this.ListBave = FXCollections.observableArrayList();
    }

    public void addActeurs(Acteur acteur) {
        this.acteurs.add(acteur);
    }

    public ObservableList<Arme> getListArmes() {
        return this.listArmes;
    }
    public ObservableList<Acteur> getListActeurs(){
        return this.acteurs;
    }

    public Acteur getJoueur() {
        return this.joueur;
    }

    public Terrain getMap() {
        return this.terrain;
    }

    public void setJoueur(Acteur joueur) {
        this.joueur = joueur;
        addActeurs(this.joueur);
    }

    public void setListenerActeurs(ObservateurActeurs acteursObserve){
        acteurs.addListener(acteursObserve);
    }

    public void setListenerArmes(ObservateurArmes armesObserve){
        listArmes.addListener(armesObserve);
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
        return Listprojectile;
    }
    public void setListenerProjectile(ObservateurProjectile projectileObserve){
        Listprojectile.addListener(projectileObserve);
    }
    public void addProjectile(Projectile projectile){
        this.Listprojectile.add(projectile);
    }

    public ObservableList<Bave> getListBave() {
        return ListBave;
    }
    public void addBave(Bave bave){
        this.ListBave.add(bave);
    }

}