/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shootemup.domain.KeyController;
import shootemup.domain.Player;

/**
 *
 * @author jussiste
 */
public class KeyControllerTest {

    private Player player;
    private Pane pane;
    private KeyController control;

    @Before
    public void setUp() {
        this.player = new Player("");
        this.pane = new Pane();
        this.control = new KeyController();
    }

    @Test
    public void pressingKeysMovesPlayer() {
        this.control.processInput(player, KeyCode.UP, pane);
        assertEquals(player.getAvatar().getTranslateY(), 480, 0);
        this.control.processInput(player, KeyCode.DOWN, pane);
        assertEquals(player.getAvatar().getTranslateY(), 500, 0);
        this.control.processInput(player, KeyCode.LEFT, pane);
        assertEquals(player.getAvatar().getTranslateX(), 480, 0);
        this.control.processInput(player, KeyCode.RIGHT, pane);
        assertEquals(player.getAvatar().getTranslateX(), 500, 0);
    }

    @Test
    public void WASDkeysCreateProjectiles() {
        this.control.processInput(player, KeyCode.W, pane);
        assertEquals(player.getMaker().getProjectiles().size(), 1);
        this.control.processInput(player, KeyCode.A, pane);
        assertEquals(player.getMaker().getProjectiles().size(), 2);
        this.control.processInput(player, KeyCode.S, pane);
        assertEquals(player.getMaker().getProjectiles().size(), 3);
        this.control.processInput(player, KeyCode.D, pane);
        assertEquals(player.getMaker().getProjectiles().size(), 4);
    }
    public void pressingSomethingElseDoesNothing(){
        this.control.processInput(player, KeyCode.SPACE, pane);
        assertEquals(player.getAvatar().getTranslateX(), 500, 0);
        assertEquals(player.getAvatar().getTranslateY(), 500, 0);
        assertEquals(player.getMaker().getProjectiles().size(), 0);
    }
}
