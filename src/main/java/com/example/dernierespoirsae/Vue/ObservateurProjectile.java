package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Armes.Balle;
import com.example.dernierespoirsae.modele.Armes.Projectile;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.beans.binding.Bindings;

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
                creerVueProjectil(projectile.getAddedSubList().get(i));
            }
            for(int i = 0; i < projectile.getRemovedSize(); i++){
                suprimerSprite(projectile.getRemoved().get(i));
            }
        }
    }
    public void creerVueProjectil(Projectile balle){

        if(balle instanceof Balle){

            // Définir la nouvelle image
            Image image = new Image("file:src/main/resources/com/example/dernierespoirsae/images/bave_projectile.png");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(10);
            imageView.setFitHeight(15);

            // Lier les propriétés de position
            imageView.translateXProperty().bind(balle.xProperty());
            imageView.translateYProperty().bind(balle.yProperty());

            // Lier la propriété de rotation
            // Lier la propriété de rotation
            imageView.rotateProperty().bind(Bindings.createDoubleBinding(() -> balle.getAngle(), balle.xProperty(), balle.yProperty()));

            ballePane.getChildren().add(imageView);
            imageView.setId(""+balle.getId());
        }
    }

    public void suprimerSprite(Projectile projectile){
        this.ballePane.getChildren().remove(this.ballePane.lookup("#"+projectile.getId()));
    }

}
