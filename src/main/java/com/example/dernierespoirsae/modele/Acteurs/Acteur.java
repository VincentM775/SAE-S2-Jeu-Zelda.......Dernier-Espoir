package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.modele.Collision;
import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.EstPresentRayon;
import javafx.beans.property.*;
import com.example.dernierespoirsae.modele.Inventaire;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

public abstract class Acteur {

    private int vitesse; // Vitesse de déplacement de l'acteur
    private IntegerProperty xProperty, yProperty;
    private String nom;
    private Environnement environnement;
    private StringProperty direction;
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
    private int xDeLaSouris;
    private int yDeLaSouris;
    private BooleanProperty armeALattaque;
    private int longBox;
    private int hautBox;

    public Acteur(int x,int y, String nom, Environnement environnement, int vie, int vitesse, int longTuile, int largeTuile, int nbTuile, int longBox, int hautBox) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.nom = nom;
        this.maxVie = new SimpleIntegerProperty(vie); // Initialise maxVie à la valeur de vie la initiale
        this.environnement = environnement;
        this.vitesse = vitesse;
        this.vie = new SimpleIntegerProperty(vie);
        this.longTuile = longTuile;
        this.largeTuile = largeTuile;
        this.nbTuile = nbTuile;
        this.longBox = longBox;
        this.hautBox = hautBox;
        this.collision = new Collision(longBox, hautBox, this);
        this.id=idStatic++;
        this.direction = new SimpleStringProperty("null");
        this.derniereDirection="null";
        this.clickSouris = "";
        this.touche = "";
        this.xDeLaSouris = 0;
        this.yDeLaSouris = 0;
        this.armeALattaque = new SimpleBooleanProperty(false);
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
        this.direction.setValue(direction);
    }

    public String getDirection() {
        return direction.getValue();
    }
    public StringProperty getDirectionProperty() {
        return direction;
    }

    public boolean estPresentDansRayonPixel(int rayonPixel,int x,int y){
        return EstPresentRayon.estPresentDansRayonPixel(rayonPixel,getX(),getY(),longBox,largeTuile,x,y);
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

    public int getxDeLaSouris() {
        return xDeLaSouris;
    }

    public int getyDeLaSouris() {
        return yDeLaSouris;
    }

    public void setyDeLaSouris(int yDeLaSouris) {
        this.yDeLaSouris = yDeLaSouris;
    }

    public void setxDeLaSouris(int xDeLaSouris) {
        this.xDeLaSouris = xDeLaSouris;
    }

    public BooleanProperty armeALattaqueProperty() {
        return armeALattaque;
    }

    public void setArmeALattaque(boolean armeALattaque) {
        this.armeALattaque.set(armeALattaque);
    }

    public boolean getArmeALattaque() {
        return armeALattaque.getValue();
    }

    public int getLongBox() {
        return longBox;
    }

    public int getHautBox() {
        return hautBox;
    }
}