package com.example.javafxazaharainterfacesintentounodepomp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //clases necesarias
        CreaterContent creater = new CreaterContent();
        //ponemos cosas a la stage
        stage.setTitle("Pong de Azahara");
        stage.setWidth(800);
        stage.setHeight(600);
        Scene escene = new Scene(creater.createContent());
        stage.setScene(escene);
        stage.show();

    }

}

