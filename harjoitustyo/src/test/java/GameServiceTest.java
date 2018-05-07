/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shootemup.domain.Enemy;
import shootemup.domain.GameService;

/**
 *
 * @author jussiste
 */
public class GameServiceTest {

    private GameService service;

    @Before
    public void setUp() {
        this.service = new GameService("Test");
    }

    @Test
    public void startCreatesEnemy() {
        this.service.startingConfig();
        assertEquals(1, this.service.getEnemies().size());
    }

    @Test
    public void playerIntersectWorks() {
        this.service.startingConfig();
        assertEquals(service.enemyIntersect(), false);
        for (int i = 0; i < 20; i++) {
            for (Enemy e : service.getEnemies()) {
                e.move(1);
            }
        }
        assertEquals(service.enemyIntersect(), true);
    }

    @Test
    public void nodeGettersClearLists() {
        this.service.startingConfig();
        assertEquals(1, this.service.getInsert().size());
        assertEquals(service.getInsert().size(), 0);
    }

    @Test
    public void projectileIntersectClearsLists() {
        this.service.startingConfig();
        this.service.getPlayer().getMaker().initProjectile(0, 0, 0, 0);
        this.service.getPlayer().getMaker().initProjectile(service.getPlayer().getAvatar().getTranslateX(), service.getPlayer().getAvatar().getTranslateY(), 0, -200);
        this.service.moveProjectiles();
        this.service.projectileIntersect();
        assertEquals(1, this.service.getProjectileRemoval().size());
        assertEquals(0, this.service.getProjectileRemoval().size());
        assertEquals(1, this.service.getRemoval().size());
        assertEquals(0, this.service.getRemoval().size());
    }
    @Test
    public void powerUpIntersectWorks(){
        this.service.startingConfig();
        this.service.spawnPowerUps(6);
        this.service.getPlayer().getAvatar().setTranslateX(this.service.getPowerUps().get(0).getPowerUp().getTranslateX());
        this.service.getPlayer().getAvatar().setTranslateY(this.service.getPowerUps().get(0).getPowerUp().getTranslateY());
        this.service.powerIntersect();
        assertEquals(10, this.service.getPlayer().getScore());
    }
    @Test
    public void spawnMethodsCreateEntities(){
        this.service.setCounter(29);
        this.service.spawnEnemies(2);
        this.service.spawnEnemies(4);
        assertEquals(this.service.getEnemies().size(), 2);
        this.service.spawnPowerUps(6);
        this.service.spawnPowerUps(7);
        assertEquals(this.service.getPowerUps().size(), 2);
    }
}
