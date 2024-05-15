package com.example.dernierespoirsae.controleur;
import com.example.dernierespoirsae.modele.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import java.net.URL;



public class Controleur implements Initializable {
    @FXML
    private TilePane mapPane;
    @FXML
    private Pane persoPane;
    @FXML
    private Pane premierPane;
    private KeyHandler keyHandler;

    private Environnement environnement;

    public void initialize(URL location, ResourceBundle ressource) {
        Environnement environnement = new Environnement(375);
        Acteur joueur = new Acteur("Johnny", environnement);
        environnement.setJoueur(joueur);
        this.keyHandler = new KeyHandler(environnement);
        creerSprite(environnement.getJoueur());
        afficherMap(environnement.getMap());

        persoPane.addEventHandler(KeyEvent.KEY_PRESSED,this.keyHandler);
        persoPane.requestFocus();
    }


    public void afficherMap(Map map) {
        for (int x = 0; x < map.getListTuiles().size(); x++) {
            ImageView imageView = new ImageView();
            switch (map.getListTuiles().get(x)) {
                case 0:
                    Image image = new Image("file:src/main/resources/com/example/dernierespoirsae/images/Grass_02_v2.png");
                    imageView.setImage(image);
                    imageView.setFitWidth(39);
                    imageView.setFitHeight(39);
                    break;
            }
            mapPane.getChildren().add(imageView);
        }
    }
    public void creerSprite(Acteur acteur){
        Circle cercle = new Circle(20 );
        cercle.setFill(Color.RED);
        cercle.translateXProperty().bind(acteur.xProperty());
        cercle.translateYProperty().bind(acteur.yProperty());
        persoPane.getChildren().add(cercle);
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        persoPane.requestFocus();
    }
}