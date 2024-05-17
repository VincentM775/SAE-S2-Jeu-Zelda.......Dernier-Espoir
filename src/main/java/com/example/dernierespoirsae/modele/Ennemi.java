package com.example.dernierespoirsae.modele;

public  class Ennemi extends Acteur{

    private int attentePourDeplacement = 0;
    private int vitesse = 1; // Vitesse de déplacement de l'ennemi

    public Ennemi(int x, int y, String nom, Environnement environnement, int vie, int nombreDeDegat, int longTuile, int largeTuile, int nbTuile) {
        super(x, y, nom, environnement, vie, nombreDeDegat, longTuile, largeTuile, nbTuile);
    }

    public void seDeplacer(){
        if (this.attentePourDeplacement<=0) {
            seDeplacerAleatoirement();
            this.attentePourDeplacement = 20;
        } else
            this.attentePourDeplacement--;
    }
    /**
     * Méthode permettant le déplacement aléatoire des zombies
     * */
    public void seDeplacerAleatoirement() {
        System.out.println("deplacement ennemi");
        int chanceDeDeplacement = (int) (Math.random() * 100) + 1; //choisi un nombre aléatoire entre 1 et 100 inclus
        int directionAleatoire;
        int nombreDePixelDeplacer=5;

        String nouvelleDirection;

        if (chanceDeDeplacement <= 100) { //TODO la chance que le zombie se déplace est de ** 30% ** (A MODIFIER)
            chanceDeDeplacement = (int) (Math.random() * 100) + 1; //choisi un nombre aléatoire entre 1 et 100 inclus
//          nombreDePixelDeplacer = (int) (Math.random() * 5) + 1;

            if (chanceDeDeplacement <=10){ //10% de chance de changer de direction
                do {
                    directionAleatoire = (int) (Math.random() * 4) + 1; //choisi un nombre aléatoire entre 1 et 4 inclus pour savoir sa direction
                    nouvelleDirection = switch (directionAleatoire) {
                        case 1 -> "up";
                        case 2 -> "right";
                        case 3 -> "down";
                        case 4 -> "left";
                        default -> "null";
                    };
                }while (nouvelleDirection.equals(this.getDirection()));//Tant que la direction est la même, on refait pour avoir une nouvelle direction
                this.setDirection(nouvelleDirection);
            }

            System.out.println("la direction est " + this.getDirection());
            seDeplacerDirection();

        }
    }
    public void seDeplacerDirection() {
        int nombreDePixelDeplacer =10; //(int) (Math.random() * 5) + 1;
        int dx = 0;
        int dy = 0;

        if (getDirection().contains("up")) {
            dy -= this.vitesse;
        }
        if (getDirection().contains("down")) {
            dy += this.vitesse;
        }
        if (getDirection().contains("left")) {
            dx -= this.vitesse;
        }
        if (getDirection().contains("right")) {
            dx += this.vitesse;
        }
        for (int i=0;i<nombreDePixelDeplacer;i++)
            moveCharacter(dx, dy);
    }

    private void moveCharacter(int dx, int dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }
}
