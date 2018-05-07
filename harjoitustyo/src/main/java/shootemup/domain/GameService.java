/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.domain;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import shootemup.gui.GameUpdate;

/**
 *
 * @author jussiste
 */
public class GameService {

    private ArrayList<Enemy> enemies;
    private ArrayList<PowerUp> powerUps;
    private Player player;
    private Random rng;
    private ArrayList<Node> removal;
    private ArrayList<Circle> projectileRemoval;
    private ArrayList<Node> insert;
    private long counter;

    public GameService(String name) {
        this.player = new Player(name);
        this.enemies = new ArrayList<>();
        this.powerUps = new ArrayList<>();
        this.rng = new Random();
        this.removal = new ArrayList<>();
        this.insert=new ArrayList<>();
        this.projectileRemoval = new ArrayList<>();
        this.counter = 0;
    }

    public ArrayList<Node> getInsert() {
        ArrayList<Node> copy = (ArrayList<Node>) insert.clone();
        insert.clear();
        return copy;
    }

    public ArrayList<Circle> getProjectileRemoval() {
        ArrayList<Circle> copy = (ArrayList<Circle>) projectileRemoval.clone();
        projectileRemoval.clear();
        return copy;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public ArrayList<Node> getRemoval() {
        ArrayList<Node> copy = (ArrayList<Node>) removal.clone();
        removal.clear();
        return copy;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<PowerUp> getPowerUps() {
        return powerUps;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean enemyIntersect() {
        for (Enemy e : this.enemies) {
            if (e.getEnemy().getBoundsInParent().intersects(player.getAvatar().getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }

    public void powerIntersect() {
        ArrayList<PowerUp> powIndex = new ArrayList<>();
        for (PowerUp pow : powerUps) {
            if (pow.getPowerUp().getBoundsInParent().intersects(player.getAvatar().getBoundsInParent())) {
                pow.powerUp(player);
                powIndex.add(pow);
                removal.add(pow.getPowerUp());
            }
        }
        for (PowerUp pow : powIndex) {
            powerUps.remove(pow);
        }
    }

    public void projectileIntersect() {
        ArrayList<Projectile> projectileIndex = new ArrayList<>();
        ArrayList<Enemy> enemyIndex = new ArrayList<>();
        for (Enemy e : enemies) {
            for (Projectile p : this.player.getMaker().getProjectiles()) {
                if (e.getEnemy().getBoundsInParent().intersects(p.getProjectile().getBoundsInParent())) {
                    enemyIndex.add(e);
                    projectileIndex.add(p);
                    removal.add(e.getEnemy());
                    projectileRemoval.add(p.getProjectile());
                    this.player.setScore(this.player.getScore() + 1);
                }
            }
        }
        for (Enemy enemy : enemyIndex) {
            enemies.remove(enemy);
        }
        for (Projectile p : projectileIndex) {
            player.getMaker().removeProjectile(p);
        }
    }

    public void startingConfig() {
        Idler idler = new Idler(player.getAvatar(), 500, 300);
        enemies.add(idler);
        insert.add(idler.getEnemy());
    }

    public void spawnEnemies(int r) {
        counter++;
        if (r < 3 && enemies.size() <= 15) {
            double x = rng.nextInt(50) * 20;
            double y = rng.nextInt(50) * 20;
            Idler idler = new Idler(player.getAvatar(), x, y);
            enemies.add(idler);
            insert.add(idler.getEnemy());
        }
        if (r > 3 && r < 5 && enemies.size() < 15) {
            double x = rng.nextInt(50) * 20;
            double y = rng.nextInt(50) * 20;
            Drone drone = new Drone(player.getAvatar(), x, y);
            enemies.add(drone);
            insert.add(drone.getEnemy());
        }
        if (counter % 31 == 0) {
            for (Enemy e : enemies) {
                int move = rng.nextInt(4);
                e.move(move);
            }
        }
    }

    public void moveProjectiles() {
        ArrayList<Projectile> projectiles = player.getMaker().getProjectiles();
        for (int i = 0; i < projectiles.size(); i++) {
            Node projectile = projectiles.get(i).getProjectile();
            projectile.setTranslateX(projectile.getTranslateX() + projectiles.get(i).getxSpeed());
            projectile.setTranslateY(projectile.getTranslateY() + projectiles.get(i).getySpeed());
        }
    }

    public void spawnPowerUps(int r) {
        if (r == 6) {
            ScorePowerUp bonus = new ScorePowerUp();
            powerUps.add(bonus);
            insert.add(bonus.getPowerUp());
        }
        if (r == 7 || r == 8) {
            AmmoPowerUp ammo = new AmmoPowerUp();
            powerUps.add(ammo);
            insert.add(ammo.getPowerUp());
        }
    }
}
