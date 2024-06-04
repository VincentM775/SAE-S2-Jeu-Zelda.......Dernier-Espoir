package com.example.dernierespoirsae.modele.Armes;

import com.example.dernierespoirsae.modele.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Projectile {


    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private int degats;
    private static int idStatic=0;
    private int id;

    private int jX; // valeur de x à la création de la balle
    private int jY; // valeur de y à la création de la balle
    private int vitesse; // Vitesse de déplacement de la balle
    private Environnement environnement;

    public Projectile(int degats, int x, int y, Environnement environnement) {
        this.degats = degats;
        this.id = idStatic++;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.jX = environnement.getJoueur().getX();
        this.jY = environnement.getJoueur().getY();
        this.vitesse = 8;
        this.environnement = environnement;
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public int getXProperty() {
        return this.xProperty.getValue();
    }

    public IntegerProperty xProperty() {
        return this.xProperty;
    }

    public int getYProperty() {
        return this.yProperty.getValue();
    }

    public IntegerProperty yProperty() {
        return this.yProperty;
    }

    public void setxProperty(int x) {
        this.xProperty.setValue(x);
    }

    public void setyProperty(int y) {
        this.yProperty.setValue(y);
    }

    public int getId() {
        return id;
    }
    public boolean avance() {
        boolean flag=false;
        int deltaX = this.jX - getXProperty();
        int deltaY = this.jY - getYProperty();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        int prochaineValX = getXProperty() + (int) (vitesse * (deltaX / distance));
        int prochaineValY = getYProperty() + (int) (vitesse * (deltaY / distance));
        if (!(testActeurToucher(prochaineValX/environnement.getInfoTuile()[0],prochaineValY/environnement.getInfoTuile()[0]))) {
            setxProperty(prochaineValX);
            setyProperty(prochaineValY);
            flag = true;
        }
        return flag;
    }
    public boolean testProjectileArriverSurJoueur() {
        return (getXProperty()>= this.jX-(this.vitesse/2) && getXProperty()<= (this.jX+this.vitesse/2));
    }
    public boolean testMurSurRoute(int prochaineValX, int prochaineValY) {
        return (prochaineValY/environnement.getInfoTuile()[0]);
        return false;
    }

    public boolean testActeurToucher(int prochaineValX,int prochaineValY) {
        int caseJX = environnement.getJoueur().getX()/environnement.getInfoTuile()[0];
        int caseJY = environnement.getJoueur().getY()/environnement.getInfoTuile()[0];

        return (caseJX==prochaineValX && caseJY==prochaineValY);
    }
}
