package com.example.dernierespoirsae.modele;

import com.example.dernierespoirsae.algo.BFS;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Ennemi extends Acteur {

    private int attentePourDeplacement = 0;
    private int nombreDePixelDeplacer = 10; // Distance totale à parcourir en pixels
    private int dx = 0;
    private int dy = 0;
    private int deplacementRestant = 0;

    public Ennemi(int x, int y, String nom, Environnement environnement, int vie, int vitesse, int nombreDeDegat, int longTuile, int largeTuile, int nbTuile) {
        super(x, y, nom, environnement, vie, vitesse, nombreDeDegat, longTuile, largeTuile, nbTuile,15,15);

    }

    @Override
    public void seDeplacer() {
//        seDeplacerAvecModeDeDeplacement(0);
        seDeplacerAvecModeDeDeplacement(1);

    }
    public void seDeplacerEnBFS(){
        algoBFS(getX(),getY());
    }
    public void algoBFS(int positionX,int positionY){
        String directionchoisi;
        int positionLigne = positionY / getEnvironnement().getInfoTuile()[0];
        int positionColonne = positionX / getEnvironnement().getInfoTuile()[0];

        //coordonnée ancienne qui servent a testé le bon déplacement de l'ennemi.
        int ancienneCooX = getX();
        int ancienneCooY = getY();

        int[][] tabDesDistances = getEnvironnement().getBfs().getTableauDesDistances();
        ArrayList<int[]> cheminOuAller = new ArrayList<>();
        int[][] directions = {{0, 1},{0, -1},{1, 0},{-1, 0}};//right,left,down,up
        int[] directionChoisiTabInt;


        // Parcourir toutes les directions
        for (int[] direction : directions) {
            int newLigne = positionLigne + direction[0];
            int newColonne = positionColonne + direction[1];
            if (newLigne >= 0 && newLigne < tabDesDistances.length && newColonne >= 0 && newColonne < tabDesDistances[0].length) {
                if (tabDesDistances[newLigne][newColonne]==tabDesDistances[positionLigne][positionColonne]-1)
                    cheminOuAller.add(direction);//les directions où aller
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
            deplacement(); //on le fait avancer

            if (ancienneCooX==getX() && ancienneCooY == getY()) { //on regarde qu'il a bien avancer
                positionX = positionX + 15;
                positionY = positionY + 14;
                algoBFS(positionX,positionY); //Pour éviter qu'il rste bloqué on lui donne la positon de son coins opposé
            }
            this.setDirection("null"); //On remet la position à null pour qu'il arrête d'avancer dans la gameLoop
        }
    }
    public void seDeplacerAvecModeDeDeplacement(int modeDeDeplacement){
        if (modeDeDeplacement==0){
            if (this.attentePourDeplacement <= 0) {
                seDeplacerAleatoirement();
                this.attentePourDeplacement = 30;
            } else
                this.attentePourDeplacement--;
            if (deplacementRestant > 0) {
                deplacement();
                deplacementRestant -= Math.abs(dx) + Math.abs(dy);
            }
        }
        if (modeDeDeplacement==1){
            seDeplacerEnBFS();
        }
    }

    private void seDeplacerAleatoirement() {
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
            deplacement();
            this.setDirection("null");
        }
    }
    public void setUneDirection(String direction){
        this.setDirection(direction);
        this.setDerniereDirection(this.getDirection());
    }

    private void deplacement() {
        this.setDirection(this.getDerniereDirection());
        dx = 0;
        dy = 0;
        deplacementRestant = nombreDePixelDeplacer;

        if (getDirection().contains("up") && getHitBox().collisionHaut()){
            dy = -this.getVitesse();
        }
        if (getDirection().contains("down") && getHitBox().collisionBas()) {
            dy = this.getVitesse();
        }
        if (getDirection().contains("left") && getHitBox().collisionGauche()) {
            dx = -this.getVitesse();
        }
        if (getDirection().contains("right") && getHitBox().collisionDroite()) {
            dx = this.getVitesse();
        }
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public void setNombreDePixelDeplacer(int nombreDePixelDeplacer) {
        this.nombreDePixelDeplacer = nombreDePixelDeplacer;
    }

}

