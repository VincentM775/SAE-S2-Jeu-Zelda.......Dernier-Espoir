package com.example.dernierespoirsae.modele;

public class Collision {

    private int longueur;
    private int hauteur;
    Acteur acteur;

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
        int positionPixelX = this.acteur.getX() + acteur.getVitesse() + this.longueur;
        int positionPixelY;
        int tuileSousPosition;

        for(int i = 0; i < hauteur; i++){
            positionPixelY = this.acteur.getY() + i;
            tuileSousPosition = (positionPixelX / acteur.getLongTuile()) + ((positionPixelY) / this.acteur.getLargeTuile() * this.acteur.getNbTuile()); //Calcul de la tuile sur laquelle se trouve le point
            if(tuileSousPosition % 25 == 24 || acteur.getEnvironnement().getMap().getListTuiles().get(tuileSousPosition) != 0 || collisionEntreActeursDroite(positionPixelX, positionPixelY))
                return false;
        }
        return true;
    }

    /**
     * Quand l'Acteur veut aller à gauche, il vérifie que tous ses pixels de gauche ne rentrent pas en collision avec une tuile "obstacle"
     */
    public boolean collisionGauche() {
        int positionPixelX = this.acteur.getX() - this.acteur.getVitesse();
        int positionPixelY;
        int tuileSousPosition;

        for(int i = 0; i < hauteur; i++){
            positionPixelY = this.acteur.getY() + i;
            tuileSousPosition = (positionPixelX / acteur.getLongTuile()) + (positionPixelY / this.acteur.getLargeTuile() * this.acteur.getNbTuile()); //Calcul de la tuile sur laquelle se trouve le point
            if(tuileSousPosition % 25 == 0 || acteur.getEnvironnement().getMap().getListTuiles().get(tuileSousPosition) != 0 || collisionEntreActeursGauche(positionPixelX, positionPixelY))
                return false;
        }
        return true;
    }

    /**
     * Quand l'Acteur veut aller en haut, il vérifie que tous ses pixels du haut ne rentrent pas en collision avec une tuile "obstacle"
     */
    public boolean collisionHaut() {
        int positionPixelX;
        int positionPixelY = this.acteur.getY() - this.acteur.getVitesse();
        int tuileSousPosition;

        for(int i = 1; i <= longueur; i++){
            positionPixelX = this.acteur.getX() + i;
            tuileSousPosition = (positionPixelX / acteur.getLongTuile()) + (positionPixelY / this.acteur.getLargeTuile() * this.acteur.getNbTuile()); //Calcul de la tuile sur laquelle se trouve le point
            if(tuileSousPosition >= 350 && tuileSousPosition < 375 || acteur.getEnvironnement().getMap().getListTuiles().get(tuileSousPosition) != 0 || collisionEntreActeursHaut(positionPixelX, positionPixelY))
                return false;
        }
        return true;
    }

    /**
     * Quand l'Acteur veut aller en bas, il vérifie que tous ses pixels du bas ne rentrent pas en collision avec une tuile "obstacle"
     */
    public boolean collisionBas() {
        int positionPixelX;
        int positionPixelY = this.acteur.getY() + this.hauteur + this.acteur.getVitesse();
        int tuileSousPosition;

        for(int i = 1; i <= hauteur; i++){
            positionPixelX = this.acteur.getX() + i;
            tuileSousPosition = ((this.acteur.getX() + i) / acteur.getLongTuile()) + ((this.acteur.getY()  + this.hauteur ) / this.acteur.getLargeTuile() * this.acteur.getNbTuile()); //Calcul de la tuile sur laquelle se trouve le point
            if(tuileSousPosition % 25 == 24 || acteur.getEnvironnement().getMap().getListTuiles().get(tuileSousPosition) != 0 || collisionEntreActeursBas(positionPixelX, positionPixelY))
                return false;
        }
        return true;
    }

    /**
     * Lorsque l'Acteur va à droite il vérifie que ses points de droite ne rentrent pas en collision avec les points de gauche
     */
    public boolean collisionEntreActeursDroite(int xActeurUn, int yActeurUn){
        for(int i = 0; i < this.acteur.getEnvironnement().getActeurs().size(); i++) {
            for(int w = 0; w < this.acteur.getEnvironnement().getActeurs().get(i).getHitBox().getHauteur() + 2; w++){
                int xActeurDeux = this.acteur.getEnvironnement().getActeurs().get(i).getX();
                int yActeuxDeux = this.acteur.getEnvironnement().getActeurs().get(i).getY() - 1 + w;
                if(xActeurDeux == xActeurUn && yActeuxDeux == yActeurUn)
                    return true;
            }
        }
        return false;
    }

    /**
     * Lorsque l'Acteur va à gauche il vérifie que ses points de gauche ne rentrent pas en collision avec les points de droite
     */
    public boolean collisionEntreActeursGauche(int xActeurUn, int yActeurUn){
        for(int i = 0; i < this.acteur.getEnvironnement().getActeurs().size(); i++) {
            for(int w = 0; w < this.acteur.getEnvironnement().getActeurs().get(i).getHitBox().getHauteur() + 2; w++){
                int xActeurDeux = this.acteur.getEnvironnement().getActeurs().get(i).getX() + this.acteur.getEnvironnement().getActeurs().get(i).getHitBox().getHauteur();
                int yActeuxDeux = this.acteur.getEnvironnement().getActeurs().get(i).getY() -1 + w;
                if(xActeurDeux == xActeurUn && yActeuxDeux == yActeurUn)
                    return true;
            }
        }
        return false;
    }
