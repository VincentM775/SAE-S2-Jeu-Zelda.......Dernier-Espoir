package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Armes.Balle;
import com.example.dernierespoirsae.modele.Armes.Projectile;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ObservateurProjectile implements ListChangeListener<Projectile> {
    private Pane ballePane;

    private Environnement environnement;

    public ObservateurProjectile(Pane ballePane, Environnement environnement) {
        this.ballePane = ballePane;
        this.environnement = environnement;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Projectile> projectile) {

        while (projectile.next()){
            for(int i = 0; i < projectile.getAddedSize(); i++){
                creerSprite(projectile.getAddedSubList().get(i));
            }
            for(int i = 0; i < projectile.getRemovedSize(); i++){
                suprimerSprite(projectile.getRemoved().get(i));
            }
        }
    }
    public void creerSprite(Projectile balle){

        if(balle instanceof Balle){

            // Définir la nouvelle image
            Image image = new Image("file:src/main/resources/com/example/dernierespoirsae/images/bave_projectile.png");
            ImageView imageView = new ImageView(image);
            imageView.translateXProperty().bind(balle.xProperty());
            imageView.translateYProperty().bind(balle.yProperty());
            imageView.setFitWidth(30);
            imageView.setFitHeight(30);
            ballePane.getChildren().add(imageView);
            imageView.setId(""+balle.getId());
        }
    }

    public void suprimerSprite(Projectile projectile){
        this.ballePane.getChildren().remove(this.ballePane.lookup("#"+projectile.getId()));
    }

}
