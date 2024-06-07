package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Armes.Arme;
import com.example.dernierespoirsae.modele.Inventaire;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class VueInventaire {

private VBox inventairePane;
Inventaire inventaire;

public VueInventaire(VBox inventairePane, Arme arme, Inventaire inventaire) {

    this.inventairePane = inventairePane;
    this.inventaire = inventaire;

    addViewArmeInventaire(arme);
}

/**
 * @param arme -> L'arme associée a l'image à la Pane mère
 *             <p>
 * Cette methode créer un Pane qui contient:    - L'affichage de l'arme
 *                                              - Un label indiquant sa quantitée
 * uniquement s'il n'en existe pas déjà, dans ce cas, on réaffiche pas l'arme mais on met à jour la quantité
 */
public void addViewArmeInventaire(Arme arme) {

    Pane emplacement = new Pane();
    Label labelExiste = null;
    String idLabel = "labelNb" + arme.getType();
    Pane pane = null;

    /*
    Cette boucle permet de vérifier en parcourant tout les Panes de la VBox si un label est présent dedans. Si c'est le cas
    on inject ce label dans labelExiste, sinon il reste a null.
     */

    //Parcours les cases de type Pane de l'inventaire
    for (int i = 0; i < inventairePane.getChildren().size(); i++) {

         //Si un Pane correspond à l'arme alors on met à jour le label dans ce Pane
       if (String.valueOf(inventairePane.getChildren().get(i).getId()).equals( arme.getType()) ) {

            pane = (Pane) inventairePane.getChildren().get(i);

            // Cherche le label dans le Pane
            for (Node node : pane.getChildren()) { //Utilisation d'un objet de type Node car on se sait pas a ce moment de quel type sont les Children du Pane

                //Si le Pane a un Label
                if (idLabel.equals(node.getId())) {
                    labelExiste = (Label) node;
                }
            }
        }
    }

    /*
        S'il n'y a qu'une arme dans l'inventaire ET que labelExiste vaut null (Signifiant qu'un label n'a pas déjà été ajouté
        Nécessaire pour ne pas créer de nouveau l'affichage de l'arme lorsque l'on décrémente, passant de 2 a 1. Ici le label nous
        sert de repère permettant de savoir, s'il existe que l'arme est déjà affichée et donc de ne pas la réafficher)
        alors on créer et affiche l'image de l'arme.
     */
    if (arme.getQuantite() == 1 && labelExiste == null) {

        //Charger l'image de l'arme
        Image imageArme = new Image("file:src/main/resources/com/example/dernierespoirsae/images/" + arme.getType() + ".png");

        //Stock l'image de la Hache dans une imageView pour pouvoir les afficher
        ImageView imageView = new ImageView(imageArme);

        //Ajoute ce Pane a la vue Inventaire
        this.inventairePane.getChildren().add(emplacement);

        //Défini la taille de l'image dans le Pane
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        //Attribue a ce Pane le type de l'arme qu'elle contient a l'ID
        emplacement.setId(arme.getType());

        //Ajoute l'image et le label a la Pane
        emplacement.getChildren().add(imageView);

    }
    else { //Sinon, l'arme est affichée et on modifie le label associé
        modifierLabel(arme, idLabel, labelExiste, pane);
    }
}

//Cette methode modifie de Label affiché pour indiqué la quantité de l'arme dans l'inventaire
public void modifierLabel(Arme arme, String idLabel, Label labelExiste, Pane pane) {

        //Si le label n'est pas null, c'est qu'il existait dans l'affichage, dans ce cas, on le met à jour
        if (labelExiste != null) {

            labelExiste.setText(String.valueOf(arme.getQuantite()));
        }
        else { // Si le label n'existe pas, en créer un nouveau

            Label label = new Label();
            label.setId(idLabel);
            label.setText(String.valueOf(arme.getQuantite()));
            pane.getChildren().add(label);
        }
    }
}