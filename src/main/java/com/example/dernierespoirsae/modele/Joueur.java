package com.example.dernierespoirsae.modele;

public class Joueur extends Acteur{

    public Joueur(Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(340, 260, "Johnny", environnement, 20, 7, longTuile, largeTuile, nbTuile);
    }

    public void seDeplacer(){
        int x=0,y=0;

        if (this.getDirection().equals("up"))
            y-=5;
        if (this.getDirection().equals("down"))
            y+=5;
        if (this.getDirection().equals("left"))
            x-=5;
        if (this.getDirection().equals("right"))
            x+=5;

        moveCharacter(x,y);
        this.setDirection("null");
    }
    private void moveCharacter(int x, int y) {
        this.setX(this.getX()+x);
        this.setY(this.getY()+y);
    }
}
