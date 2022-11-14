package com.example.javafxazaharainterfacesintentounodepomp;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.MalformedURLException;
import java.util.concurrent.atomic.AtomicInteger;

public class CreaterContent {

    public AnchorPane createContent() throws MalformedURLException {

        AnchorPane pane = new AnchorPane();

        Circle circle = createCircle(pane);
        Rectangle rectangleHeatI = createRectangleHeatI(pane);
        Rectangle rectangleHeatD = createRectangleHeatD(pane);
        Rectangle rectangleLateralARR = createRectangleLateralARR(pane);
        Rectangle rectangleLateralABB = createRectangleLateralABB(pane);
        AtomicInteger marcadorIzquierda = new AtomicInteger(0);
        AtomicInteger  marcadorDerecha= new AtomicInteger(0);
        Text textoAbajo = createTextoAbajo(pane, marcadorIzquierda, marcadorDerecha);
        Text textoArriva = createTextoArriva(pane, marcadorIzquierda, marcadorDerecha);
        Button botonStart = createButonStart(pane);

        pane.getChildren().add(circle);
        pane.getChildren().add(rectangleHeatI);
        pane.getChildren().add(rectangleHeatD);
        pane.getChildren().add(rectangleLateralARR);
        pane.getChildren().add(rectangleLateralABB);
        pane.getChildren().add(textoAbajo);
        pane.getChildren().add(textoArriva);
        pane.getChildren().add(botonStart);

        pane.setFocusTraversable(true);
        pane.requestFocus();

        KeyManager keyManager = new KeyManager();
        pane = keyManager.setListenerKeys(pane, rectangleHeatI, rectangleHeatD);

        AnchorPane finalPane = pane;

        botonStart.setOnAction(event -> {
            botonStart.visibleProperty().set(false);
            //al iniciar el boton
            finalPane.requestFocus();
            ManagerPLay managerplay = new ManagerPLay();
            managerplay.inicioDeJuego(finalPane, circle,rectangleHeatD,rectangleHeatI,
                    rectangleLateralABB,rectangleLateralARR , textoAbajo,
                    marcadorDerecha, marcadorIzquierda);
        });

        return pane;
    }

    private Button createButonStart(Pane pane) {
        //boton de iniciar
        Button botonStart = new Button("Empezamos");
        botonStart.translateXProperty().bind(pane.widthProperty().divide(2.3));
        botonStart.translateYProperty().bind(pane.heightProperty().divide(2));
        //cuado el boron no se halla pulsado sea vivisoble luego no
        botonStart.visibleProperty().set(true);
        return botonStart;

    }

    private Text createTextoArriva(AnchorPane pane,
                                   AtomicInteger marcadorIzquierda,
                                   AtomicInteger marcadorDerecha) {
        //texto de arriva
        Text textoArriva = new Text("La bola loca, cada vez que rebote puede tomar una direción, Cuidado!!");
        //todo me gustaria cambiar el tamaño de la letra
        textoArriva.xProperty().bind(pane.widthProperty().divide(5)); //centrado ancho
        textoArriva.yProperty().bind(pane.heightProperty().divide(25));

        return textoArriva;
    }

    private Text createTextoAbajo(AnchorPane pane,
                                  AtomicInteger marcadorIzquierda,
                                  AtomicInteger marcadorDerecha) {
        //texto debajo
        Text textoAbajo = new Text("Equipo Rojo : "+marcadorIzquierda +" puntos                " +
                "        Equipo Amarillo : "+marcadorDerecha+" puntos" +
                "\n \n         El ganador será el que marque antes 3 puntos.");
        textoAbajo.xProperty().bind(pane.widthProperty().divide(4)); //centrado ancho
        textoAbajo.yProperty().bind(pane.heightProperty().divide(1.1));

        return textoAbajo;
    }

    private Rectangle createRectangleLateralABB(AnchorPane pane) {

        //rectangulos de laterales abajo
        Rectangle rectangleLateralABB = new Rectangle();
        //color
        rectangleLateralABB.setFill(Color.CORAL);
        //tamaño
        rectangleLateralABB.widthProperty().bind(pane.widthProperty());     //ancho toda la pantalla
        rectangleLateralABB.heightProperty().bind(pane.heightProperty().divide(50));   //alto depende de la pantalla

        //posicion
        rectangleLateralABB.setX(0);
        rectangleLateralABB.yProperty().bind(pane.heightProperty().divide(1.3));    //sienpre al inicio

        return rectangleLateralABB;

    }

    private Rectangle createRectangleLateralARR(AnchorPane pane) {
        //rectangulo de laterale arriva
        Rectangle rectangleLateralARR = new Rectangle();
        //color
        rectangleLateralARR.setFill(Color.CORAL);
        //tamaño
        rectangleLateralARR.widthProperty().bind(pane.widthProperty());
        rectangleLateralARR.heightProperty().bind(pane.heightProperty().divide(50));

        //posicion
        rectangleLateralARR.setX(0);    //siemrpre pegado a la pantalla
        rectangleLateralARR.yProperty().bind(pane.heightProperty().divide(13)); //separado de la pantalla
        return rectangleLateralARR;
    }

    private Rectangle createRectangleHeatD(AnchorPane pane) {
        //rectangulo golpeo derecha
        Rectangle rectangleHeatD = new Rectangle();
        rectangleHeatD.setFill(Color.YELLOW);
        //tamaño
        rectangleHeatD.widthProperty().bind(pane.widthProperty().divide(100));
        rectangleHeatD.heightProperty().bind(pane.heightProperty().divide(10));
        //poner posicion
        //posicion
        rectangleHeatD.xProperty().bind(pane.widthProperty().divide(1.05));
        rectangleHeatD.yProperty().bind(pane.heightProperty().divide(2.5));
        return rectangleHeatD;

    }

    private Rectangle createRectangleHeatI(AnchorPane pane) {
        //rectangulo golpeo Izquierda
        Rectangle rectangleHeatI = new Rectangle();
        //poner tamaño
        rectangleHeatI.heightProperty().bind(pane.heightProperty().divide(10));
        rectangleHeatI.widthProperty().bind(pane.widthProperty().divide(100));
        //poner posicion
        rectangleHeatI.setX(20);
        //poner color
        rectangleHeatI.setFill(Color.RED);
        //posicion
        rectangleHeatI.xProperty().bind(pane.widthProperty().divide(60));
        rectangleHeatI.yProperty().bind(pane.heightProperty().divide(2.5));
        return rectangleHeatI;

    }

    private Circle createCircle(AnchorPane pane) {
        Circle  circle = new Circle(20);
        //poner en el medio
        circle.translateXProperty().bind(pane.widthProperty().divide(2));
        circle.translateYProperty().bind(pane.heightProperty().divide(2.8));
        //tamaño
        circle.radiusProperty().bind(pane.heightProperty().divide(50));
        return circle;

    }

}
