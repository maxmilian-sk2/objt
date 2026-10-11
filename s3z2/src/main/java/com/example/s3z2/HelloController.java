package com.example.s3z2;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML private Pane plocha;
    private Robot robot;

    @FXML private void initialize() {
        robot = new Robot(40, 40);
        robot.setLayoutX(50);
        robot.setLayoutY(50);
        plocha.getChildren().add(robot);
    }
}
