package com.example.dernierespoirsae.modele;
import com.example.dernierespoirsae.Vue.ObservateurActeurs;
import com.example.dernierespoirsae.Vue.ObservateurArmes;
import com.example.dernierespoirsae.modele.Armes.Armes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement{

    private ObservableList<Acteur> acteurs;
    private Acteur joueur;
    private  ObservableList<Armes> listArmes;
    private Map map;

    public Environnement(int n){
        this.listArmes = FXCollections.observableArrayList();
        this.map = new Map();
        this.acteurs = FXCollections.observableArrayList();
        this.joueur = null;
    }
    public void addActeurs(Acteur acteur) {
        this.acteurs.add(acteur);
    }

    public ObservableList<Armes> getListArmes() {
        return this.listArmes;
    }
    public ObservableList<Acteur> getListActeurs(){
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

    public void setListenerArmes(ObservateurArmes armesObserve){
        listArmes.addListener(armesObserve);
    }


}