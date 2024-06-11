package com.example.dernierespoirsae.modele.Armes;

import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Projectile {

    private IntegerProperty xProperty;
    private IntegerProperty yProperty;

    private int degats;

    private static int idStatic=0;

    private int id;

    private int ouAllerX; // valeur de x à la création de la balle

    private int ouAllerY; // valeur de y à la création de la balle

    private int vitesse; // Vitesse de déplacement de la balle
    private Environnement environnement;
    private Acteur acteurQuiALancer;
    private boolean estVivant;

    public Projectile(int degats, Environnement environnement, Acteur acteurQuiALancer) {
        this.environnement = environnement;
        this.degats = degats;
        this.id = idStatic++;
        this.xProperty = new SimpleIntegerProperty(acteurQuiALancer.getX()+28/2);
        this.yProperty = new SimpleIntegerProperty(acteurQuiALancer.getY()+28/2);
        this.ouAllerX = jeVaisEnX();
        this.ouAllerY = jeVaisEnY();
        this.vitesse = 8;
        this.acteurQuiALancer = acteurQuiALancer;
        this.estVivant = true;
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
    public void avance() {
        int deltaX = this.ouAllerX - getXProperty();
        int deltaY = this.ouAllerY - getYProperty();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        int prochaineValX = getXProperty() + (int) (vitesse * (deltaX / distance));
        int prochaineValY = getYProperty() + (int) (vitesse * (deltaY / distance));
        if (!testMurSurRoute(prochaineValX,prochaineValY)) {
            if (!getEnvironnement().getListActeurs().isEmpty()) {
                testActeurToucher(prochaineValX, prochaineValY);
                setxProperty(prochaineValX);
                setyProperty(prochaineValY);
            }
        }
        else {
            setEstVivant(false);
        }
    }

    public boolean testMurSurRoute(int prochaineValX, int prochaineValY) {
        int tuileATester;
        boolean flag = false;
        int[][] directions = {{0, 0},{5, 0},{5, 5},{0, 5}};
        for (int[] tab : directions){
            tuileATester = (prochaineValY+tab[0])/environnement.getInfoTuile()[0]*environnement.getInfoTuile()[1]+(prochaineValX+tab[1])/environnement.getInfoTuile()[0];
            if (environnement.getTerrain().getTerrain().get(tuileATester)!=0)
                flag=true;
        }
        return flag;
    }

    public void testActeurToucher(int prochaineValX,int prochaineValY) {
        prochaineValX = prochaineValX /environnement.getInfoTuile()[0];
        prochaineValY = prochaineValY /environnement.getInfoTuile()[0];
        int caseAX = 0;
        int caseAY = 0;
        for (int i=0;i< environnement.getListActeurs().size();i++){
            if (environnement.getListActeurs().get(i) != acteurQuiALancer) {
                caseAX = environnement.getListActeurs().get(i).getX() / environnement.getInfoTuile()[0];
                caseAY = environnement.getListActeurs().get(i).getY() / environnement.getInfoTuile()[0];
                if (caseAX==prochaineValX && caseAY==prochaineValY) {
                    environnement.getListActeurs().get(i).perdPV(10);

                }
            }
        }
    }
    public boolean testProjectileArriverSurJoueur() {
        return (getXProperty()>= getjX()-(getVitesse()/2) && getXProperty()<= (getjX()+getVitesse()/2) && getYProperty()>= this.getjY()-(getVitesse()/2) && getYProperty()<= (this.getjY()+getVitesse()/2));
    }
    public double getAngle() {
        int deltaX = this.ouAllerX - getXProperty();
        int deltaY = this.ouAllerY - getYProperty();
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

    public int getjX() {
        return this.ouAllerX;
    }

    public int getjY() {
        return this.ouAllerY;
    }

    public int getVitesse() {
        return vitesse;
    }
    public abstract void agit();

    public void setEstVivant(boolean estVivant) {
        this.estVivant = estVivant;
    }
    public boolean getEstVivant(){
        return this.estVivant;
    }

    public Environnement getEnvironnement() {
        return this.environnement;
    }
    public abstract int jeVaisEnX();
    public abstract int jeVaisEnY();
}
