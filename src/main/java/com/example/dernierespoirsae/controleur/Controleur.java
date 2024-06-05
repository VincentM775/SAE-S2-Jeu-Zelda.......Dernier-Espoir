package com.example.dernierespoirsae.controleur;
import com.example.dernierespoirsae.algo.BFS;
import com.example.dernierespoirsae.Vue.*;
import com.example.dernierespoirsae.modele.*;
import com.example.dernierespoirsae.modele.Acteurs.*;
import com.example.dernierespoirsae.modele.Armes.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
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
    private TilePane terrainPane;
    @FXML
    private Pane persoPane;
    @FXML
    private Pane principalPane;
    private VueInventaire vueInventaire;
    @FXML
    private VBox inventairePane;
    @FXML
    private Pane armePaneMap;
    @FXML
    private Pane projectilePane;
    private Environnement environnement;
    private Timeline gameLoop;
    private int temps;
    private BFS bfs;

    public void initialize(URL location, ResourceBundle ressource) {

        //creation de l'environement
        this.environnement = new Environnement(32, 100, 100);

        //Definition de la taille des tuiles du FXML dépendamment de celles indiquées lors de la création de l'environnement
        this.terrainPane.setPrefTileWidth(this.environnement.getInfoTuile()[0]);
        this.terrainPane.setPrefTileHeight(this.environnement.getInfoTuile()[0]);

        //Definition du nombre de colonnes et nombres de ligne du Terrain, dépendamment de celles indiquées lors de la création de l'environnement
        this.terrainPane.setPrefWidth(this.environnement.getInfoTuile()[1] * this.environnement.getInfoTuile()[0]);
        this.terrainPane.setPrefHeight(this.environnement.getInfoTuile()[2] * this.environnement.getInfoTuile()[0]);

        //Creation du joueur
        Acteur joueur = new Joueur(environnement,(int) this.terrainPane.getPrefTileWidth(), (int) this.terrainPane.getPrefTileHeight(), this.terrainPane.getPrefColumns());

        /* ObservateurActeurs est une methode qui va observer les changement (ajout ou supression)
        *  dans la liste d'acteur de l'environement (qui est une liste Observable) */
        ObservateurActeurs observateurActeurs = new ObservateurActeurs(persoPane);

        //Lie l'observateur d'acteur a l'environnement
        environnement.setListenerActeurs(observateurActeurs);

        //Ajout du joueur a l'environnement
        environnement.setJoueur(joueur);

        //Initialisation de la vueInventaire
        this.vueInventaire = new VueInventaire(inventairePane);

        //Initialisation de la vue Terrain
        VueTerrain terrain =  new VueTerrain(environnement.getTerrain(), this.terrainPane);

        //Initialise un observateur pour une liste d'arme
        ObservateurArmes observateurArme = new ObservateurArmes(armePaneMap);

        //Lie cet observateur a la liste d'arme dans l'environnement
        environnement.setListenerArmes(observateurArme);

        //Creer des haches
        Arme hache = new Hache(60,150);
        Arme hache1 = new Hache(80,50);
        Arme hache3 = new Hache(100,200);

        //Creer un pistolet
        Arme pistolet = new Pistolet(800,300);
        Arme pistolet1 = new Pistolet(300,1000);
        Arme pistolet2 = new Pistolet(900,300);

        //Ajoute les armes a l'environnement
        environnement.getListArmes().add(hache);
        environnement.getListArmes().add(pistolet);
        environnement.getListArmes().add(hache1);
        environnement.getListArmes().add(hache3);
        environnement.getListArmes().add(pistolet1);
        environnement.getListArmes().add(pistolet2);

        //Génére un terrain avec des tuile aléatoire
        environnement.getTerrain().generTerrain(environnement.getInfoTuile()[1] * environnement.getInfoTuile()[2]);

        //Affiche le terrain
        terrain.afficherTerrain();

        //Création d'un premier zombie MasticartorZ
        Ennemi acteur1 = new MasticatorZ(360,260, environnement,(int) this.terrainPane.getPrefTileWidth(), (int) this.terrainPane.getPrefTileHeight(), this.terrainPane.getPrefColumns());

        //Création d'un 2e zombie LeZamikaze
        Ennemi acteur2 = new Zamikaze(400,340, environnement,(int) this.terrainPane.getPrefTileWidth(), (int) this.terrainPane.getPrefTileHeight(), this.terrainPane.getPrefColumns());
        environnement.addActeurs(acteur2);

        //Création d'un 3e zombie le Bave-Zmort
        Ennemi acteur3 = new BaveZmort(400,340, environnement,(int) this.terrainPane.getPrefTileWidth(), (int) this.terrainPane.getPrefTileHeight(), this.terrainPane.getPrefColumns());
        environnement.addActeurs(acteur3);

        //Créer le lien entre la liste Des Projectiles et la class observableProjectile
        environnement.getListProjectile().addListener(new ObservateurProjectile(this.projectilePane,environnement));

        //Créer le lien entre la liste Des flaques de baves et la class observableBave
        environnement.getListBave().addListener(new ObservateurTrainerBave(this.environnement,this.terrainPane));

        ChangeListener<Number> listenerX = new ObservateurPositionX(principalPane, joueur);
        joueur.xProperty().addListener(listenerX);

        ChangeListener<Number> listenerY = new ObservateurPositionY(principalPane, joueur);
        joueur.yProperty().addListener(listenerY);

        //Initialialisation du keyHandler pour les gestions des entrée clavier utilisateur
        KeyHandler keyHandler = new KeyHandler(environnement);
        persoPane.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
        persoPane.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);

        //Initialisation du BFS
        this.bfs = new BFS(this.environnement);

        //Ajout du BFS dans l'environnement
        this.environnement.setBfs(this.bfs);

        //Demarrage de la gameLoop
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

                //A modifier
               int rayonInteraction = 5;//Nombre de pixel

               for (int i = 0; i < environnement.getListActeurs().size(); i++) {

                   Rectangle rectangle = (Rectangle) persoPane.lookup("#" + environnement.getListActeurs().get(i).getId());

                   if (temps % 50 == 0) {

                       //Si un acteur est dans un rayon de 'rayonInteraction' autours du joueur alors
                       if ((environnement.getJoueur().getY() + rectangle.getWidth() + rayonInteraction) >= environnement.getListActeurs().get(i).getY()
                               && ((environnement.getJoueur().getY() - rectangle.getWidth() - rayonInteraction) <= environnement.getListActeurs().get(i).getY())
                               && (environnement.getJoueur().getX() + rectangle.getWidth() + rayonInteraction) >= environnement.getListActeurs().get(i).getX()
                               && ((environnement.getJoueur().getX() - rectangle.getWidth() - rayonInteraction) <= environnement.getListActeurs().get(i).getX())) {

                           if(environnement.getListActeurs().get(i) != environnement.getJoueur()){

                               //Enlève 10 pv a l'acteur
                               environnement.getListActeurs().get(i).perdPV(10);

                               //Vérifie si l'acteur doit mourir, si Oui il le supprime de l'environnement
                               environnement.getListActeurs().get(i).meurtOuVie();
                           }
                       }
                   }
               }
                //A modifier
                for (int i = 0; i < environnement.getListArmes().size(); i++) {

                    ImageView imageView = (ImageView) armePaneMap.lookup("#" + environnement.getListArmes().get(i).getId());

                    //Si le joueur est entré dans un rayon de 'rayonInteraction' autour d'une arme alors
                    if ((environnement.getJoueur().getY() + imageView.getFitWidth() + rayonInteraction) >= environnement.getListArmes().get(i).getY()
                       && ((environnement.getJoueur().getY() - imageView.getFitWidth() - rayonInteraction) <= environnement.getListArmes().get(i).getY())
                       && (environnement.getJoueur().getX() + imageView.getFitWidth() + rayonInteraction) >= environnement.getListArmes().get(i).getX()
                       && ((environnement.getJoueur().getX() - imageView.getFitWidth() - rayonInteraction) <= environnement.getListArmes().get(i).getX())) {

                        //Ajoute l'arme a l'inventaire
                        environnement.getJoueur().getInventaire().getArmes().add(environnement.getListArmes().get(i));

                        //Recupère la position de l'arme ajouté dans l'inventaire
                        int dernierElement = environnement.getJoueur().getInventaire().getArmes().size() - 1;

                        //Supprime l'arme de la liste d'armes contenue dans l'environnement
                        environnement.getListArmes().remove(i);

                        //Affiche cette arme dans l'inventaire
                        this.vueInventaire.addViewArmeInventaire(environnement.getJoueur().getInventaire().getArmes().get(dernierElement));
                    }
                }

                //Code pour l'explosion du LeZamikaze
                for (int i = 0; i < environnement.getListActeurs().size(); i++) {
                    if (environnement.getListActeurs().get(i) instanceof Zamikaze){

                        Rectangle joueur = (Rectangle) persoPane.lookup("#" + environnement.getListActeurs().get(i).getId());

                        //verifie si un acteur est dans un rayon de 'zoneDegat' autours du joueur
                        if ((environnement.getJoueur().getY() + joueur.getWidth() + rayonInteraction) >= environnement.getListActeurs().get(i).getY() && ((environnement.getJoueur().getY() - joueur.getWidth() - rayonInteraction) <= environnement.getListActeurs().get(i).getY()) && (environnement.getJoueur().getX() + joueur.getWidth() + rayonInteraction) >= environnement.getListActeurs().get(i).getX() && ((environnement.getJoueur().getX() - joueur.getWidth() - rayonInteraction) <= environnement.getListActeurs().get(i).getX())) {
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
                    if (environnement.getListActeurs().get(i) instanceof BaveZmort){
                        ((BaveZmort) environnement.getListActeurs().get(i)).attaque(temps);
                        ((BaveZmort) environnement.getListActeurs().get(i)).joueurDansBave();
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


    public void mouseClicked(MouseEvent mouseEvent) {
        persoPane.requestFocus();
    }

    public void setImageAtIndex(int index, String imagePath) {
        // Obtenir le nœud à l'index spécifique
        Node node = terrainPane.getChildren().get(index);

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
        persoPane.getChildren().add(imageView);

    }
}