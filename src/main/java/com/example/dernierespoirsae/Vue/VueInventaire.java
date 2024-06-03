package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Armes.Arme;
import com.example.dernierespoirsae.modele.Armes.Hache;
import com.example.dernierespoirsae.modele.Armes.Pistolet;
import com.example.dernierespoirsae.modele.Inventaire;
import com.example.dernierespoirsae.modele.Joueur;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class VueInventaire {

    private VBox inventairePane;
    private Inventaire inventaire;

    public VueInventaire(VBox inventairePane, Inventaire inventaire) {
        this.inventairePane = inventairePane;
        this.inventaire = inventaire;
    }

    public void addViewArmeIventaire(Arme arme) {

        Image hache = new Image("file:src/main/resources/com/example/dernierespoirsae/images/hache.png");
        Image pistolet = new Image("file:src/main/resources/com/example/dernierespoirsae/images/pistolet.png");

        //Si on appelle addViewIventaire() c'est qu'il y a déja 1 objet du type cherché dans l'inventaire
        int nbHache = 1;
        int nbPistolet = 1;

        //Si le type de l'arme est une hache
        if(arme.getType().equals("hache")) {

            //Créer la case de type Pane qui va contenire la Hache
            Pane emplacementHache = new Pane();

            //Stock l'image de la Hache dans une imageView pour pouvoir les afficher
            ImageView imageView = new ImageView(hache);

            //creation d'un emplacement pour l'affichage de l'arme et d'un label affichant le nombre de haches en sa possédé
            creationEmplacementInventaire(emplacementHache, imageView, arme, nbHache, "labelNbHache");

        } else if(arme.getType().equals("pistolet")){

            //Créer la case de type Pane qui va contenire la Hache
            Pane emplacementPistolet = new Pane();

            //Stock l'image du pistolet dans une imageView  pour pouvoir les afficher
            ImageView imageView = new ImageView(pistolet);

            //creation d'un emplacement pour l'affichage de du pistolet et d'un label affichant le nombre de pistolet possédé
            creationEmplacementInventaire(emplacementPistolet, imageView, arme, nbPistolet, "labelNbPistolet");

        }

    }

    /**
     *
     * @param emplacement       -> La Pane mère qui contiendra l'image et le Label
     * @param imageView         -> L'image a afficher
     * @param arme              -> L'arme associée a l'image à la Pane mère
     * @param quantitePosssede  -> La quantité de l'arme possédé
     * @param idLabel           -> Un ID qui va être attribué au label, pour pouvoir le retrouver plus tard s'il existe
     *
     * Cette methode créer un Pane qui contient:    - L'affichage de l'arme
     *                                              - Un label indiquant sa quantitée
     * uniquement s'il n'en existe pas déjà, dans ce cas, on réaffiche pas l'arme mais on met à jour la quantité
     *
     */

    public void creationEmplacementInventaire(Pane emplacement, ImageView imageView, Arme arme, int quantitePosssede, String idLabel){

        //S'il n'existe pas d'emplacement hache dans l'inventaire alors en créer une
        if (!this.inventaire.existe(arme)) {

            //Ajoute ce Pane a la vue Inventaire
            this.inventairePane.getChildren().add(emplacement);

            //Défini la taille de l'image dans le Pane
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);

            //Attribue a ce Pane le type de l'arme qu'elle contient a l'ID
            emplacement.setId(arme.getType());

            //Ajoute l'image et le label a la Pane
            emplacement.getChildren().add(imageView);

        } else { //Sinon on incrémente le label qui indique sa quantité

            //Compte le nombre de hache aquis
            for (Arme hacheInventaire : inventaire.getArmes()) {
                if (arme.getType().equals(hacheInventaire.getType())) {
                    quantitePosssede++;
                }
            }

            //Parcours les cases de type Pane de l'inventaire
            for (int i = 0; i < inventairePane.getChildren().size(); i++) {

                // Si un Pane correspond à l'arme alors on met à jour le label dans ce Pane
                if (String.valueOf(inventairePane.getChildren().get(i).getId()).equals(arme.getType())) {

                    Pane pane = (Pane) inventairePane.getChildren().get(i);

                    Label labelExiste = null;

                    // Cherche le label dans le Pane
                    for (Node node : pane.getChildren()) { //Utilisation d'un objet de type Node car on se sait pas a ce moment de quel type sont les Children du Pane

                        //Si le Pane a un Label
                        if (node instanceof Label && idLabel.equals(node.getId())) {
                            labelExiste = (Label) node;
                        }
                    }

                    //Si le label est pas null, c'est qu'il existait dans l'affichage, dans ce cas, on le met à jour
                    if (labelExiste != null) {

                        // Met à jour le texte du label existant
                        labelExiste.setText(String.valueOf(quantitePosssede));

                    } else {

                        // Si le label n'existe pas, en créer un nouveau
                        Label label = new Label();
                        label.setId(idLabel);
                        label.setText(String.valueOf(quantitePosssede));
                        pane.getChildren().add(label);
                    }
                }
            }
        }
    }
}