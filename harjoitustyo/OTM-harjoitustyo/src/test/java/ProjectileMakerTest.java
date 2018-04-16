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
import shootemup.domain.ProjectileMaker;
import shootemup.domain.Speed;

/**
 *
 * @author jussiste
 */
public class ProjectileMakerTest {
    
    private Pane map;
    private ProjectileMaker maker;
        
    @Before
    public void setUp() {
        Pane map=new Pane();
        map.setPrefSize(1000, 1000);
        maker=new ProjectileMaker();
    }
    
    @Test
    public void constructorCreatesArrayLists(){
        assertEquals(maker.getProjectiles().size(), 0);
        assertEquals(maker.getSpeeds().size(), 0);
    }
    @Test
    public void firingIncreasesLists(){
        maker.initProjectile(0, 0, new Speed(KeyCode.W));
        assertEquals(maker.getProjectiles().size(), 1);
        assertEquals(maker.getSpeeds().size(), 1);
    }
    @Test
    public void removingProjectilesRemovesFromBothLists(){
        Node n=maker.initProjectile(0, 0, new Speed(KeyCode.W));
        assertEquals(maker.getProjectiles().size(), 1);
        assertEquals(maker.getSpeeds().size(), 1);
        maker.removeProjectile(n);
        assertEquals(maker.getProjectiles().size(), 0);
        assertEquals(maker.getSpeeds().size(), 0);
        
    }
    @Test
    public void keyCodesCreateCorrectSpeeds(){
        Speed speed=new Speed(KeyCode.W);
        assertEquals(0.0,speed.getxSpeed(), 0.0);
        assertEquals(-3.0, speed.getySpeed(), 0.0);
        speed=new Speed(KeyCode.A);
        assertEquals(-3.0, speed.getxSpeed(), 0.0);
        assertEquals(0.0, speed.getySpeed(), 0.0);        
        speed=new Speed(KeyCode.S);
        assertEquals(0.0, speed.getxSpeed(), 0.0);
        assertEquals(3.0, speed.getySpeed(), 0.0);
        speed=new Speed(KeyCode.D);
        assertEquals(3.0, speed.getxSpeed(), 0.0);
        assertEquals(0.0, speed.getySpeed(), 0.0);
        speed=new Speed(KeyCode.O);
        assertEquals(0.0, speed.getxSpeed(), 0.0);
    }
    
}
