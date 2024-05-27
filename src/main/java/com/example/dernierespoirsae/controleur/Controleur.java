package com.example.dernierespoirsae.controleur;
import com.example.dernierespoirsae.Vue.*;
import com.example.dernierespoirsae.modele.*;
import com.example.dernierespoirsae.modele.Armes.Armes;
import com.example.dernierespoirsae.modele.Armes.Hache;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ResourceBundle;
import java.net.URL;



public class Controleur implements Initializable {
    @FXML
    private TilePane mapPane;
    @FXML
    private Pane persoPane;
    @FXML
    private Pane armePane;

    @FXML
    private Pane inventairePane;
    private Environnement environnement;

    //sert la gameloop :
    private Timeline gameLoop;
    private int temps;

    public void initialize(URL location, ResourceBundle ressource) {

        this.environnement = new Environnement(375);

        Acteur joueur = new Joueur(environnement,(int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns());
        environnement.setJoueur(joueur);

        VueMap map =  new VueMap(environnement.getMap(), this.mapPane);
        map.afficherMap();

        ObservateurArmes observateurArme = new ObservateurArmes(armePane, joueur);
        environnement.setListenerArmes(observateurArme);

        //Creer une hache
        Armes hache = new Hache(60,150);

        //Creer une hache
        Armes hache1 = new Hache(500,300);

        //Creer une hache
        Armes hache2 = new Hache(380,500);

        //Ajoute des haches a l'environnement
        environnement.getListArmes().add(hache);
        environnement.getListArmes().add(hache1);
        environnement.getListArmes().add(hache2);

        //Creer un sprite qui represente le joueur
        VueActeur vueActeur = new VueActeur(joueur, persoPane);

        /*
        ObservateurActeurs est une methode qui va observer les changement (ajout ou supression)
        dans la liste d'acteur de l'environement (qui est une liste Observable)
        */
        ObservateurActeurs observateurActeurs = new ObservateurActeurs(persoPane);

        //Lie l'observateur d'acteur a l'envirenoment
        environnement.setListenerActeurs(observateurActeurs);
        Ennemi acteur1 = new MasticatorZ(360,260, environnement,(int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns());
        acteur1.setVitesse(5); // Exemple : régler la vitesse à 2
        acteur1.setNombreDePixelDeplacer(100); // Exemple : régler la distance à 100 pixels
        environnement.addActeurs(acteur1);

        KeyHandler keyHandler = new KeyHandler(environnement);
        persoPane.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
        persoPane.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);

        initAnimation();
        gameLoop.play();
    }
    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
            // on définit le FPS (nbre de frame par seconde)
            Duration.seconds((0.016)),
            // on définit ce qui se passe à chaque frame
            // c'est un eventHandler d'ou le lambda
            (ev ->{

//                    if(temps==10){
//                        System.out.println("boucle fini");
//                        gameLoop.stop();
//                    }

                int zoneDegat = 5;


                for (int i = 0; i < environnement.getListActeurs().size(); i++) {

                    Rectangle rectangle = (Rectangle) persoPane.lookup("#" + environnement.getListActeurs().get(i).getId());

                    if (temps % 50 == 0) {

                        //verifie si un acteur est dans un rayon de 'zoneDegat' autours du joueur
                        if ((environnement.getJoueur().getY() + rectangle.getWidth() + zoneDegat) >= environnement.getListActeurs().get(i).getY() && ((environnement.getJoueur().getY() - rectangle.getWidth() - zoneDegat) <= environnement.getListActeurs().get(i).getY()) && (environnement.getJoueur().getX() + rectangle.getWidth() + zoneDegat) >= environnement.getListActeurs().get(i).getX() && ((environnement.getJoueur().getX() - rectangle.getWidth() - zoneDegat) <= environnement.getListActeurs().get(i).getX())) {
                            //Enlève 10 pv au Zombie
                            environnement.getListActeurs().get(i).perdPV(10);
                            environnement.getListActeurs().get(i).meurtOuVie();
                        }
                    }
                }
                for (int i = 0; i < environnement.getListArmes().size(); i++) {
                    ImageView imageView = (ImageView) armePane.lookup("#" + environnement.getListArmes().get(i).getId());
                    if ((environnement.getJoueur().getY() + imageView.getFitWidth() + zoneDegat) >= environnement.getListArmes().get(i).getY() && ((environnement.getJoueur().getY() - imageView.getFitWidth() - zoneDegat) <= environnement.getListArmes().get(i).getY()) && (environnement.getJoueur().getX() + imageView.getFitWidth() + zoneDegat) >= environnement.getListArmes().get(i).getX() && ((environnement.getJoueur().getX() - imageView.getFitWidth() - zoneDegat) <= environnement.getListArmes().get(i).getX())) {
                        environnement.getJoueur().getInventaire().getArmes().add(environnement.getListArmes().get(i));
                        environnement.getListArmes().remove(i);
                        System.out.println(environnement.getJoueur().getInventaire().getArmes());
                    }
                }

                environnement.getJoueur().seDeplacer();
                if (temps%3==0){
                    for (Acteur acteur : this.environnement.getListActeurs()) {
                        if (acteur instanceof Ennemi) {
                            ((Ennemi) acteur).seDeplacer();
                        }
                    }
                }

                environnement.getJoueur().seDeplacer();

                temps++;

            })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        persoPane.requestFocus();
    }
}