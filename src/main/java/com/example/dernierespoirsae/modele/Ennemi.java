package com.example.dernierespoirsae.modele;

public  class Ennemi extends Acteur{




    public Ennemi(int x, int y, String nom, Environnement environnement, int vie, int nombreDeDegat, int longTuile, int largeTuile, int nbTuile) {
        super(x, y, nom, environnement, vie, nombreDeDegat, longTuile, largeTuile, nbTuile);
    }

    public void seDeplacer(){
        seDeplacerAleatoirement();
    }
    /**
     * Méthode permettant le déplacement aléatoire des zombies
     * */
    public void seDeplacerAleatoirement() {
        System.out.println("deplacement ennemi");
        int chanceDeDeplacement = (int) (Math.random() * 100) + 1; //choisi un nombre aléatoire entre 1 et 100 inclus
        int directionAleatoire;
        int nombreDePixelDeplacer;

        String nouvelleDirection;

        if (chanceDeDeplacement <= 5) { //TODO la chance que le zombie se déplace est de ** 5% ** (A MODIFIER)
            chanceDeDeplacement = (int) (Math.random() * 100) + 1; //choisi un nombre aléatoire entre 1 et 100 inclus
            nombreDePixelDeplacer = (int) (Math.random() * 20) + 1;

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
            for (int i=0;i<nombreDePixelDeplacer;i++)
                seDeplacerDirection(this.getDirection());

        }
    }
    public void seDeplacerDirection(String direction){
        switch (this.getDirection()){
            case "up" :
                this.yProperty().setValue(getY()-2);
//                if(acteurQuiSeDeplace.getHitBox())
//                    this.yProperty().setValue(getY()+1);
                break;

            case "right" :
                this.xProperty().setValue(getX()+2);
//                if(!collision(environnement))
//                    this.xProperty().setValue(getX()-1);
                break;

            case "down" :
                this.yProperty().setValue(getY()+2);
//                if(!collision(environnement))
//                    this.yProperty.setValue(getY()-1);
                break;

            case "left" :
                this.xProperty().setValue(getX()-2);
//                if(!collision(environnement))
//                    this.xProperty.setValue(getX()+1);
                break;
        }
    }
}
