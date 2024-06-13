package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteurs.Joueur;
import com.example.dernierespoirsae.modele.Inventaire;
import com.example.dernierespoirsae.modele.Objets.AutreObjets.AutreObjets;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class VueInventaireObjets {

    private VBox inventaireVBox;
    private Inventaire inventaire;
    private Joueur joueur;
    private IntegerProperty quantite;


    public VueInventaireObjets(VBox inventaireVBox, AutreObjets objets, Inventaire inventaire, Joueur joueur) {
        this.inventaireVBox = inventaireVBox;
        this.inventaire = inventaire;
        this.joueur = joueur;
        System.out.println();
        this.quantite.bind(joueur.quantiteMunitionsProperty());

        addViewAutreObjetsInventaire(objets);
        //this.quantite =  new SimpleIntegerProperty();
    }

    /**
     * @param objets -> L'arme associée a l'image à la Pane mère
     *             <p>
     * Cette methode créer un Pane qui contient:    - L'affichage de l'arme
     *                                              - Un label indiquant sa quantitée
     * uniquement s'il n'en existe pas déjà, dans ce cas, on réaffiche pas l'arme mais on met à jour la quantité
     */
    public void addViewAutreObjetsInventaire(AutreObjets objets) {
        Pane emplacement = new Pane();
        emplacement.setOnMouseClicked(event -> setClick(emplacement));
        Label labelExiste;
        String idLabel = "labelNb" + objets.getType();

        /*
            S'il n'y a qu'une arme dans l'inventaire ET que labelExiste vaut null (Signifiant qu'un label n'a pas déjà été ajouté
            Nécessaire pour ne pas créer de nouveau l'affichage de l'arme lorsque l'on décrémente, passant de 2 a 1. Ici le label nous
            sert de repère permettant de savoir, s'il existe que l'arme est déjà affichée et donc de ne pas la réafficher)
            alors on créer et affiche l'image de l'arme.
         */
        System.out.println(objets.getQuantite());
        if (objets.getQuantite() ==1) {

            //Charger l'image de l'objet
            Image imageObjet = new Image("file:src/main/resources/com/example/dernierespoirsae/images/" + objets.getType() + ".png");

            //Stock l'image de l'objet dans une imageView pour pouvoir les afficher
            ImageView imageView = new ImageView(imageObjet);

            //Gère le style de l'emplacement de chaque objet
            emplacement.setStyle("-fx-border-width: 1; -fx-border-color: black; -fx-background-color: #77B5FE; -fx-border-radius: 10; -fx-background-radius: 10" );

            //Ajoute ce Pane a la vue Inventaire
            this.inventaireVBox.getChildren().add(emplacement);

            //Défini la taille de l'image dans le Pane
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);

            //Attribue a ce Pane le type de l'arme qu'elle contient a l'ID
            emplacement.setId(objets.getType());

            //Ajoute l'image et le label a la Pane
            emplacement.getChildren().add(imageView);
            Label label = new Label();
            label.setId(idLabel);
            label.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            label.setTranslateY(58);
            label.setTranslateX(65);
            label.setText(("x"+(objets.getQuantiteObjets())+getQuantite()));
            emplacement.getChildren().add(label);
        }
        else { //Sinon, l'arme est affichée et on modifie le label associé

            for (int i = 0; i < inventaireVBox.getChildren().size(); i++) {

                //Si un Pane correspond à l'objet alors on met à jour le label dans ce Pane
                if (String.valueOf(inventaireVBox.getChildren().get(i).getId()).equals( objets.getType()) ) {

                    Pane pane = (Pane) inventaireVBox.getChildren().get(i);

                    // Cherche le label dans le Pane
                    for (Node node : pane.getChildren()) { //Utilisation d'un objet de type Node car on se sait pas a ce moment de quel type sont les Children du Pane

                        //Si le Pane a un Label
                        if (idLabel.equals(node.getId())) {
                            labelExiste = (Label) node;
                            String textNombre = labelExiste.getText().substring(1);
                            labelExiste.setText(("x"+(Integer.parseInt(textNombre))+ getQuantite()));
                        }
                    }
                }
            }
        }
    }

    public int getQuantite() {
        return quantite.getValue();
    }

    public IntegerProperty quantiteProperty() {
        return quantite;
    }

    public void setClick(Pane emplacement){
        if(this.inventaire.getEnvironnement().getJoueur().getClickSouris().equals("d"))
            this.inventaire.getEnvironnement().getJoueur().setArmeEquipee(emplacement.getId());
    }
}