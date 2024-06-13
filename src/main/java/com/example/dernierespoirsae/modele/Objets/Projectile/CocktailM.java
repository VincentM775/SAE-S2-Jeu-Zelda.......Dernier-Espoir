package com.example.dernierespoirsae.modele.Objets.Projectile;

import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import com.example.dernierespoirsae.modele.Environnement;

public class CocktailM extends Projectile{
    public CocktailM(int degats, Environnement environnement, Acteur acteurQuiALancer) {
        super(degats, environnement, acteurQuiALancer, 12, 320);
    }

    @Override
    public int jeVaisEnX() {
        return getEnvironnement().getJoueur().getxDeLaSouris();
    }

    @Override
    public int jeVaisEnY() {
        return getEnvironnement().getJoueur().getyDeLaSouris();
    }

    @Override
    public void effet() {
        //TODO Il faut regarder les 8 cases autour de lui + là où il est et compter celle qui sont le sol et des arbres.
        // Il faut remplacer toute ces cases par de la brulure
        // Mettre un effet de feu côté vue
        // choisir de facon aléatoire entre 1case et le max de cases compter au début et laisser des flammes
        // à ces cases pendant 4000 ms au même endroit.
        // Faire des dégâts quand un acteur va dans les flammes
        // Incrémenter / desincrémenter le nombre de cocktailMolotov dans Joueur
        // et même choses côté vue
    }
}
