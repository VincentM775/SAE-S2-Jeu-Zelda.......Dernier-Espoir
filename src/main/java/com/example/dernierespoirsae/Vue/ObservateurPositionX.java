package com.example.dernierespoirsae.Vue;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;

public class ObservateurPositionX implements ChangeListener<Number> {

    private Pane principalPane;

    public ObservateurPositionX(Pane principalPane){
        this.principalPane = principalPane;
    }

    @Override
    public void changed(ObservableValue<? extends Number> obs, Number oldV, Number newV) {
        int deplacement = newV.intValue() - oldV.intValue();

        this.principalPane.setTranslateX(this.principalPane.getTranslateX() - deplacement);
    }
}
