/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shootemup.domain.ProjectileMaker;
import shootemup.gui.KeyController;
import shootemup.*;
/**
 *
 * @author jussiste
 */
public class KeyControllerTest {
    private KeyController control;
    private Pane pane;
    private ProjectileMaker maker;
    private Rectangle player;
    @Before
    public void setUp() {
        this.control=new KeyController();
        this.maker=new ProjectileMaker();
        pane=new Pane();
        this.player= new Rectangle();
        player.setTranslateX(50);
        player.setTranslateY(50);
    }
    

     @Test
     public void pressingArrowKeysMovesPlayer() {
         control.processInput(player, maker, KeyCode.UP, pane);
         assertEquals(50.0, player.getTranslateX(),0.0);
         assertEquals(30.0, player.getTranslateY(),0.0);
         control.processInput(player, maker, KeyCode.LEFT, pane);
         assertEquals(30.0, player.getTranslateX(),0.0);
         assertEquals(30.0, player.getTranslateY(),0.0);
         control.processInput(player, maker, KeyCode.DOWN, pane);
         assertEquals(30.0, player.getTranslateX(),0.0);
         assertEquals(50.0, player.getTranslateY(),0.0);
         control.processInput(player, maker, KeyCode.RIGHT, pane);
         assertEquals(50.0, player.getTranslateX(),0.0);
         assertEquals(50.0, player.getTranslateY(),0.0);
     }
     @Test
     public void pressingWASDcreatesProjectiles(){
        control.processInput(player, maker, KeyCode.W, pane);
        assertEquals(1, maker.getProjectiles().size());
        control.processInput(player, maker, KeyCode.A, pane);
        assertEquals(2, maker.getProjectiles().size());
        control.processInput(player, maker, KeyCode.S, pane);
        assertEquals(3, maker.getProjectiles().size());
        control.processInput(player, maker, KeyCode.D, pane);
        assertEquals(4, maker.getProjectiles().size());
     }
}
