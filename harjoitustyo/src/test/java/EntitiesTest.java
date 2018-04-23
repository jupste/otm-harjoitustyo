/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.shape.Rectangle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import shootemup.domain.Drone;
import shootemup.domain.Idler;
import shootemup.domain.Player;
import static org.junit.Assert.assertNotEquals;
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
        Player player=new Player("test");
        assertEquals(player.getAvatar().getTranslateX(), 300, 0.0);
        assertEquals(player.getAvatar().getTranslateY(), 300, 0.0);
        assertEquals(player.getName(), "test");
    }
    @Test
    public void enemiesAreCreated(){
        Drone drone=new Drone(new Player("").getAvatar());
        Idler idler= new Idler(new Player("").getAvatar());
        assertNotNull("Drone not created", drone);
        assertNotNull("Idler not created", idler);
    }
    @Test
    public void enemiesMove(){
        Drone drone=new Drone(new Player("").getAvatar());
        Idler idler= new Idler(new Player("").getAvatar());
        double droneXbefore=drone.getEnemy().getTranslateX();
        double droneYbefore=drone.getEnemy().getTranslateY();
        double idlerXbefore=drone.getEnemy().getTranslateX();
        double idlerYbefore=drone.getEnemy().getTranslateY();
        idler.move();
        drone.move();
        if(idler.getEnemy().getTranslateX()==idlerXbefore){
            assertNotEquals(idler.getEnemy().getTranslateY(), idlerYbefore);
        }else{
            assertNotEquals(idler.getEnemy().getTranslateX(), idlerXbefore);
        }
        if(drone.getEnemy().getTranslateX()==droneXbefore){
            assertNotEquals(drone.getEnemy().getTranslateY(), droneYbefore);
        }else{
            assertNotEquals(drone.getEnemy().getTranslateX(), droneXbefore);
        }
    }
} 