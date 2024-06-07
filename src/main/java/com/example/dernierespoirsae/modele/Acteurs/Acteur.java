package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.modele.Armes.Arme;
import com.example.dernierespoirsae.modele.Collision;
import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Inventaire;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Acteur {

    private ObservableList<Arme> armes;
    private int vitesse; // Vitesse de déplacement de l'acteur
    private IntegerProperty xProperty, yProperty;
    private String nom;
    private Inventaire inventaire;
    private Environnement environnement;
    private String direction;
    private IntegerProperty vie;
    private String derniereDirection;

    private static int idStatic=0;
    private int id;
    private int longTuile;
    private int largeTuile;
    private int nbTuile;
    private Collision collision;
    private IntegerProperty maxVie;
    private String touche;
    private String clickSouris;

    public Acteur(int x,int y, String nom, Environnement environnement, int vie, int vitesse, int longTuile, int largeTuile, int nbTuile, int longBox, int largeBox) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.nom = nom;
        this.maxVie = new SimpleIntegerProperty(vie); // Initialiser maxVie à la valeur de vie initiale
        this.environnement = environnement;
        this.vitesse = vitesse;
        this.vie = new SimpleIntegerProperty(vie);
        this.longTuile = longTuile;
        this.largeTuile = largeTuile;
        this.nbTuile = nbTuile;
        this.collision = new Collision(longBox, largeBox, this);
        this.id=idStatic++;
        this.direction = "null";
        this.derniereDirection="null";
        this.inventaire = new Inventaire();
        this.armes = FXCollections.observableArrayList();
        this.clickSouris ="";
        this.touche = "";
    }

    public int getMaxVie() {
        return maxVie.get();
    }
    public IntegerProperty maxVie() {
        return maxVie;
    }
    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getVie() {
        return vie.getValue();
    }

    public IntegerProperty getVieProperty() {
        return vie;
    }

    public void perdPV(int decrement){
        this.vie.setValue(this.vie.getValue()-decrement);
    }
    public int getId() {
        return this.id;
    }

    public String getDerniereDirection() {
        return derniereDirection;
    }

    public void setDerniereDirection(String derniereDirection) {
        this.derniereDirection = derniereDirection;
    }

    public void setX(int x){
        this.xProperty.setValue(x);
    }

    public abstract void meurtOuVie();
    public void setY(int y){
        this.yProperty.setValue(y);
    }

    public int getxProperty() {
        return xProperty.get();
    }

    public int getX() {
        return xProperty.getValue();
    }

    public final IntegerProperty xProperty() {
        return xProperty;
    }

    public int getyProperty() {
        return yProperty.get();
    }

    public int getY() {
        return yProperty.getValue();
    }

    public final IntegerProperty yProperty() {
        return yProperty;
    }

    public String getNom() {
        return nom;
    }

    public abstract void seDeplacer();

    public int getLongTuile() {
        return longTuile;
    }

    public int getLargeTuile() {
        return largeTuile;
    }

    public int getNbTuile() {
        return nbTuile;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

    public Collision getHitBox() {
        return collision;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public ObservableList<Arme> getArmes() {
        return armes;
    }

    public void setArmes(ObservableList<Arme> armes) {
        this.armes = armes;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }


    public boolean estPresentDansRayonPixel(int rayonPixel,int x,int y){
        //On récupère les numéros de ligne et de colonne sur la map
        int aX = getX();
        int aY = getY();

        //On renvoie true si les coordonnées x et y entrée en paramètre se trouve dans la portée de l'acteur
        return (Math.abs(x-aX)<=rayonPixel && Math.abs(y-aY)<=rayonPixel);
    }

    public void setTouche(String touche) {
        this.touche = touche;
    }

    public String getTouche() {
        return touche;
    }
    public abstract void agit();

    public void setClicks(String clickSouris) {
        this.clickSouris = clickSouris;
    }

    public String getClickSouris() {
        return this.clickSouris;
    }
}