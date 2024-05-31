package com.example.dernierespoirsae.algo;

import com.example.dernierespoirsae.modele.Acteur;
import com.example.dernierespoirsae.modele.Ennemi;
import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Terrain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.util.*;

public class BFS {
    private Environnement environnement;
    private int[][] tableauDesDistances;

    public BFS(Environnement environnement) {
        this.environnement = environnement;
        this.tableauDesDistances = new int[19][35];
        lancementBFS();
    }

    public void lancementBFS(){
        int val;
        int tuileJoueurX= this.environnement.getJoueur().getY()/this.environnement.getInfoTuile()[0]*this.environnement.getInfoTuile()[2];
        int tuileJoueurY=this.environnement.getJoueur().getX()/this.environnement.getInfoTuile()[0]*this.environnement.getInfoTuile()[1];
        //Recupère la position dans la liste du joueur
        int tuileJoueurDansLaListe =
                this.environnement.getJoueur().getY()/this.environnement.getInfoTuile()[0]*this.environnement.getInfoTuile()[2]
                +this.environnement.getJoueur().getX()/this.environnement.getInfoTuile()[0]*this.environnement.getInfoTuile()[1];

        int tuileHautGauche = tuileJoueurDansLaListe-35*this.environnement.getInfoTuile()[1]-9;

        if (tuileHautGauche<0)
            tuileHautGauche=0;
        System.out.println("tuile haut gauche : "+tuileHautGauche);
        System.out.println("nombre total de tuile : "+this.environnement.getMap().getListTuiles().size());
        val = tuileHautGauche;
        for (int ligne = 0; ligne < 19; ligne++) {
            for (int colonne = 0; colonne <35; colonne++) {
                if (this.environnement.getMap().getListTuiles().get(val) == 0) {
                    this.tableauDesDistances[ligne][colonne] = -1; //case où il peut aller
                }else
                    this.tableauDesDistances[ligne][colonne] = -2; //les murs
                val++;
            }
        }
        algoBFS(this.environnement.getJoueur().getY() / this.environnement.getInfoTuile()[0], this.environnement.getJoueur().getX() / this.environnement.getInfoTuile()[0], 0);

    }

    public void algoBFS(int ligne, int colonne, int value) {
        // Vérifier les limites de la grille
        if (ligne < 0 || ligne >= this.tableauDesDistances.length || colonne < 0 || colonne >= this.tableauDesDistances[0].length) {
            return;
        }

        // Vérifier si la case est -1 et inférieur ou égal à value
        if (this.tableauDesDistances[ligne][colonne] != -1 && this.tableauDesDistances[ligne][colonne] <= value) {
            return;
        }
        // Mettre à jour la valeur de la case courante
        this.tableauDesDistances[ligne][colonne] = value;

        // Définir les directions (droite, gauche, bas, haut)
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // Parcourir toutes les directions
        for (int[] direction : directions) {
            int newLigne = ligne + direction[0];
            int newColonne = colonne + direction[1];
            algoBFS(newLigne, newColonne, value + 1);
        }
    }
    /**
     * Retourne le tableau des distances
    * */
    public int[][] getTableauDesDistances() {
        return tableauDesDistances;
    }

    public void tabRempliSimple(){
        //On crée le tableau des distances
        for (int ligne = 0; ligne < environnement.getInfoTuile()[2]; ligne++) {
            for (int colonne = 0; colonne < environnement.getInfoTuile()[1]; colonne++) {
                this.tableauDesDistances[ligne][colonne] = -1;
            }
        }
    }
}
