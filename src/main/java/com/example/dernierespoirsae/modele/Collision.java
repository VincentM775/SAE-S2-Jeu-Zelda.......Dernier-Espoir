package com.example.dernierespoirsae.modele;

public class Collision {

    private int longueur;
    private int hauteur;
    private Acteur acteur;

    public Collision(int longueur, int hauteur, Acteur acteur){
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.acteur = acteur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getLongueur() {
        return longueur;
    }

    /**
     * Quand l'Acteur veut aller à droite, il vérifie que tous ses pixels de droite ne rentrent pas en collision avec une tuile "obstacle"
     */
    public boolean collisionDroite(){
        this.acteur.getEnvironnement().addActeurs(this.acteur.getEnvironnement().getJoueur());
        int positionPixelX = this.acteur.getX() + acteur.getVitesse() + this.longueur;
        int positionPixelY;
        int tuileSousPosition;

        for(int i = 0; i < hauteur; i++){
            positionPixelY = this.acteur.getY() + i;
            tuileSousPosition = (positionPixelX / acteur.getLongTuile()) + ((positionPixelY) / this.acteur.getLargeTuile() * this.acteur.getNbTuile());
            if(tuileSousPosition % 25 == 24 || acteur.getEnvironnement().getMap().getListTuiles().get(tuileSousPosition) != 0 || collisionEntreActeurs(positionPixelX, positionPixelY)){
                this.acteur.getEnvironnement().getActeurs().remove(this.acteur.getEnvironnement().getJoueur());
                return false;
            }
        }
        this.acteur.getEnvironnement().getActeurs().remove(this.acteur.getEnvironnement().getJoueur());
        return true;
    }

    /**
     * Quand l'Acteur veut aller à gauche, il vérifie que tous ses pixels de gauche ne rentrent pas en collision avec une tuile "obstacle"
     */
    public boolean collisionGauche() {
        this.acteur.getEnvironnement().addActeurs(this.acteur.getEnvironnement().getJoueur());
        int positionPixelX = this.acteur.getX() - this.acteur.getVitesse();
        int positionPixelY;
        int tuileSousPosition;

        for(int i = 0; i < hauteur; i++){
            positionPixelY = this.acteur.getY() + i;
            tuileSousPosition = (positionPixelX / acteur.getLongTuile()) + (positionPixelY / this.acteur.getLargeTuile() * this.acteur.getNbTuile());
            if(tuileSousPosition % 25 == 0 || acteur.getEnvironnement().getMap().getListTuiles().get(tuileSousPosition) != 0 || collisionEntreActeurs(positionPixelX, positionPixelY)){
                this.acteur.getEnvironnement().getActeurs().remove(this.acteur.getEnvironnement().getJoueur());
                return false;
            }
        }
        this.acteur.getEnvironnement().getActeurs().remove(this.acteur.getEnvironnement().getJoueur());
        return true;
    }

    /**
     * Quand l'Acteur veut aller en haut, il vérifie que tous ses pixels du haut ne rentrent pas en collision avec une tuile "obstacle"
     */
    public boolean collisionHaut() {
        this.acteur.getEnvironnement().addActeurs(this.acteur.getEnvironnement().getJoueur());
        int positionPixelX;
        int positionPixelY = this.acteur.getY() - this.acteur.getVitesse();
        int tuileSousPosition;

        for(int i = 1; i <= longueur; i++){
            positionPixelX = this.acteur.getX() + i;
            tuileSousPosition = (positionPixelX / acteur.getLongTuile()) + (positionPixelY / this.acteur.getLargeTuile() * this.acteur.getNbTuile());
            if(tuileSousPosition >= 350 && tuileSousPosition < 375 || acteur.getEnvironnement().getMap().getListTuiles().get(tuileSousPosition) != 0 || collisionEntreActeurs(positionPixelX, positionPixelY)){
                this.acteur.getEnvironnement().getActeurs().remove(this.acteur.getEnvironnement().getJoueur());
                return false;
            }
        }
        this.acteur.getEnvironnement().getActeurs().remove(this.acteur.getEnvironnement().getJoueur());
        return true;
    }

    /**
     * Quand l'Acteur veut aller en bas, il vérifie que tous ses pixels du bas ne rentrent pas en collision avec une tuile "obstacle"
     */
    public boolean collisionBas() {
        this.acteur.getEnvironnement().addActeurs(this.acteur.getEnvironnement().getJoueur());
        int positionPixelX;
        int positionPixelY = this.acteur.getY() + this.hauteur + this.acteur.getVitesse();
        int tuileSousPosition;

        for(int i = 1; i <= longueur; i++){
            positionPixelX = this.acteur.getX() + i;
            tuileSousPosition = ((this.acteur.getX() + i) / acteur.getLongTuile()) + ((this.acteur.getY()  + this.hauteur ) / this.acteur.getLargeTuile() * this.acteur.getNbTuile());
            if(tuileSousPosition % 25 == 24 || acteur.getEnvironnement().getMap().getListTuiles().get(tuileSousPosition) != 0 || collisionEntreActeurs(positionPixelX, positionPixelY)){
                this.acteur.getEnvironnement().getActeurs().remove(this.acteur.getEnvironnement().getJoueur());
                return false;
            }
        }
        this.acteur.getEnvironnement().getActeurs().remove(this.acteur.getEnvironnement().getJoueur());
        return true;
    }

    /**
     * Vérifie la collision entre l'acteur et les autres acteurs
     */
    private boolean collisionEntreActeurs(int xActeur, int yActeur) {
        for (Acteur autreActeur : this.acteur.getEnvironnement().getActeurs()) {
            if (autreActeur != this.acteur) {
                if (xActeur >= autreActeur.getX() && xActeur <= autreActeur.getX() + autreActeur.getHitBox().getLongueur() &&
                        yActeur >= autreActeur.getY() && yActeur <= autreActeur.getY() + autreActeur.getHitBox().getHauteur()) {
                    return true;
                }
            }
        }
        return false;
    }
}