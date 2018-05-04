/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shootemup.domain.Projectile;
import shootemup.domain.ProjectileMaker;

/**
 *
 * @author jussiste
 */
public class ProjectileMakerTest {

    private Pane map;
    private ProjectileMaker maker;

    @Before
    public void setUp() {
        Pane map = new Pane();
        map.setPrefSize(1000, 1000);
        maker = new ProjectileMaker();
    }

    @Test
    public void constructorCreatesArrayLists() {
        assertEquals(maker.getProjectiles().size(), 0);
    }

    @Test
    public void firingIncreasesLists() {
        maker.initProjectile(0, 0, 0, 0);
        assertEquals(maker.getProjectiles().size(), 1);
    }

    @Test
    public void removingProjectilesRemovesFromList() {
        Node n = maker.initProjectile(0, 0, 0, 0);
        assertEquals(maker.getProjectiles().size(), 1);
        maker.removeProjectile(maker.getProjectiles().get(0));
        assertEquals(maker.getProjectiles().size(), 0);
    }

    @Test
    public void cantShootWhenOutOfAmmo() {
        assertTrue(maker.hasAmmo());
        for (int i = 0; i < 50; i++) {
            maker.hasAmmo();
        }
        assertFalse(maker.hasAmmo());
    }
}