//    public boolean collisionEntreActeursGauche(int xActeurUn, int yActeurUn){
//        int xActeurDeux;
//        int yActeuxDeux;
//        this.acteur.getEnvironnement().addActeurs(this.acteur.getEnvironnement().getJoueur()); //Ajoute le joueur dans la liste d'acteur
//        for(int i = 0; i < this.acteur.getEnvironnement().getActeurs().size(); i++) {
//            for(int w = 0; w < this.acteur.getEnvironnement().getActeurs().get(i).getHitBox().getHauteur() + 2; w++){
//                xActeurDeux = this.acteur.getEnvironnement().getActeurs().get(i).getX() + this.acteur.getEnvironnement().getActeurs().get(i).getHitBox().getHauteur();
//                yActeuxDeux = this.acteur.getEnvironnement().getActeurs().get(i).getY() -1 + w;
//                if(this.acteur.getEnvironnement().getActeurs().get(i) != this.acteur && xActeurDeux != xActeurUn && yActeuxDeux != yActeurUn) {
//                    this.acteur.getEnvironnement().getActeurs().remove(this.acteur.getEnvironnement().getJoueur());
//                    return true;
//                }
//            }
//        }
//        this.acteur.getEnvironnement().getActeurs().remove(this.acteur.getEnvironnement().getJoueur());
//        return false;
//    }

    /**
     * Lorsque l'Acteur va en haut il vérifie que ses points du haut ne rentrent pas en collision avec les points du bas
     */
    public boolean collisionEntreActeursHaut(int xActeurUn, int yActeurUn){
        for(int i = 0; i < this.acteur.getEnvironnement().getActeurs().size(); i++) {
            for(int w = 0; w < this.acteur.getEnvironnement().getActeurs().get(i).getHitBox().getLongueur() + 2; w++){
                int xActeurDeux = this.acteur.getEnvironnement().getActeurs().get(i).getX() + w;
                int yActeuxDeux = this.acteur.getEnvironnement().getActeurs().get(i).getY() + this.acteur.getEnvironnement().getActeurs().get(i).getHitBox().getLongueur();
                if(xActeurDeux == xActeurUn && yActeuxDeux == yActeurUn)
                    return true;
            }
        }
        return false;
    }

    /**
     * Lorsque l'Acteur va en bas il vérifie que ses points du bas ne rentrent pas en collision avec les points du haut
     */
    public boolean collisionEntreActeursBas(int xActeurUn, int yActeurUn){
        for(int i = 0; i < this.acteur.getEnvironnement().getActeurs().size(); i++) {
            for(int w = 0; w < this.acteur.getEnvironnement().getActeurs().get(i).getHitBox().getLongueur() + 2; w++){
                int xActeurDeux = this.acteur.getEnvironnement().getActeurs().get(i).getX() + w;
                int yActeuxDeux = this.acteur.getEnvironnement().getActeurs().get(i).getY();
                if(xActeurDeux == xActeurUn && yActeuxDeux == yActeurUn)
                    return true;
            }
        }
        return false;
    }
}