/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import shootemup.domain.Drone;
import shootemup.domain.Idler;
import shootemup.domain.Player;
import static org.junit.Assert.assertNotEquals;
import shootemup.domain.AmmoPowerUp;
import shootemup.domain.ProjectileMaker;
import shootemup.domain.ScorePowerUp;

/**
 *
 * @author jussiste
 */
public class EntitiesTest {

    @Before
    public void setUp() {
    }

    @Test
    public void playerHasNodeAndName() {
        Player player = new Player("test");
        assertEquals(player.getAvatar().getTranslateX(), 500, 0.0);
        assertEquals(player.getAvatar().getTranslateY(), 500, 0.0);
        assertEquals(player.getName(), "test");
    }

    @Test
    public void enemiesAreCreated() {
        Drone drone = new Drone(new Player("").getAvatar(), 1, 1);
        Idler idler = new Idler(new Player("").getAvatar(), 1, 1);
        assertNotNull("Drone not created", drone);
        assertNotNull("Idler not created", idler);
    }

    @Test
    public void enemiesMove() {
        Drone drone = new Drone(new Player("").getAvatar(), 200, 200);
        Idler idler = new Idler(new Player("").getAvatar(), 200, 200);
        double droneXbefore = drone.getEnemy().getTranslateX();
        double droneYbefore = drone.getEnemy().getTranslateY();
        double idlerXbefore = idler.getEnemy().getTranslateX();
        double idlerYbefore = idler.getEnemy().getTranslateY();
        idler.move(0);
        assertEquals(idler.getEnemy().getTranslateY(), idlerYbefore - 10, 0.0);
        idler.move(1);
        assertEquals(idler.getEnemy().getTranslateY(), idlerYbefore, 0.0);
        idler.move(2);
        assertEquals(idler.getEnemy().getTranslateX(), idlerXbefore - 10, 0.0);
        idler.move(3);
        assertEquals(idler.getEnemy().getTranslateX(), idlerXbefore, 0.0);
        drone.move(0);
        assertEquals(drone.getEnemy().getTranslateY(), droneYbefore + 15, 0.0);
        drone.move(0);
        assertEquals(drone.getEnemy().getTranslateX(), droneXbefore - 15, 0.0);
        drone.move(0);
        assertEquals(drone.getEnemy().getTranslateX(), droneXbefore, 0.0);
        drone.move(0);
        assertEquals(drone.getEnemy().getTranslateY(), droneYbefore, 0.0);
    }

    @Test
    public void enemiesDontSpawnOnPlayer() {
        Idler i = new Idler(new Player("").getAvatar(), 500, 500);
        Drone d = new Drone(new Player("").getAvatar(), 500, 500);
        assertNotEquals(i.getEnemy().getTranslateY(), 500, 0.0);
        assertNotEquals(i.getEnemy().getTranslateX(), 500, 0.0);
        assertNotEquals(d.getEnemy().getTranslateY(), 500, 0.0);
        assertNotEquals(d.getEnemy().getTranslateX(), 500, 0.0);
    }

    @Test
    public void powerUpsAreCreated() {
        ScorePowerUp bonus = new ScorePowerUp();
        AmmoPowerUp ammo = new AmmoPowerUp();
        assertNotNull(bonus);
        assertNotNull(ammo);
    }

    @Test
    public void powerUpsIncreaseValues() {
        ScorePowerUp bonus = new ScorePowerUp();
        AmmoPowerUp ammo = new AmmoPowerUp();
        Player p = new Player("test");
        ProjectileMaker maker = new ProjectileMaker();
        bonus.powerUp(p, maker);
        assertEquals(10, p.getScore());
        ammo.powerUp(p, maker);
        assertEquals(100, maker.getAmmo());
    }
}
