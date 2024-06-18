package com.example.dernierespoirsae.modele.Objets.Projectile;

import com.example.dernierespoirsae.modele.Acteurs.Joueur;
import com.example.dernierespoirsae.modele.Environnement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CocktailMTest {
    private Environnement env;
    private CocktailM cocktail;
    @BeforeEach
    void setUp() {
        Environnement environnement = new Environnement(32,100,100);
        Joueur joueur = new Joueur(environnement,100,100,10000);
        environnement.setJoueur(joueur);
        this.env = environnement;
        CocktailM cocktail = new CocktailM(5,environnement,environnement.getJoueur());
        this.cocktail = cocktail;
    }

    @Test
    void valDansListe() {
        assertEquals(3646,this.cocktail.valDansListe(2,2),"renvoi la valeur de la tuile dans la liste de 2 ligne et de 2 colonne après le joueur");
        //Comme le joueur est en ligne 1408, colonne 1088, cela renvoie 3646, si le joueur avait été en ligne 0, colonne 0, la méthode renverrait 202

        assertEquals(3444,this.cocktail.valDansListe(0,0),"renvoi la valeur de la tuile où est le joueur");
    }
}