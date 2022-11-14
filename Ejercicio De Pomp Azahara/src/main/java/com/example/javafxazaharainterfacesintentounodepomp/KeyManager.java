package com.example.javafxazaharainterfacesintentounodepomp;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class KeyManager {

    public AnchorPane setListenerKeys(AnchorPane pane, Rectangle  rectangleHeatI, Rectangle rectangleHeatD) {
        pane.setOnKeyPressed((KeyEvent keyEvent) -> {
            //desbindamos los rectanulos para poder moverlos
            rectangleHeatI.xProperty().unbind();
            rectangleHeatD.xProperty().unbind();

            //cuando toques alguna tecla
            //q y a para izquierda y p y l para derecha
            if (keyEvent.getCode().equals(KeyCode.Q)) {
                System.out.println("derecha arriva");
                rectangleHeatI.setTranslateY(rectangleHeatI.getTranslateY() - 13);
            }
            if (keyEvent.getCode().equals(KeyCode.A)) {
                System.out.println("derecha abajo");
                rectangleHeatI.setTranslateY(rectangleHeatI.getTranslateY() + 13);
            }
            if (keyEvent.getCode().equals(KeyCode.P)) {
                System.out.println("izquierda arriva");
                rectangleHeatD.setTranslateY(rectangleHeatD.getTranslateY() - 13);
            }
            if (keyEvent.getCode().equals(KeyCode.L)) {
                System.out.println("ixquierda abajo");
                rectangleHeatD.setTranslateY(rectangleHeatD.getTranslateY() + 13);
            }

        });
        return pane;

    }
}