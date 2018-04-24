/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.domain;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import shootemup.gui.KeyController;
import shootemup.gui.ScreenLoader;

/**
 *
 * @author jussiste
 */
public class GameUpdate {

    private Random rng;
    private ProjectileMaker maker;
    private ArrayList<Enemy> enemies;
    private int score;
    private long counter;
    private Player player;
    private AnimationTimer timer;
    private ScreenLoader loader;
    private KeyController controller;

    public ScreenLoader getLoader() {
        return loader;
    }

    public Player getPlayer() {
        return player;
    }

    public ProjectileMaker getMaker() {
        return maker;
    }

    public GameUpdate(ScreenLoader loader) {
        this.maker = new ProjectileMaker();
        this.enemies = new ArrayList<Enemy>();
        this.rng = new Random();
        this.controller = new KeyController();
        this.score = 0;
        this.loader = loader;

    }

    public void startGame(String name) {
        loader.getStage().getScene().setOnKeyPressed(e -> {
            controller.processInput(this, e.getCode());
        });
        this.player = new Player(name);
        this.loader.getRoot().getChildren().add(player.getAvatar());
        Idler idler=new Idler(player.getAvatar());
        enemies.add(idler);
        this.loader.getRoot().getChildren().add(idler.getEnemy());
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

    public void checkState() {
        ArrayList<Node> projectileIndex = new ArrayList<>();
        ArrayList<Enemy> enemyIndex = new ArrayList<>();
        for (Enemy e : enemies) {
            for (Node p : maker.getProjectiles()) {
                if (e.getEnemy().getBoundsInParent().intersects(p.getBoundsInParent())) {
                    enemyIndex.add(e);
                    projectileIndex.add(p);
                    loader.getRoot().getChildren().remove(p);
                    loader.getRoot().getChildren().remove(e.getEnemy());
                    this.score++;
                    loader.getScores().setText("Score: " + Integer.toString(this.score));
                }
            }
            if (e.getEnemy().getBoundsInParent().intersects(player.getAvatar().getBoundsInParent())) {
                System.out.println("You died");
                timer.stop();
                loader.getStage().setScene(new Scene(loader.startingScreen(loader.getStage())));
                loader.getDbManager().insertIntoTable(player.getName(), score);
                score = 0;
            }
        }
        for (Node p : projectileIndex) {
            maker.removeProjectile(p);
        }
        for (Enemy enemy : enemyIndex) {
            enemies.remove(enemy);
        }
    }

    public void onUpdate() {
        int r = rng.nextInt(1000);
        counter++;
        ArrayList<Node> projectiles = maker.getProjectiles();
        ArrayList<Speed> speeds = maker.getSpeeds();
        if (r == 0 && enemies.size() <= 15) {
            Idler idler = new Idler(player.getAvatar());
            enemies.add(idler);
            loader.getRoot().getChildren().add(idler.getEnemy());
        }
        if (r == 1 && enemies.size() < 15) {
            Drone drone = new Drone(player.getAvatar());
            enemies.add(drone);
            loader.getRoot().getChildren().add(drone.getEnemy());
        }
        for (int i = 0; i < projectiles.size(); i++) {
            Node projectile = projectiles.get(i);
            Speed speed = speeds.get(i);
            projectile.setTranslateX(projectile.getTranslateX() + speed.getxSpeed());
            projectile.setTranslateY(projectile.getTranslateY() + speed.getySpeed());
        }
        if (counter % 100 == 0) {
            for (Enemy e : enemies) {
                e.move();
            }
        }
        checkState();
    }
}
