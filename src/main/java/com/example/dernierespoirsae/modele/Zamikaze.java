package com.example.dernierespoirsae.modele;

public class Zamikaze extends Zombie{
    private static int compteur=0;
    public Zamikaze(int x, int y, Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(x, y, "MasticatorZ", environnement, 100, 5, 10, longTuile, largeTuile, nbTuile, 8+(int) (Math.random()*2));
    }

    public void explose(int temps){
        int tuileAcolonne;
        int tuileAligne;
        if (compteur == 0) {
            compteur = temps;
            compteur +=75;
        }
        if (temps >= compteur){
            tuileAcolonne = getX()/getEnvironnement().getInfoTuile()[0];
            tuileAligne = getY()/getEnvironnement().getInfoTuile()[0];
//            int tuilePositionEListe = getEnvironnement().getInfoTuile()[1]*tuileAligne+tuileAcolonne; //recupere la position de l'ennemi dans la liste

            for (int y=-1;y<=1;y++){
                for (int x=-1;x<=1;x++){
                    if (tuileAligne+y>=0 && tuileAligne+y < getEnvironnement().getInfoTuile()[1] && tuileAcolonne+x>=0 && tuileAcolonne+x < getEnvironnement().getInfoTuile()[1]){
                        getEnvironnement().getMap().getListTuiles().remove(caseAExploser(y,x)); //case à remplacer selon x et y
                        getEnvironnement().getMap().getListTuiles().add(caseAExploser(y,x),2); //case à remplacer selon x et y
                        System.out.println("explosion");
                        this.perdPV(this.getVie());
                    }
                }
            }
            compteur=0;
        }
    }
    public int caseAExploser(int caseY,int caseX){
        return (((getY()/getEnvironnement().getInfoTuile()[0])+caseY)*getEnvironnement().getInfoTuile()[1]+(getX()/getEnvironnement().getInfoTuile()[0])+caseX);
    }
    public void tuileAExploser(int positionTuile){
        //TODO Méthode qui servira à l'animation de l'explosion, elle prend en paramètre une
        // position et devra changer cette tuile plusieurs fois pour voir une animation.
        //
        // A voir c'est pas aussi simple... il faudrait cette méthode dans le controleur (ou dans un package vue)
        // parce que l'animation doit être fluide et donc être dans la gameLoop
    }
}
