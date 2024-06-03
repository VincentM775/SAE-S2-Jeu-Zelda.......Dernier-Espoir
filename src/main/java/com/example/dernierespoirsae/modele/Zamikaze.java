package com.example.dernierespoirsae.modele;

public class Zamikaze extends Zombie{
    private static int compteur=0;
    private boolean aExplosee=false; //Par défaut, il n'a pas explosé (0 pas explosé, 1 explosé)
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
            for (int y=-1;y<=1;y++){
                for (int x=-1;x<=1;x++){
                    if (tuileAligne+y>=0 && tuileAligne+y < getEnvironnement().getInfoTuile()[1] && tuileAcolonne+x>=0 && tuileAcolonne+x < getEnvironnement().getInfoTuile()[1]){
                        getEnvironnement().getMap().getListTuiles().remove(caseAExploser(y,x)); //case à remplacer selon x et y
                        getEnvironnement().getMap().getListTuiles().add(caseAExploser(y,x),2); //case à remplacer selon x et y
                        this.aExplosee=true;
//                        getEnvironnement().getJoueur().perdPV(1000); //Le joueur prend des dégâts
                    }
                }
            }
            compteur=0;
        }
    }
    public int caseAExploser(int caseY,int caseX){
        return (((getY()/getEnvironnement().getInfoTuile()[0])+caseY)*getEnvironnement().getInfoTuile()[1]+(getX()/getEnvironnement().getInfoTuile()[0])+caseX);
    }

    public void meurt() {
        this.perdPV(this.getVie()); //l'acteur meurt
    }

    public boolean aExploser(){
        return this.aExplosee;
    }
}
