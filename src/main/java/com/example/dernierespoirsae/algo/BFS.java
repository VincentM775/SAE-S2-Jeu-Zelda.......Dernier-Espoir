package com.example.dernierespoirsae.algo;

import com.example.dernierespoirsae.modele.Environnement;

public class BFS {
    private Environnement environnement;
    private int[][] tableauDesDistances;
    private int xDebutTab;
    private int yDebutTab;
    
    public BFS(Environnement environnement) {
        this.environnement = environnement;
        this.tableauDesDistances = new int[20][34];
        lancementBFS();
    }

    public void lancementBFS(){

        int valTuile;
        int tuileJcolonne = environnement.getJoueur().getX()/environnement.getInfoTuile()[0];
        int tuileJligne = environnement.getJoueur().getY()/environnement.getInfoTuile()[0];
        int tuileJcolonneDansNvTab;
        int tuileJligneDansNvTab;

        if (tuileJcolonne<=16){
            xDebutTab = 0;
            if (tuileJligne <=9){
                yDebutTab = 0;
            }
            else if (environnement.getInfoTuile()[2]-tuileJligne <=9) {
                yDebutTab = environnement.getInfoTuile()[2]-19;
            }
            else {
                yDebutTab = tuileJligne - 9;
            }
        }
        else if (environnement.getInfoTuile()[1]-tuileJcolonne <=16) {
            xDebutTab = environnement.getInfoTuile()[1]-33;

            if (tuileJligne <=9){
                yDebutTab = 0;
            }
            else if (environnement.getInfoTuile()[2]-tuileJligne <=9) {
                yDebutTab = environnement.getInfoTuile()[2]-19;
            }
            else {
                yDebutTab = tuileJligne - 9;
            }
        }
        else{
            xDebutTab = tuileJcolonne - 16;
            if (tuileJligne <=9){
                yDebutTab = 0;
            }
            else if (environnement.getInfoTuile()[2]-tuileJligne <=9) {
                yDebutTab = environnement.getInfoTuile()[2]-19;
            }
            else {
                yDebutTab = tuileJligne - 9;
            }

        }

        valTuile = yDebutTab*environnement.getInfoTuile()[1]+xDebutTab; //Ici la valeur de la premiere tuile du tableau

        for (int ligne=0;ligne<=19;ligne++){
            for (int colonne=0;colonne<=33;colonne++){

                if (this.environnement.getMap().getListTuiles().get(valTuile) == 0)
                    this.tableauDesDistances[ligne][colonne] = -1; //case où il peut aller

                else {this.tableauDesDistances[ligne][colonne] = -2; } //les murs

                valTuile++;
            }
            valTuile = valTuile + environnement.getInfoTuile()[1]-34;
        }

        tuileJcolonneDansNvTab = tuileJcolonne-xDebutTab;
        tuileJligneDansNvTab = tuileJligne-yDebutTab;
        algoBFS(tuileJligneDansNvTab, tuileJcolonneDansNvTab, 0);

    }

    public void algoBFS(int ligne, int colonne, int value) {

        int newLigne;
        int newColonne;

        // Vérifier les limites de la grille
        if (ligne >= 0 && ligne < this.tableauDesDistances.length && colonne >= 0 && colonne < this.tableauDesDistances[0].length) {

            // Vérifier si la case est -1 et inférieur ou égal à value
            if (this.tableauDesDistances[ligne][colonne] == -1 || this.tableauDesDistances[ligne][colonne] > value) {

                // Mettre à jour la valeur de la case courante
                this.tableauDesDistances[ligne][colonne] = value;

                // Définir les directions (droite, gauche, bas, haut)
                int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

                // Parcourir toutes les directions
                for (int[] direction : directions) {

                    newLigne = ligne + direction[0];
                    newColonne = colonne + direction[1];
                    algoBFS(newLigne, newColonne, value + 1);
                }
            }
        }
    }

    /**
     * Retourne le tableau des distances
    * */
    public int[][] getTableauDesDistances() {
        return tableauDesDistances;
    }

    public int getxDebutTab() {
        return xDebutTab;
    }

    public int getyDebutTab() {
        return yDebutTab;
    }
}
