package com.example.javafxazaharainterfacesintentounodepomp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ManagerPLay {
    public void inicioDeJuego(AnchorPane pane, Circle circle, Rectangle rectangleHeatD, Rectangle rectangleHeatI, Rectangle rectangleLateralABB, Rectangle rectangleLateralARR, Text textoAbajo, AtomicInteger marcadorDerecha, AtomicInteger marcadorIzquierda) {


            AtomicInteger movimientoHorizontal = new AtomicInteger(1);
            AtomicInteger movimientoVertical = new AtomicInteger(0);

            circle.translateXProperty().unbind();
            circle.translateYProperty().unbind();


            Timeline animacion = new Timeline(
                    new KeyFrame(Duration.seconds(0.010), t -> {

                        moverPelota( circle, movimientoHorizontal, movimientoVertical);

                        comprobarChoque( circle,  rectangleHeatD, rectangleHeatI,  rectangleLateralABB,  rectangleLateralARR,movimientoHorizontal, movimientoVertical);

                        marcadorDerecha.set(comporbarMarcoDerecha(pane, circle, marcadorDerecha.get(), marcadorIzquierda.get(), textoAbajo));

                        marcadorIzquierda.set(comporbarMarcoIzquierda(pane, circle, marcadorDerecha.get(), marcadorIzquierda.get(), textoAbajo));

                        comprobarFin(marcadorDerecha, marcadorIzquierda);

                    })
            );

            animacion.setCycleCount(Timeline.INDEFINITE);
            animacion.play();


        }

        private void comprobarFin(AtomicInteger marcadorDerecha, AtomicInteger marcadorIzquierda) {
            if (marcadorDerecha.get()==3) {
                System.out.println("gano el equipo derecho con 3 puntos");
                System.exit(0);
            }
            if (marcadorIzquierda.get()==3) {
                System.out.println("gano el equipo izquierdo con 3 puntos");
                System.exit(0);
            }

        }

        private int comporbarMarcoIzquierda(Pane pane, Circle circle, int marcadorDerecha, int marcadorIzquierda, Text textoAbajo) {
            var derecha = pane.getWidth();
            if (circle.getTranslateX() > derecha) {
                System.out.println("se salio por la derecha");
                marcadorIzquierda ++;
                reiniciarPelota( circle, pane);
                textoAbajo.setText("Equipo Rojo : "+marcadorIzquierda+" puntos  " +
                        "                      Equipo Amarillo : "+marcadorDerecha+" puntos");
            }
            return marcadorIzquierda;

        }

        private int comporbarMarcoDerecha(Pane pane, Circle circle, int marcadorDerecha, int marcadorIzquierda, Text textoAbajo) {
            if(circle.getTranslateX()< 0){
                System.out.println("se salio por la izquierda");
                reiniciarPelota(circle, pane);
                marcadorDerecha++;
                textoAbajo.setText("Equipo Rojo : "+marcadorIzquierda+" puntos  " +
                        "                      Equipo Amarillo : "+marcadorDerecha+" puntos");
            }
            return marcadorDerecha;

        }


        private void reiniciarPelota(Circle circle, Pane pane) {

            //poner en el medio
            circle.setTranslateX(pane.getWidth()/2);
            circle.setTranslateY(pane.getHeight()/2.8);


        }

        private void comprobarChoque(Circle circle, Rectangle rectangleHeatD, Rectangle rectangleHeatI, Rectangle rectangleLateralABB, Rectangle  rectangleLateralARR , AtomicInteger movimientoHorizontal, AtomicInteger movimientoVertical) {

            //comporbar que en las pareces de arriba y abajo siga en horizontal
            var r = new Random();

            //si choca con raqueta cambia el x trasnlate
            if(circle.getBoundsInParent().intersects(rectangleHeatD.getBoundsInParent())){

                var movimientoAleatorioCantidad = r.nextInt(0,4);
                var movimientoAleatorioorientacion = r.nextInt(0,2);
                System.out.println("moimiento " +movimientoAleatorioCantidad
                        +"y" +movimientoAleatorioorientacion);

                if (movimientoAleatorioorientacion==1){
                    movimientoVertical.set(movimientoAleatorioCantidad*-1);
                }else {
                    movimientoVertical.set(movimientoAleatorioCantidad);
                }
                movimientoHorizontal.set(-1);
                System.out.println("choca con raqueta derecha ");
            }

            if(circle.getBoundsInParent().intersects(rectangleHeatI.getBoundsInParent())){

                var movimientoAleatorioCantidad = r.nextInt(0,4);
                var movimientoAleatorioorientacion = r.nextInt(0,2);

                System.out.println("moimiento " +movimientoAleatorioCantidad
                        +"y" +movimientoAleatorioorientacion);

                if (movimientoAleatorioorientacion==1){
                    movimientoVertical.set(movimientoAleatorioCantidad*-1);
                }else {
                    movimientoVertical.set(movimientoAleatorioCantidad);
                }

                movimientoHorizontal.set(+1);

                System.out.println("choca con raqueta izquierda ");
            }

            //si choca con techo cambia el y translate
            if(circle.getBoundsInParent().intersects(rectangleLateralABB.getBoundsInParent())){
                var movimientoAleatorioCantidad = r.nextInt(1,4);

                System.out.println("moimiento " +movimientoAleatorioCantidad
                );

                if (movimientoHorizontal.get()<=0){
                    movimientoHorizontal.set(movimientoAleatorioCantidad*-1);
                }else {
                    movimientoHorizontal.set(movimientoAleatorioCantidad);
                }

                movimientoVertical.set(-1);
                System.out.println("choca con pared abajo ");
            }
            if(circle.getBoundsInParent().intersects(rectangleLateralARR.getBoundsInParent())){
                var movimientoAleatorioCantidad = r.nextInt(1,4);

                System.out.println("moimiento " +movimientoAleatorioCantidad
                );
                if (movimientoHorizontal.get()<=0){
                    movimientoHorizontal.set(movimientoAleatorioCantidad*-1);
                }else {
                    movimientoHorizontal.set(movimientoAleatorioCantidad);
                }
                movimientoVertical.set(1);
                System.out.println("choca con pared abrriba ");
            }
        }

        private void moverPelota(Circle circle, AtomicInteger movimientoHorizontal , AtomicInteger movimientoVertical) {
            circle.setTranslateX(circle.getTranslateX()+ movimientoHorizontal.get());
            circle.setTranslateY(circle.getTranslateY()+ movimientoVertical.get());
        }




}
