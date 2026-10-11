package com.example.s3z2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Robot extends Canvas {
    private int w, h;
    private int smer = 1;
    private final double krok = 10;
    private final double rychlost = 3;
    private boolean automaticky = false;
    private Timeline t;

    public Robot(int w, int h) {
        super(w, h);
        this.w = w; this.h = h;

        t = new Timeline(new KeyFrame(Duration.millis(20), e -> automatickyPohyb()));
        t.setCycleCount(Timeline.INDEFINITE);

        setOnKeyPressed(evt -> spracuj(evt));
        setFocusTraversable(true);

        setOnMouseClicked(evt -> prepniRezim());

        Vykresli(getGraphicsContext2D());
    }

    private void spracuj(KeyEvent evt) {
        if (automaticky) return;

        KeyCode k = evt.getCode();
        if (k == KeyCode.W)      smer = 0;
        else if (k == KeyCode.D) smer = 1;
        else if (k == KeyCode.S) smer = 2;
        else if (k == KeyCode.A) smer = 3;
        else return;

        posun(krok);
        Vykresli(getGraphicsContext2D());
        evt.consume();
    }

    private boolean posun(double d) {
        double x = getLayoutX();
        double y = getLayoutY();

        switch (smer) {
            case 0: y -= d; break;
            case 1: x += d; break;
            case 2: y += d; break;
            case 3: x -= d; break;
        }

        double maxX = sirkaPlochy() - w;
        double maxY = vyskaPlochy() - h;
        boolean naraz = x < 0 || y < 0 || x > maxX || y > maxY;

        x = Math.max(0, Math.min(x, maxX));
        y = Math.max(0, Math.min(y, maxY));

        setLayoutX(x);
        setLayoutY(y);
        return naraz;
    }

    private void automatickyPohyb() {
        if (posun(rychlost)) {
            smer = (smer + 2) % 4;
            Vykresli(getGraphicsContext2D());
        }
    }

    public void prepniRezim() {
        requestFocus();
        automaticky = !automaticky;
        if (automaticky) t.play();
        else t.stop();
        Vykresli(getGraphicsContext2D());
    }

    public void zapniAutomat() {
        if (!automaticky) prepniRezim();
    }

    private double sirkaPlochy() {
        if (getParent() instanceof Region) return ((Region) getParent()).getWidth();
        return getScene().getWidth();
    }

    private double vyskaPlochy() {
        if (getParent() instanceof Region) return ((Region) getParent()).getHeight();
        return getScene().getHeight();
    }

    private void Vykresli(GraphicsContext gc) {
        gc.clearRect(0, 0, w, h);

        gc.setFill(automaticky ? Color.ORANGE : Color.STEELBLUE);
        gc.fillRect(0, 0, w, h);

        double p = 8;
        gc.setFill(Color.RED);
        switch (smer) {
            case 0: gc.fillRect(0, 0, w, p); break;
            case 1: gc.fillRect(w - p, 0, p, h); break;
            case 2: gc.fillRect(0, h - p, w, p); break;
            case 3: gc.fillRect(0, 0, p, h); break;
        }

        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, w, h);
    }
}
