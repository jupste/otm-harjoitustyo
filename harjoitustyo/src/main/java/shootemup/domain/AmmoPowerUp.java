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
public class AmmoPowerUp implements PowerUp {
    private Polygon powerUp;

    public AmmoPowerUp() {
        this.powerUp = new Polygon();
        powerUp.getPoints().addAll(
            0d, 0d,
            15d, 0d,
            20d, 2.5,
            20d, 7.5,
            15d, 10d,
            0d, 10d);
        powerUp.setFill(Color.BROWN);
        Random r = new Random();
        int x = r.nextInt(50) * 20;
        int y = r.nextInt(50) * 20;
        this.powerUp.setTranslateX(x);
        this.powerUp.setTranslateY(y);
    }
    
    @Override
    public void powerUp(Player player, ProjectileMaker maker) {
        maker.reload(50);
    }

    @Override
    public Node getPowerUp() {
        return powerUp;
    }
    
}
