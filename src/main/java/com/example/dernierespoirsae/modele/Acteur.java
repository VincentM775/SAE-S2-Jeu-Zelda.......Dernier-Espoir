package com.example.dernierespoirsae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;

public class Acteur {
    private static int id=0;
    private IntegerProperty xProperty, yProperty;
    private String nom;
    private Environnement environnement;
    private IntegerProperty vie;
    private int nombreDeDegat;

    private static int idStatic=0;

    private int id;

    private int longTuile;
    private int largeTuile;
    private int nbTuile;

    public Acteur(int x,int y, String nom, Environnement environnement,int vie, int nombreDeDegat, int longTuile, int largeTuile, int nbTuile) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.nom = nom;
        this.environnement = environnement;
        this.vie = new SimpleIntegerProperty(vie);
        this.nombreDeDegat = nombreDeDegat;
        this.longTuile = longTuile;
        this.largeTuile = largeTuile;
        this.nbTuile = nbTuile;
        this.id=idStatic++;
    }
    public Acteur(String nom, Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        this(300,260,nom, environnement, 20, 5,longTuile, largeTuile, nbTuile);
    }

    public void perdPV(int decrement){
        this.vie.setValue(this.vie.getValue()-decrement);
    }
    protected int getId() {
        return this.id;
    }

    public void setX(int x){
        this.xProperty.setValue(x);
    }

    public void meurt(){
        if(this.vie.getValue() < 0){
            environnement.getListActeurs().remove(this);
        }
    }
    public void setY(int y){
        this.yProperty.setValue(y);
    }

    public int getX() {
        return xProperty.getValue();
    }

    public final IntegerProperty xProperty() {
        return xProperty;
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

    public void seDeplacer(String direction){
        switch (direction){
            case "up" :
                this.yProperty.setValue(getY()-5);
                if(!collision(environnement))
                    this.yProperty.setValue(getY()+5);
                break;

            case "right" :
                this.xProperty.setValue(getX()+5);
                if(!collision(environnement))
                    this.xProperty.setValue(getX()-5);
                break;

            case "down" :
                this.yProperty.setValue(getY()+5);
                if(!collision(environnement))
                    this.yProperty.setValue(getY()-5);
                break;

            case "left" :
                this.xProperty.setValue(getX()-5);
                if(!collision(environnement))
                    this.xProperty.setValue(getX()+5);
                break;
        }
    }

    public boolean collision(Environnement environnement){

        int position = (int) ((this.getX() / this.longTuile) + (this.getY() / this.largeTuile * nbTuile));
//        int position = (int) ((this.getX() / 40) + (this.getY() / 40 * 25));

        if(position % 25 == 0 || position % 25 == 24 || position > 0 && position < 25 || position > 350 && position < 375 || environnement.getMap().getListTuiles().get(position) != 0)
            return false;
        return true;
    }
}