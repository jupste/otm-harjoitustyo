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
     * A method that determines what happens when player picks up a power up. ScorePowerUp increases score by 50, AmmoPowerUp increases available ammo.
     * @param player used by ScorePowerUp to increase score.
     * @param maker used by AmmoPowerUp to increase ammo.
     */
    public void powerUp(Player player, ProjectileMaker maker);
    public Node getPowerUp();
}
