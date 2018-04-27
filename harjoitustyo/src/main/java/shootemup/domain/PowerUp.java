/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.domain;

import javafx.scene.Node;

/**
 *
 * @author jussiste
 */
public interface PowerUp {

    /**
     * A method that determines what happens when player picks up a power up,
     * @param player
     * @param maker
     */
    public void powerUp(Player player, ProjectileMaker maker);
    public Node getPowerUp();
}
