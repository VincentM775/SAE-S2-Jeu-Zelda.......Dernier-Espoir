package com.example.dernierespoirsae.modele;

public class Zombie extends Ennemi{

    public Zombie(int x, int y, String nom, Environnement environnement, int vie, int nombreDeDegat, int longTuile, int largeTuile, int nbTuile) {
        super(x, y, nom, environnement, vie, nombreDeDegat, longTuile, largeTuile, nbTuile);
    }
    /**
    * Méthode permettant le déplacement aléatoire des zombies
    * */
    public void seDeplacerAleatoirement() {
        int chanceDeDeplacement = (int) (Math.random() * 100) + 1; //choisi un nombre aléatoire entre 1 et 100 inclus
        int directionAleatoire;
        String direction = null;

        if (chanceDeDeplacement <= 100) { //TODO la chance que le zombie se déplace est de ** 100% ** (A MODIFIER)
            directionAleatoire = (int) (Math.random() * 4) + 1; //choisi un nombre aléatoire entre 1 et 4 inclus pour savoir sa direction
            direction = switch (directionAleatoire) {
                case 1 -> "up";
                case 2 -> "right";
                case 3 -> "down";
                case 4 -> "left";
                default -> direction;
            };
            if (direction != null) {
                System.out.println("la direction est " + direction);
                for (int i = 0; i < 3; i++) //avance de 3fois d'un coup
                    super.seDeplacer(direction);
            }
        }
    }
}
