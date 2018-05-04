/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.domain;

import java.util.Random;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author jussiste
 */
public class ScorePowerUp implements PowerUp {

    Polygon powerUp;

    public ScorePowerUp() {
        this.powerUp = new Polygon();
        powerUp.getPoints().addAll(
            0d, 10d,
            0d, 20d,
            10d, 30d,
            20d, 30d,
            30d, 20d,
            30d, 10d,
            20d, 0d,
            10d, 0d);
        powerUp.setFill(Color.GOLD);
        Random r = new Random();
        int x = r.nextInt(50) * 20;
        int y = r.nextInt(50) * 20;
        this.powerUp.setTranslateX(x);
        this.powerUp.setTranslateY(y);
    }

    public Polygon getPowerUp() {
        return powerUp;
    }

    @Override
    public void powerUp(Player player) {
        player.setScore(player.getScore() + 10);
    }

}
