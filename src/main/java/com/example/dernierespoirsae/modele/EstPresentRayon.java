package com.example.dernierespoirsae.modele;

public class EstPresentRayon {
    public static boolean estPresentDansRayonPixel(int rayonPixel,int xActeur1,int yActeur1,int xActeur2,int yActeur2){
        //On renvoie true si les coordonnées x et y entrée en paramètre se trouve dans la portée de l'acteur
        return (Math.abs(xActeur1- xActeur2)<=rayonPixel && Math.abs(yActeur1- yActeur2)<=rayonPixel);
    }
}
