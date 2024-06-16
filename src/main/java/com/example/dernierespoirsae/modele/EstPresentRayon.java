package com.example.dernierespoirsae.modele;

public class EstPresentRayon {
    public static boolean estPresentDansRayonPixel(int rayonPixel,int xActeur1,int yActeur1,int longBoxActeur1,int largeBoxActeur1,int xActeur2,int yActeur2){
        //On récupère les numéros de ligne et de colonne sur la map
        int aX = xActeur2 + longBoxActeur1/2;
        int aY = yActeur2 + largeBoxActeur1/2;

        //On renvoie true si les coordonnées x et y entrée en paramètre se trouve dans la portée de l'acteur
        return (Math.abs(xActeur1-aX)<=rayonPixel && Math.abs(yActeur1-aY)<=rayonPixel);
    }
}
