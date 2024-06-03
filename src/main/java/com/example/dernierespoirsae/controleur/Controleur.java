package com.example.dernierespoirsae.controleur;
import com.example.dernierespoirsae.Vue.ObservateurActeurs;
import com.example.dernierespoirsae.Vue.ObservateurPositionX;
import com.example.dernierespoirsae.Vue.ObservateurPositionY;
import com.example.dernierespoirsae.Vue.VueActeur;
import com.example.dernierespoirsae.Vue.VueArmes;
import com.example.dernierespoirsae.algo.BFS;
import com.example.dernierespoirsae.modele.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.fxml.Initializable;
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
    private Pane principalPane;
    private Environnement environnement;

    //sert la gameloop :
    private Timeline gameLoop;
    private int temps;
    private BFS bfs;

    public void initialize(URL location, ResourceBundle ressource) {

        this.environnement = new Environnement(32, 100, 100);

        environnement.getMap().generMap(environnement.getInfoTuile()[1] * environnement.getInfoTuile()[2]);

        this.mapPane.setPrefTileWidth(this.environnement.getInfoTuile()[0]);
        this.mapPane.setPrefTileHeight(this.environnement.getInfoTuile()[0]);
        this.mapPane.setPrefWidth(this.environnement.getInfoTuile()[1] * this.environnement.getInfoTuile()[0]);
        this.mapPane.setPrefHeight(this.environnement.getInfoTuile()[2] * this.environnement.getInfoTuile()[0]);


        Acteur joueur = new Joueur(environnement,(int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns());
        environnement.setJoueur(joueur);

        /*
        ObservateurActeurs est une methode qui va observer les changement (ajout ou supression)
        dans la liste d'acteur de l'environement (qui est une liste Observable)
        */
        ObservateurActeurs observateurActeurs = new ObservateurActeurs(persoPane);
        this.environnement.setJoueur(joueur);
        this.bfs = new BFS(this.environnement);
        this.environnement.setBfs(this.bfs);

        //Lie l'observateur d'acteur a l'envirenoment
        environnement.setListenerActeurs(observateurActeurs);

        //Création d'un premier zombie MasticartorZ
        Ennemi acteur1 = new MasticatorZ(360,260, environnement,(int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns());
        acteur1.setVitesse(8); // Exemple : régler la vitesse à 2
        environnement.addActeurs(acteur1);

        //Création d'un 2e zombie LeZamikaze
        Ennemi acteur2 = new Zamikaze(400,340, environnement,(int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns());
        acteur1.setVitesse(8); // Exemple : régler la vitesse à 2
        environnement.addActeurs(acteur2);


        //Creer un sprite qui represente le joueur
        VueActeur vueActeur = new VueActeur(joueur, persoPane);

        ChangeListener<Number> listenerX = new ObservateurPositionX(principalPane, joueur);
        joueur.xProperty().addListener(listenerX);

        ChangeListener<Number> listenerY = new ObservateurPositionY(principalPane, joueur);
        joueur.yProperty().addListener(listenerY);

        KeyHandler keyHandler = new KeyHandler(environnement);
        persoPane.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
        persoPane.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
        afficherMap(environnement.getMap());
        initAnimation();
        gameLoop.play();
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
            // on définit le FPS (nbre de frame par seconde)
            Duration.seconds((0.040)),
            // on définit ce qui se passe à chaque frame
            // c'est un eventHandler d'ou le lambda
            (ev ->{

//                    if(temps==10){
//                        System.out.println("boucle fini");
//                        gameLoop.stop();
//                    }

                int zoneDegat = 20;

                for (int i = 0; i < environnement.getListActeurs().size(); i++) {

                    Rectangle rectangle = (Rectangle) persoPane.lookup("#" + environnement.getListActeurs().get(i).getId());

                    if (temps % 50 == 0) {
                        //verifie si un acteur est dans un rayon de 'zoneDegat' autours du joueur
                        if ((environnement.getJoueur().getY() + rectangle.getWidth() + zoneDegat) >= environnement.getListActeurs().get(i).getY() && ((environnement.getJoueur().getY() - rectangle.getWidth() - zoneDegat) <= environnement.getListActeurs().get(i).getY()) && (environnement.getJoueur().getX() + rectangle.getWidth() + zoneDegat) >= environnement.getListActeurs().get(i).getX() && ((environnement.getJoueur().getX() - rectangle.getWidth() - zoneDegat) <= environnement.getListActeurs().get(i).getX())) {
                            //Enlève 10 pv au Zombie
                            environnement.getListActeurs().get(i).perdPV(10);
//                            environnement.getListActeurs().get(i).meurtOuVie();
                        }
                    }
                }
                //Code pour l'explosion du LeZamikaze
                for (int i = 0; i < environnement.getListActeurs().size(); i++) {
                    if (environnement.getListActeurs().get(i) instanceof Zamikaze){
                        Rectangle joueur = (Rectangle) persoPane.lookup("#" + environnement.getListActeurs().get(i).getId());

                        //verifie si un acteur est dans un rayon de 'zoneDegat' autours du joueur
                        if ((environnement.getJoueur().getY() + joueur.getWidth() + zoneDegat) >= environnement.getListActeurs().get(i).getY() && ((environnement.getJoueur().getY() - joueur.getWidth() - zoneDegat) <= environnement.getListActeurs().get(i).getY()) && (environnement.getJoueur().getX() + joueur.getWidth() + zoneDegat) >= environnement.getListActeurs().get(i).getX() && ((environnement.getJoueur().getX() - joueur.getWidth() - zoneDegat) <= environnement.getListActeurs().get(i).getX())) {
                            ((Zamikaze) environnement.getListActeurs().get(i)).explose(temps);

                            if (((Zamikaze) environnement.getListActeurs().get(i)).aExploser()){//Si le Zamikaze explose
                                int tuileAcolonne = environnement.getListActeurs().get(i).getX()/environnement.getInfoTuile()[0];
                                int tuileAligne = environnement.getListActeurs().get(i).getY()/environnement.getInfoTuile()[0];
                                int tuilePositionEListe; //recupere la position de l'ennemi dans la liste
                                addGifToPane(environnement.getListActeurs().get(i).getX(),environnement.getListActeurs().get(i).getY(),96,"file:src/main/resources/com/example/dernierespoirsae/images/explosion.gif");

                                for (int y=-1;y<=1;y++){
                                    for (int x=-1;x<=1;x++){
                                        if (tuileAligne+y>=0 && tuileAligne+y < environnement.getInfoTuile()[1] && tuileAcolonne+x>=0 && tuileAcolonne+x < environnement.getInfoTuile()[1]){
                                            tuilePositionEListe = environnement.getInfoTuile()[1]*(tuileAligne+y)+(tuileAcolonne+x);
                                            setImageAtIndex(tuilePositionEListe, "file:src/main/resources/com/example/dernierespoirsae/images/Grass_burned.png");

                                        }
                                    }
                                }
                                ((Zamikaze) environnement.getListActeurs().get(i)).meurt();
                            }
                        }

                    }
                }



                for (int i = 0; i < environnement.getListActeurs().size(); i++) {
                    environnement.getListActeurs().get(i).seDeplacer();
                }

                environnement.getJoueur().seDeplacer();
                temps++;

            })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public void afficherMap(Terrain terrain) {
        Image pelouse = new Image("file:src/main/resources/com/example/dernierespoirsae/images/Grass_02_v2.png");
        Image mur = new Image("file:src/main/resources/com/example/dernierespoirsae/images/mur.png");
        for (int x = 0; x < terrain.getListTuiles().size(); x++) {
            ImageView imageView = new ImageView();
            int tuile = terrain.getListTuiles().get(x);
            switch (tuile) {
                case 0:
                    imageView.setImage(pelouse);
                    imageView.setFitWidth(this.environnement.getInfoTuile()[0]);
                    imageView.setFitHeight(this.environnement.getInfoTuile()[0]);
                    break;

                case 1:
                    imageView.setImage(mur);
                    imageView.setFitWidth(this.environnement.getInfoTuile()[0]);
                    imageView.setFitHeight(this.environnement.getInfoTuile()[0]);
                    break;
            }
            mapPane.getChildren().add(imageView);
        }
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        persoPane.requestFocus();
    }
    public void setImageAtIndex(int index, String imagePath) {
        // Obtenir le nœud à l'index spécifique
        Node node = mapPane.getChildren().get(index);

        // Vérifier si le nœud est bien une instance d'ImageView
        if (node instanceof ImageView) {
            ImageView imageView = (ImageView) node;

            // Définir la nouvelle image
            Image image = new Image(imagePath);
            imageView.setImage(image);
        }
    }
    public void addGifToPane(int x, int y,int taille, String image) {
        // Charger le GIF
        Image gifImage = new Image(image);

        // Créer un ImageView pour contenir le GIF
        ImageView imageView = new ImageView(gifImage);

        // Définir la position et la taille du ImageView
        imageView.setX((int) x-((double) taille /2));
        imageView.setY(y-taille+15+15);
        imageView.setFitWidth(taille);
        imageView.setFitHeight(taille);
        // Ajouter le ImageView au Pane
        persoPane.getChildren().add(imageView);
        persoPane.getChildren().remove(persoPane.lookup("#"+imageView.getId()));



    }



}