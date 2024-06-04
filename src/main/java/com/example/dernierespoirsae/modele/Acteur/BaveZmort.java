package com.example.dernierespoirsae.modele.Acteur;

import com.example.dernierespoirsae.modele.Armes.Balle;
import com.example.dernierespoirsae.modele.Environnement;

public class BaveZmort extends Zombie {
    public BaveZmort(int x, int y, Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(x, y, "BaveZmort", environnement, 60, 2, 5, longTuile, largeTuile, nbTuile, 6+(int) (Math.random()*2));

    }

    public void attaque(int temps){
        Balle bave;
        if (joueurPresent()){
            if (temps % 10==0 ){
                bave = new Balle(getNombreDeDegat(),getX() + (15 / 2),getY() + (15 / 2) ,getEnvironnement());
                getEnvironnement().addProjectile(bave);
            }
        }
        for(int i=0;i< getEnvironnement().getListProjectile().size();i++) {
            if (getEnvironnement().getListProjectile().get(i) instanceof Balle) {
                boolean val = getEnvironnement().getListProjectile().get(i).avance();
                if (getEnvironnement().getListProjectile().get(i).testProjectileArriverSurJoueur() || !val) {
                    getEnvironnement().getListProjectile().remove(i);
                }
            }
        }

    }

    public void trainerBave(){
        //TODO le zombie laisse de la bave derrière lui sur une portée de 2 case qui fait ralentir et perdre 1 de dégât
    }


}
