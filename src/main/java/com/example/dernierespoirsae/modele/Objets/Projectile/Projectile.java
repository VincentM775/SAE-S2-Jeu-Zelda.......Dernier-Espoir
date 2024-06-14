package com.example.dernierespoirsae.modele.Objets.Projectile;

import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Projectile {

    private int initialX;
    private int initialY;
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;

    private int degats;

    private static int idStatic=0;

    private int id;

    private int ouAllerX; // valeur de x où il faut aller

    private int ouAllerY; // valeur de y où il faut aller

    private int vitesse; // Vitesse de déplacement de la balle
    private Environnement environnement;
    private Acteur acteurQuiALancer;
    private boolean estVivant;
    private int portee;

    public Projectile(int degats, Environnement environnement, Acteur acteurQuiALancer, int vitesse, int portee) {
        this.environnement = environnement;
        this.degats = degats;
        this.id = idStatic++;
        this.xProperty = new SimpleIntegerProperty(acteurQuiALancer.getX()+28/2);
        this.yProperty = new SimpleIntegerProperty(acteurQuiALancer.getY()+28/2);
        this.initialX = this.xProperty.get();
        this.initialY = this.yProperty.get();
        this.ouAllerX = jeVaisEnX();
        this.ouAllerY = jeVaisEnY();
        this.vitesse = vitesse;
        this.acteurQuiALancer = acteurQuiALancer;
        this.estVivant = true;
        this.portee = portee;
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public int getX() {
        return this.xProperty.getValue();
    }

    public IntegerProperty xProperty() {
        return this.xProperty;
    }

    public int getY() {
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
        int deltaX = this.ouAllerX - getX();
        int deltaY = this.ouAllerY - getY();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        int prochaineValX = getX() + (int) (vitesse * (deltaX / distance));
        int prochaineValY = getY() + (int) (vitesse * (deltaY / distance));

        // Calculer la distance parcourue depuis la position initiale
        int totalDeltaX = prochaineValX - initialX;
        int totalDeltaY = prochaineValY - initialY;
        double distanceParcourue = Math.sqrt(totalDeltaX * totalDeltaX + totalDeltaY * totalDeltaY);

        if ((!testMurSurRoute(prochaineValX,prochaineValY)&&distanceParcourue<=portee)
            && !getEnvironnement().getListActeurs().isEmpty()
            && !testActeurToucher(prochaineValX, prochaineValY)) {
                setxProperty(prochaineValX);
                setyProperty(prochaineValY);
        }
        else {
            effet();
            setEstVivant(false);
        }
    }

    public boolean testMurSurRoute(int prochaineValX, int prochaineValY) {
        int tuileATester;
        boolean flag = false;
        int[][] directions = {{0, 0},{5, 0},{5, 5},{0, 5}};
        for (int[] tab : directions){
            tuileATester = (prochaineValY+tab[0])/environnement.getInfoTuile()[0]*environnement.getInfoTuile()[1]+(prochaineValX+tab[1])/environnement.getInfoTuile()[0];
            if (environnement.getTerrain().estObstacle(tuileATester)) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean testActeurToucher(int prochaineValX,int prochaineValY) {
        int caseAX = 0;
        int caseAY = 0;
        for (int i=0;i< environnement.getListActeurs().size();i++){
            if (environnement.getListActeurs().get(i) != acteurQuiALancer) {
                caseAX = environnement.getListActeurs().get(i).getX() ;
                caseAY = environnement.getListActeurs().get(i).getY() ;
                if (prochaineValX >=caseAX && prochaineValX <= caseAX+environnement.getListActeurs().get(i).getHitBox().getLongueur()
                && prochaineValY >= caseAY && prochaineValY <= caseAY+environnement.getListActeurs().get(i).getHitBox().getHauteur()){
                    getEnvironnement().getListActeurs().get(i).perdPV(getDegats()); //On met de gros dégats à l'ennemi toucher
                    return true;
                }
            }
        }
        return false;
    }
    public boolean testProjectileArriverSurCoord() {
        return (getX()>= getouAllerX()-(getVitesse()/2) && getX()<= (getouAllerX()+getVitesse()/2) && getY()>= this.getouAllerY()-(getVitesse()/2) && getY()<= (this.getouAllerY()+getVitesse()/2));
    }
    public double getAngle() {
        int deltaX = this.ouAllerX - getX();
        int deltaY = this.ouAllerY - getY();
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

    public int getouAllerX() {
        return this.ouAllerX;
    }

    public int getouAllerY() {
        return this.ouAllerY;
    }

    public int getVitesse() {
        return vitesse;
    }
    public void agit(){
        if (getEstVivant()) {
            if (!testProjectileArriverSurCoord()) {
                avance();
            }
            else{
                effet();
                setEstVivant(false);
            }
        }
        else getEnvironnement().getListProjectile().remove(this);

    }

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
    public abstract void effet();
}
