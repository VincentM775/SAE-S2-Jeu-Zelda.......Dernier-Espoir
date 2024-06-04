package com.example.dernierespoirsae.controleur;
import com.example.dernierespoirsae.Vue.*;
import com.example.dernierespoirsae.modele.*;
import com.example.dernierespoirsae.modele.Armes.Arme;
import com.example.dernierespoirsae.modele.Armes.Hache;
import com.example.dernierespoirsae.modele.Armes.Pistolet;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ResourceBundle;
import java.net.URL;

public class Controleur implements Initializable {

    @FXML
    private TilePane mapPane;

    @FXML
    private Pane persoPane;

    private VueInventaire vueInventaire;

    @FXML
    private VBox inventairePane;

    @FXML
    private Pane armePaneMap;

    @FXML
    private Pane hache;
    private Environnement environnement;

    //sert la gameloop :
    private Timeline gameLoop;
    private int temps;

    public void initialize(URL location, ResourceBundle ressource) {

        this.environnement = new Environnement(375);


        Acteur joueur = new Joueur(environnement,(int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns());

    /* ObservateurActeurs est une methode qui va observer les changement (ajout ou supression)
     * dans la liste d'acteur de l'environement (qui est une liste Observable)
     */ObservateurActeurs observateurActeurs = new ObservateurActeurs(persoPane);

        //Lie l'observateur d'acteur a l'envirenoment
        environnement.setListenerActeurs(observateurActeurs);
        environnement.setJoueur(joueur);

        this.vueInventaire = new VueInventaire(inventairePane, joueur.getInventaire());

        VueMap map =  new VueMap(environnement.getMap(), this.mapPane);
        map.afficherMap();

        ObservateurArmes observateurArme = new ObservateurArmes(armePaneMap);
        environnement.setListenerArmes(observateurArme);

        //Creer des haches
        Arme hache = new Hache(60,150);
        Arme hache1 = new Hache(80,50);
        Arme hache3 = new Hache(100,200);

        //Creer un pistolet
        Arme pistolet = new Pistolet(500,300);
        Arme pistolet1 = new Pistolet(300,500);
        Arme pistolet2 = new Pistolet(200,300);


        //Ajoute des haches a l'environnement
        environnement.getListArmes().add(hache);
        environnement.getListArmes().add(pistolet);
        environnement.getListArmes().add(hache1);
        environnement.getListArmes().add(hache3);
        environnement.getListArmes().add(pistolet1);
        environnement.getListArmes().add(pistolet2);

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

                //A modifier
               int zoneDegat = 5;

               for (int i = 0; i < environnement.getListActeurs().size(); i++) {

                   Rectangle rectangle = (Rectangle) persoPane.lookup("#" + environnement.getListActeurs().get(i).getId());

                   if (temps % 50 == 0) {

                       //verifie si un acteur est dans un rayon de 'zoneDegat' autours du joueur
                       if ((environnement.getJoueur().getY() + rectangle.getWidth() + zoneDegat) >= environnement.getListActeurs().get(i).getY()
                               && ((environnement.getJoueur().getY() - rectangle.getWidth() - zoneDegat) <= environnement.getListActeurs().get(i).getY())
                               && (environnement.getJoueur().getX() + rectangle.getWidth() + zoneDegat) >= environnement.getListActeurs().get(i).getX()
                               && ((environnement.getJoueur().getX() - rectangle.getWidth() - zoneDegat) <= environnement.getListActeurs().get(i).getX())) {
                           if(environnement.getListActeurs().get(i) != environnement.getJoueur()){
                               //Enlève 10 pv au Zombie
                               environnement.getListActeurs().get(i).perdPV(10);
                               environnement.getListActeurs().get(i).meurtOuVie();
                           }
                       }
                   }
               }
                //A modifier
                for (int i = 0; i < environnement.getListArmes().size(); i++) {

                    ImageView imageView = (ImageView) armePaneMap.lookup("#" + environnement.getListArmes().get(i).getId());

                    if ((environnement.getJoueur().getY() + imageView.getFitWidth() + zoneDegat) >= environnement.getListArmes().get(i).getY()
                       && ((environnement.getJoueur().getY() - imageView.getFitWidth() - zoneDegat) <= environnement.getListArmes().get(i).getY())
                       && (environnement.getJoueur().getX() + imageView.getFitWidth() + zoneDegat) >= environnement.getListArmes().get(i).getX()
                       && ((environnement.getJoueur().getX() - imageView.getFitWidth() - zoneDegat) <= environnement.getListArmes().get(i).getX())) {

                        this.vueInventaire.addViewArmeIventaire(environnement.getListArmes().get(i));
                        environnement.getJoueur().getInventaire().getArmes().add(environnement.getListArmes().get(i));
                        environnement.getListArmes().remove(i);
                    }
                }

               environnement.getJoueur().seDeplacer();
                if (temps%3==0){
                    for (Acteur acteur : this.environnement.getListActeurs()) {
                        if (acteur instanceof Ennemi) {
                          (acteur).seDeplacer();
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