/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.gui;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import shootemup.domain.AmmoPowerUp;
import shootemup.domain.Drone;
import shootemup.domain.Enemy;
import shootemup.domain.Idler;
import shootemup.domain.Player;
import shootemup.domain.PowerUp;
import shootemup.domain.ProjectileMaker;
import shootemup.domain.ScorePowerUp;
import shootemup.domain.Speed;
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
    private ArrayList<PowerUp> powerUps;
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
        this.powerUps = new ArrayList<PowerUp>();
        this.rng = new Random();
        this.controller = new KeyController();
        this.loader = loader;
        this.rng = new Random();

    }

    public void startGame(String name) {
        loader.getStage().getScene().setOnKeyPressed(e -> {
            controller.processInput(this, e.getCode());
        });
        this.player = new Player(name);
        this.loader.getRoot().getChildren().add(player.getAvatar());
        double x = rng.nextInt(50) * 20;
        double y = rng.nextInt(50) * 20;
        Idler idler = new Idler(player.getAvatar(), x, y);
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
        ArrayList<PowerUp> powIndex = new ArrayList<>();
        for (Enemy e : enemies) {
            for (Node p : maker.getProjectiles()) {
                if (e.getEnemy().getBoundsInParent().intersects(p.getBoundsInParent())) {
                    enemyIndex.add(e);
                    projectileIndex.add(p);
                    loader.getRoot().getChildren().remove(p);
                    loader.getRoot().getChildren().remove(e.getEnemy());
                    this.player.setScore(this.player.getScore() + 1);
                    loader.getScores().setText("Score: " + Integer.toString(this.player.getScore()));
                }
            }
            if (e.getEnemy().getBoundsInParent().intersects(player.getAvatar().getBoundsInParent())) {
                timer.stop();
                loader.getStage().setScene(new Scene(loader.startingScreen(loader.getStage())));
                loader.getDbManager().insertIntoTable(player.getName(), player.getScore());
            }
        }
        for (PowerUp pow : powerUps) {
            if (pow.getPowerUp().getBoundsInParent().intersects(player.getAvatar().getBoundsInParent())) {
                pow.powerUp(player, maker);
                loader.getScores().setText("Score:  " + Integer.toString(this.player.getScore()));
                loader.getAmmo().setText("Ammo: " + maker.getAmmo());
                powIndex.add(pow);
                loader.getRoot().getChildren().remove(pow.getPowerUp());
            }
        }
        for (PowerUp pow : powIndex) {
            powerUps.remove(pow);
        }
        for (Node p : projectileIndex) {
            maker.removeProjectile(p);
        }
        for (Enemy enemy : enemyIndex) {
            enemies.remove(enemy);
        }
    }

    public void onUpdate() {
        int r = rng.nextInt(2000);
        counter++;
        ArrayList<Node> projectiles = maker.getProjectiles();
        ArrayList<Speed> speeds = maker.getSpeeds();
        if (r < 3 && enemies.size() <= 15) {
            double x = rng.nextInt(50) * 20;
            double y = rng.nextInt(50) * 20;
            Idler idler = new Idler(player.getAvatar(), x, y);
            enemies.add(idler);
            loader.getRoot().getChildren().add(idler.getEnemy());
        }
        if (r > 3 && r < 5 && enemies.size() < 15) {
            double x = rng.nextInt(50) * 20;
            double y = rng.nextInt(50) * 20;
            Drone drone = new Drone(player.getAvatar(), x, y);
            enemies.add(drone);
            loader.getRoot().getChildren().add(drone.getEnemy());
        }
        if (r == 6) {
            ScorePowerUp bonus = new ScorePowerUp();
            powerUps.add(bonus);
            loader.getRoot().getChildren().add(bonus.getPowerUp());
        }
        if (r == 7 || r == 8) {
            AmmoPowerUp ammo = new AmmoPowerUp();
            powerUps.add(ammo);
            loader.getRoot().getChildren().add(ammo.getPowerUp());
        }
        for (int i = 0; i < projectiles.size(); i++) {
            Node projectile = projectiles.get(i);
            Speed speed = speeds.get(i);
            projectile.setTranslateX(projectile.getTranslateX() + speed.getxSpeed());
            projectile.setTranslateY(projectile.getTranslateY() + speed.getySpeed());
        }
        if (counter % 31 == 0) {
            for (Enemy e : enemies) {
                int move = rng.nextInt(4);
                e.move(move);
            }
        }
        checkState();
    }
}
