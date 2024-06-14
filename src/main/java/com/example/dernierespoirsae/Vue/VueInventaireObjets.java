package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Inventaire;
import com.example.dernierespoirsae.modele.Objets.Objets;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class VueInventaireObjets {

    private VBox inventaireVBox;
    private Inventaire inventaire;

    public VueInventaireObjets(VBox inventaireVBox, Objets objets, Inventaire inventaire) {
        this.inventaireVBox = inventaireVBox;
        this.inventaire = inventaire;
        addViewAutreObjetsInventaire(objets);
    }

    /**
     * @param objets -> L'arme associée a l'image à la Pane mère
     *             <p>
     * Cette methode créer un Pane qui contient:    - L'affichage de l'arme
     *                                              - Un label indiquant sa quantitée
     * uniquement s'il n'en existe pas déjà, dans ce cas, on réaffiche pas l'arme mais on met à jour la quantité
     */
    public void addViewAutreObjetsInventaire(Objets objets) {

        String idLabel = "labelNb" + objets.getType();
        Label label = new Label();

        //On creer un nouvel emplacement dans l'inventaire
        if (objets.quantiteProperty().getValue() == 1) {

            Pane emplacement = new Pane();
            emplacement.setOnMouseClicked(event -> setClick(emplacement));
            label.setId(idLabel);
            label.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            label.setTranslateY(58);
            label.setTranslateX(75);

            if(!objets.ObjetUnique())
                label.textProperty().bind(Bindings.convert(objets.quantiteObjetsProperty()));

            emplacement.getChildren().add(label);

            //Charger l'image de l'arme
            Image imageObjet = new Image("file:src/main/resources/com/example/dernierespoirsae/images/" + objets.getType() + ".png");

            //Stock l'image de l'objet dans une imageView pour pouvoir les afficher
            ImageView imageView = new ImageView(imageObjet);

            //Gère le style de l'emplacement de chaque arme
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

        }
        //Sinon on retrouve le Label et on l'incremente
        else {
            {
                Label labelExiste = null;
                Pane pane;
                for (int i = 0; i < inventaireVBox.getChildren().size(); i++) {
                    if (String.valueOf(inventaireVBox.getChildren().get(i).getId()).equals(objets.getType())) {
                        pane = (Pane) inventaireVBox.getChildren().get(i);
                        for (Node node : pane.getChildren()) {
                            if (idLabel.equals(node.getId())) {
                                labelExiste = (Label) node;
                            }
                        }
                    }
                }

                //On Récupère le contenue du String retrouveé pour l'additionner la nouvelle le nombre de munition
                String texteNombre = labelExiste.getText();
                int nouvelleQuantite = Integer.parseInt(texteNombre) + objets.quantiteObjetsProperty().get();
                labelExiste.textProperty().unbind();
                labelExiste.setText(String.valueOf(nouvelleQuantite));

                if(!objets.ObjetUnique()) {
                    label.textProperty().bind(Bindings.createStringBinding(
                            () ->String.valueOf(objets.quantiteObjetsProperty().get()),
                            objets.quantiteObjetsProperty()));
                }
            }
        }
    }

    public void setClick(Pane emplacement){
        if(this.inventaire.getEnvironnement().getJoueur().getClickSouris().equals("d"))
            this.inventaire.getEnvironnement().getJoueur().setArmeEquipee(emplacement.getId());
    }
}
