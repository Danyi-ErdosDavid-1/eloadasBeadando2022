package com.erettsegi;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ParallelController implements Initializable  {
    @FXML
    Label mozgóLabel1;
    @FXML
    Label mozgóLabel2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClock1();
        initClock2();
    }
    protected void initClock1() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            mozgóLabel1.setLayoutX(mozgóLabel1.getLayoutX() + 4);
            if(mozgóLabel1.getLayoutX() == 600) {
                mozgóLabel1.setLayoutX(0);
            }
        }), new KeyFrame(Duration.millis(50)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    protected void initClock2() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            mozgóLabel2.setLayoutX(mozgóLabel2.getLayoutX() - 10);
            if(mozgóLabel2.getLayoutX() == 0) {
                mozgóLabel2.setLayoutX(600);
            }
        }), new KeyFrame(Duration.millis(300)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}
