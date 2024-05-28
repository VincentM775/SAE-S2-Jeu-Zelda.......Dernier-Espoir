package com.example.dernierespoirsae.Vue;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;

public class ObservateurPositionY implements ChangeListener<Number> {

    private Pane principalPane;

    public ObservateurPositionY(Pane pane){
        this.principalPane = pane;
    }

    @Override
    public void changed(ObservableValue<? extends Number> obs, Number oldV, Number newV) {
        int deplacement = newV.intValue() - oldV.intValue();

        this.principalPane.setTranslateY(this.principalPane.getTranslateY() - deplacement);
    }
}
