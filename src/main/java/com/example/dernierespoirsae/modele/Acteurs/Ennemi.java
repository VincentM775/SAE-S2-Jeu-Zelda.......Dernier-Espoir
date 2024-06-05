package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.modele.Environnement;
import java.util.ArrayList;

public class Ennemi extends Acteur {

    private int attentePourDeplacement = 0;
    private int nombreDePixelDeplacer = 10; // Distance totale à parcourir en pixels
    private int dx = 0;
    private int dy = 0;
    private int deplacementRestant = 0;
    private int porteeDeVue;

    public Ennemi(int x, int y, String nom, Environnement environnement, int vie, int vitesse, int nombreDeDegat, int longTuile, int largeTuile, int nbTuile, int porteeDeVue) {
        super(x, y, nom, environnement, vie, vitesse, nombreDeDegat, longTuile, largeTuile, nbTuile,15,15);
        this.porteeDeVue = porteeDeVue;
    }

    @Override
    public boolean seDeplacer() {
        if (joueurPresent()){
            //Si un joueur est présent dans la portée de l'ennemi
            prochaineDirection(getX(),getY()); //Grace au BFS, on cherche la prochaine direction et la set automatiquement
        }
        else { //Sinon il bouge aléatoirement
            if (this.attentePourDeplacement <= 0) {
                seDeplacerAleatoirement();
                this.attentePourDeplacement = 30;
            } else
                this.attentePourDeplacement--;

            if (deplacementRestant > 0) {
                deplacement(getVitesse());
                deplacementRestant -= Math.abs(dx) + Math.abs(dy);
            }
        }
        return true;
    }
    public void suivreJoueurDansMemeCase(){
        int deltaX = getEnvironnement().getJoueur().getX() - getX(); //Calcul en X  la différence entre le x du joueur et x de l'ennemi
        int deltaY = getEnvironnement().getJoueur().getY() - getY(); //Calcul en Y  la différence entre le y du joueur et y de l'ennemi

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (deltaX > 0) {
                setUneDirection("right");
            }
            else {
                setUneDirection("left");
            }
        }
        else {
            if (deltaY > 0) {
                setUneDirection("down");
            }
            else {
                setUneDirection("up");
            }
        }
    }
    public void prochaineDirection(int positionX,int positionY){
        String directionchoisi;

        int positionLigne = positionY / getEnvironnement().getInfoTuile()[0];
        int positionColonne = positionX / getEnvironnement().getInfoTuile()[0];

        //coordonnée ancienne qui servent a testé le bon déplacement de l'ennemi.
        int ancienneCooX = getX();
        int ancienneCooY = getY();

        int tuileEcolonneDansNvTab = positionColonne-getEnvironnement().getBfs().getxDebutTab();
        int tuileEligneDansNvTab = positionLigne-getEnvironnement().getBfs().getyDebutTab();

        int newLigne;
        int newColonne;

        int[][] tabDesDistances = getEnvironnement().getBfs().getTableauDesDistances();
        ArrayList<int[]> cheminOuAller = new ArrayList<>();
        int[][] directions = {{0, 1},{0, -1},{1, 0},{-1, 0}};//right,left,down,up
        int[] directionChoisiTabInt;


        // Parcourir toutes les directions
        for (int[] direction : directions) {

            newLigne = tuileEligneDansNvTab + direction[0];
            newColonne = tuileEcolonneDansNvTab + direction[1];

            if (newLigne >= 0 && newLigne < tabDesDistances.length && newColonne >= 0 && newColonne < tabDesDistances[0].length) {
                 if (tabDesDistances[newLigne][newColonne]==tabDesDistances[tuileEligneDansNvTab][tuileEcolonneDansNvTab]-1) {
                    cheminOuAller.add(direction);//les directions où aller
                }
            }
        }
        if (!cheminOuAller.isEmpty()) { //Si l'ennemi a trouvé un chemin
            //Choisi une direction aléatoire entre toutes celle disponible
            directionChoisiTabInt = cheminOuAller.get((int) (Math.random() * cheminOuAller.size()));

            //converti le tab en string
            if (directionChoisiTabInt[0]==0 && directionChoisiTabInt[1]==1)
                directionchoisi = "right";
            else if (directionChoisiTabInt[0]==0 && directionChoisiTabInt[1]==-1)
                directionchoisi = "left";
            else if (directionChoisiTabInt[0]==1 && directionChoisiTabInt[1]==0)
                directionchoisi = "down";
            else
                directionchoisi = "up";

            setUneDirection(directionchoisi); //On définit la nouvelle direction (chemin vers le joueur)
            deplacement(getVitesse()); //on le fait avancer

            if (ancienneCooX==getX() && ancienneCooY == getY()) { //on regarde qu'il a bien avancer
                positionX = positionX + 15;
                positionY = positionY + 14;
                prochaineDirection(positionX,positionY); //Pour éviter qu'il reste bloqué, on lui donne la position de son coin opposé
            }
            this.setDirection("null"); //On remet la position à null pour qu'il arrête d'avancer dans la gameLoop
        }
        else if (tabDesDistances[tuileEligneDansNvTab][tuileEcolonneDansNvTab]==0) {//Si on est sur la même case que le joueur
            suivreJoueurDansMemeCase();
            deplacement(1);
        }
    }
    public void seDeplacerAleatoirement() {
        int chanceDeDeplacement = (int) (Math.random() * 100) + 1;
        int chanceDeNouvelleDirection = (int) (Math.random() * 100) + 1;
        int directionAleatoire;
        String nouvelleDirection;

        if (chanceDeDeplacement <=100) { //75% de chance de se déplacer
            if (chanceDeNouvelleDirection <= 75) { //75% de chance de changer de direction
                do {//Tant que l'on a pas changé de direction
                    directionAleatoire = (int) (Math.random() * 4) + 1;
                    nouvelleDirection = switch (directionAleatoire) {
                        case 1 -> "up";
                        case 2 -> "right";
                        case 3 -> "down";
                        case 4 -> "left";
                        default -> "null";
                    };
                }while (nouvelleDirection.equals(this.getDerniereDirection()));
                setUneDirection(nouvelleDirection);
            }
            //sinon il se déplace dans la meme direction
            deplacement(getVitesse());
            this.setDirection("null");
        }
    }
    public void setUneDirection(String direction){
        this.setDirection(direction);
        this.setDerniereDirection(this.getDirection());
    }

    public void deplacement(int vitesse) {
        this.setDirection(this.getDerniereDirection());
        dx = 0;
        dy = 0;
        deplacementRestant = nombreDePixelDeplacer;

        if (getDirection().contains("up") && getHitBox().collisionHaut(vitesse)){
            dy = -vitesse;
        }
        if (getDirection().contains("down") && getHitBox().collisionBas(vitesse)) {
            dy = vitesse;
        }
        if (getDirection().contains("left") && getHitBox().collisionGauche(vitesse)) {
            dx = -vitesse;
        }
        if (getDirection().contains("right") && getHitBox().collisionDroite(vitesse)) {
            dx = vitesse;
        }
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public boolean joueurPresent(){
        //On récupère les numéros de ligne et de colonne sur la map
        int aColonne = getX()/getEnvironnement().getInfoTuile()[0];
        int aLigne = getY()/getEnvironnement().getInfoTuile()[0];

        //On renvoie true si le joueur se trouve dans la portée de l'ennemi
        return (Math.abs(getEnvironnement().getJoueur().getX()/getEnvironnement().getInfoTuile()[0]-aColonne)<=this.porteeDeVue
            &&Math.abs(getEnvironnement().getJoueur().getY()/getEnvironnement().getInfoTuile()[0]-aLigne)<=this.porteeDeVue);
    }

    public int getAttentePourDeplacement() {
        return attentePourDeplacement;
    }

    public int getDeplacementRestant() {
        return deplacementRestant;
    }

    public void setAttentePourDeplacement(int attentePourDeplacement) {
        this.attentePourDeplacement = attentePourDeplacement;
    }

    public void setDeplacementRestant(int deplacementRestant) {
        this.deplacementRestant = deplacementRestant;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}