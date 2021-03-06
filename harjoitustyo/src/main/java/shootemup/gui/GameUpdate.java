/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.gui;

import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import shootemup.domain.GameService;
import shootemup.domain.KeyController;

/**
 *
 * @author jussiste
 */
public class GameUpdate {
    private Random rng;
    private AnimationTimer timer;
    private ScreenLoader loader;
    private KeyController controller;
    private GameService service;

    public ScreenLoader getLoader() {
        return loader;
    }


    public GameUpdate(ScreenLoader loader) {
        this.rng = new Random();
        this.controller = new KeyController();
        this.loader = loader;
    }

    /**
     * Starts the game by initializing key code listener. Activates the AnimationTimer.
     * @param name name of player passed down from ScreenLoader
     */
    public void startGame(String name) {
        this.service = new GameService(name);
        loader.getStage().getScene().setOnKeyPressed(e -> {
            controller.processInput(service.getPlayer(), e.getCode(), this.loader.getRoot());
        });
        this.service.startingConfig();
        this.loader.getRoot().getChildren().add(service.getPlayer().getAvatar());
        this.loader.getRoot().getChildren().addAll(service.getInsert());
        this.timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();
    }

    public KeyController getController() {
        return controller;
    }

    /**
     * Method that checks the intersections of various objects. If player intersects with an enemy this method ends the game.
     */
    public void checkState() {
        service.projectileIntersect();
        service.powerIntersect();
        if (service.enemyIntersect()) {
            timer.stop();
            loader.getStage().setScene(new Scene(loader.startingScreen(loader.getStage())));
            loader.getDbManager().insertIntoTable(service.getPlayer().getName(), service.getPlayer().getScore());
            service.getPlayer().setScore(0);
        }
    }

    /**
     * Method updates the contents of the screen every timer tick.
     */
    public void onUpdate() {
        int r = rng.nextInt(2000);
        service.spawnEnemies(r);
        service.spawnPowerUps(r);
        service.moveProjectiles();
        loader.getAmmo().setText("Ammo: " + service.getPlayer().getMaker().getAmmo());
        loader.getScores().setText("Score:  " + Integer.toString(service.getPlayer().getScore()));
        loader.getRoot().getChildren().addAll(service.getInsert());
        loader.getRoot().getChildren().removeAll(service.getRemoval());
        loader.getRoot().getChildren().removeAll(service.getProjectileRemoval());
        checkState();
    }
}
