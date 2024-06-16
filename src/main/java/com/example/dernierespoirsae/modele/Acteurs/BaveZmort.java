package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.modele.Objets.Armes.Bave;
import com.example.dernierespoirsae.modele.Objets.Projectile.BalleBave;
import com.example.dernierespoirsae.modele.Objets.Projectile.Projectile;
import com.example.dernierespoirsae.modele.Environnement;

public class BaveZmort extends Ennemi {
    private int enregistrementVitesseJoueur;
    public BaveZmort(int x, int y, Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(x, y, "BaveZmort", environnement, 60, 1, 30, longTuile, largeTuile, nbTuile, 6+(int) (Math.random()*2),28,28);
        enregistrementVitesseJoueur = getEnvironnement().getJoueur().getVitesse();
    }
    public void seDeplacer() {
        Bave bave;
        int tuileOuIlEstAvant = (getY()/getEnvironnement().getInfoTuile()[0])*getEnvironnement().getInfoTuile()[1]+(getX()/getEnvironnement().getInfoTuile()[0]);
        int tuileOuIlEst;

        super.seDeplacer();

        if (joueurPresent()) { //Si un joueur est présent dans la portée de l'ennemi
            prochaineDirection(getX(), getY()); //Grace au BFS, on cherche la prochaine direction et la set automatiquement
        }
        else { //Sinon il bouge aléatoirement
            if (getAttentePourDeplacement() <= 0) {
                seDeplacerAleatoirement();
                setAttentePourDeplacement(30);
            }
            else{
                setAttentePourDeplacement(getAttentePourDeplacement()-1);
            }

            if (getDeplacementRestant() > 0) {
                deplacement(getVitesse());
                setDeplacementRestant((getDeplacementRestant()-(Math.abs(getDx()) + Math.abs(getDy()))));
            }
        }

        tuileOuIlEst = (getY()/getEnvironnement().getInfoTuile()[0])*getEnvironnement().getInfoTuile()[1]+(getX()/getEnvironnement().getInfoTuile()[0]);

        if (tuileOuIlEst!=tuileOuIlEstAvant) {
            bave = new Bave(getEnvironnement(), this);
            getEnvironnement().addBave(bave);
        }

    }           

    @Override
    public void enleverEffet() {
        getEnvironnement().getJoueur().setVitesse(this.enregistrementVitesseJoueur);
        for (int i=(getEnvironnement().getListBave().size()-1);i>=0;i--){
            getEnvironnement().getListBave().remove(i);
        }
    }

    @Override
    public void agit() {
        seDeplacer();
        attaque(getEnvironnement().getTemps());
        joueurDansBave();
    }

    public void attaque(int temps){
        Projectile balleBave;
        if (joueurPresent()){
            if (temps % 30==0 ){
                balleBave = new BalleBave(getNombreDeDegat(),getEnvironnement(),this);
                getEnvironnement().addProjectile(balleBave);
            }
        }

    }
    public void joueurDansBave(){
        if (detectJoueurDansBave()){
            getEnvironnement().getJoueur().setVitesse((this.enregistrementVitesseJoueur/3));
            getEnvironnement().getJoueur().perdPV(1);
        }
        else{
            getEnvironnement().getJoueur().setVitesse(this.enregistrementVitesseJoueur);
        }
    }
    public boolean detectJoueurDansBave(){
        int caseJx = getEnvironnement().getJoueur().getX()/getEnvironnement().getInfoTuile()[0]; //Coord x du joueur dans la liste
        int caseJy = getEnvironnement().getJoueur().getY()/getEnvironnement().getInfoTuile()[0]; //Coord y du joueur dans la liste
        int tuileJoueurDansListe = caseJy*getEnvironnement().getInfoTuile()[1]+caseJx; //emplacement du joueur dans la liste
        int tuileCase1DansListe;
        int tuileCase2DansListe;
        int tuileCase3DansListe;

        boolean flag=false;

        if (getEnvironnement().getListBave().size()>1) {

            if (getEnvironnement().getListBave().size() >= 2) {
                tuileCase1DansListe = getEnvironnement().getListBave().get(1).getLigne() * getEnvironnement().getInfoTuile()[1] + getEnvironnement().getListBave().get(1).getColonne(); //emplacement de la case1 dans la liste

                if (getEnvironnement().getListBave().size() >= 3) {
                    tuileCase2DansListe = getEnvironnement().getListBave().get(2).getLigne() * getEnvironnement().getInfoTuile()[1] + getEnvironnement().getListBave().get(2).getColonne(); //emplacement de la case2 dans la liste

                    if (getEnvironnement().getListBave().size() >= 4) {
                        tuileCase3DansListe = getEnvironnement().getListBave().get(3).getLigne() * getEnvironnement().getInfoTuile()[1] + getEnvironnement().getListBave().get(3).getColonne(); //emplacement de la case2 dans la liste

                        if (tuileJoueurDansListe == tuileCase3DansListe) {
                            flag = true;
                        }
                    }
                    if (tuileJoueurDansListe == tuileCase2DansListe){
                        flag=true;
                    }
                }
                if (tuileJoueurDansListe == tuileCase1DansListe){
                    flag=true;
                }
            }
        }
        return flag;
    }
}
