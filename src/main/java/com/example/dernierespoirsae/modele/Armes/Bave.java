package com.example.dernierespoirsae.modele.Armes;

import com.example.dernierespoirsae.modele.Acteur.Acteur;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Bave {


    private int ligne;
    private int colonne;
    private int degats;
    private static int idStatic=0;
    private int id;

    private Environnement environnement;
    private Acteur acteurQuiALancer;

    public Bave(Environnement environnement, Acteur acteurQuiALancer) {
        this.degats = 1;
        this.id = idStatic++;
        this.colonne = acteurQuiALancer.getX()/environnement.getInfoTuile()[0];
        this.ligne = acteurQuiALancer.getY()/environnement.getInfoTuile()[0];
        this.environnement = environnement;
        this.acteurQuiALancer = acteurQuiALancer;
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public int getColonne() {
        return this.colonne;
    }

    public int getLigne() {
        return this.ligne;
    }

    public void setColonne(int x) {
        this.colonne=x;
    }

    public void setLigneProperty(int y) {
        this.ligne=y;
    }

    public int getId() {
        return id;
    }

}
