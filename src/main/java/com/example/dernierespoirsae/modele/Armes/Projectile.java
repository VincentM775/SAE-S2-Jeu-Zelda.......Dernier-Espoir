package com.example.dernierespoirsae.modele.Armes;

import com.example.dernierespoirsae.modele.Acteurs.Acteur;
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
    private Acteur acteurQuiALancer;

    public Projectile(int degats, int x, int y, Environnement environnement, Acteur acteurQuiALancer) {
        this.degats = degats;
        this.id = idStatic++;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.jX = environnement.getJoueur().getX();
        this.jY = environnement.getJoueur().getY();
        this.vitesse = 8;
        this.environnement = environnement;
        this.acteurQuiALancer = acteurQuiALancer;
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
        if (!(testActeurToucher(prochaineValX,prochaineValY))&&!testMurSurRoute(prochaineValX,prochaineValY)) {
            setxProperty(prochaineValX);
            setyProperty(prochaineValY);
            flag = true;
        }
        return flag;
    }
    public boolean testProjectileArriverSurJoueur() {
        return (getXProperty()>= this.jX-(this.vitesse/2) && getXProperty()<= (this.jX+this.vitesse/2) && getYProperty()>= this.jY-(this.vitesse/2) && getYProperty()<= (this.jY+this.vitesse/2));

    }
    public boolean testMurSurRoute(int prochaineValX, int prochaineValY) {
        int tuileATester;
        boolean flag = false;
        int[][] directions = {{0, 0},{5, 0},{5, 5},{0, 5}};
        for (int[] tab : directions){
            tuileATester = (prochaineValY+tab[0])/environnement.getInfoTuile()[0]*environnement.getInfoTuile()[1]+(prochaineValX+tab[1])/environnement.getInfoTuile()[0];
            if (environnement.getTerrain().getTerrain().get(tuileATester)==1)
                flag=true;
        }

        return flag;
    }

    public boolean testActeurToucher(int prochaineValX,int prochaineValY) {
        prochaineValX = prochaineValX /environnement.getInfoTuile()[0];
        prochaineValY = prochaineValY /environnement.getInfoTuile()[0];
        int caseAX = 0;
        int caseAY = 0 ;
        for (int i=0;i< environnement.getListActeurs().size();i++){
            if (environnement.getListActeurs().get(i) != acteurQuiALancer) {
                caseAX = environnement.getListActeurs().get(i).getX() / environnement.getInfoTuile()[0];
                caseAY = environnement.getListActeurs().get(i).getY() / environnement.getInfoTuile()[0];
                if (caseAX==prochaineValX && caseAY==prochaineValY) {
                    environnement.getListActeurs().get(i).perdPV(10);
                    return true;
                }
            }
        }
        return false;
    }
    public double getAngle() {
        int deltaX = this.jX - getXProperty();
        int deltaY = this.jY - getYProperty();
        double angle;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        // Éviter la division par zéro
        if (distance == 0)
            angle = 0;
        else
            angle = Math.toDegrees(Math.atan2(deltaY, deltaX));

        // Ajouter un décalage de 90 degrés pour ajuster l'orientation de l'image
        return (angle + 90);
    }
}
