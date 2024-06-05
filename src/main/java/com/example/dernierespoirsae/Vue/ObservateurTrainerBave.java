package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Armes.Bave;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class ObservateurTrainerBave implements ListChangeListener<Bave> {
    private Environnement environnement;
    private TilePane animationPane;

    public ObservateurTrainerBave(Environnement environnement, TilePane animationPane) {
        this.environnement = environnement;
        this.animationPane = animationPane;
    }
    @Override
    public void onChanged(Change<? extends Bave> bave) {
        while (bave.next()){
            for(int i = 0; i < bave.getAddedSize(); i++){
                int tuileDansListe = bave.getAddedSubList().get(i).getLigne()*environnement.getInfoTuile()[1]+bave.getAddedSubList().get(i).getColonne();
                setImageAtIndex(tuileDansListe);
                if (environnement.getListBave().size()==5)
                    environnement.getListBave().remove(0);
            }
            for(int i = 0; i < bave.getRemovedSize(); i++){
                int tuileDansListe = bave.getRemoved().get(i).getLigne()*environnement.getInfoTuile()[1]+bave.getRemoved().get(i).getColonne();
                supprimerImageAtIndex(tuileDansListe);
            }
        }
    }

    public void setImageAtIndex(int index) {
        // Obtenir le nœud à l'index spécifique
        Node node = animationPane.getChildren().get(index);

        // Vérifier si le nœud est bien une instance d'ImageView
        if (node instanceof ImageView) {
            ImageView imageView = (ImageView) node;

            // Définir la nouvelle image
            Image image = new Image("file:src/main/resources/com/example/dernierespoirsae/images/flaque_bave.png");
            imageView.setImage(image);
        }
    }
    public void supprimerImageAtIndex(int index){
        // Obtenir le nœud à l'index spécifique
        Node node = animationPane.getChildren().get(index);

        // Vérifier si le nœud est bien une instance d'ImageView
        if (node instanceof ImageView) {
            ImageView imageView = (ImageView) node;

            // Définir la nouvelle image
            Image image = new Image("file:src/main/resources/com/example/dernierespoirsae/images/Grass_02_v2.png");
            imageView.setImage(image);
        }
    }


}
