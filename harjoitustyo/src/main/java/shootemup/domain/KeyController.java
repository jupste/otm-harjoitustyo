/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.domain;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;


/**
 *
 * @author jussiste
 */
public class KeyController {

    /**
     * Moves the player and creates projectiles by key inputs.
     * @param player player
     * @param key key pressed
     * @param pane pane
     */
    public void processInput(Player player, KeyCode key, Pane pane) {
        switch (key) {
            case UP:
                if (player.getAvatar().getTranslateY() > 0) {
                    player.getAvatar().setTranslateY(player.getAvatar().getTranslateY() - 20);
                }
                break;
            case DOWN:
                if (player.getAvatar().getTranslateY() < 1000) {
                    player.getAvatar().setTranslateY(player.getAvatar().getTranslateY() + 20);
                }
                break;
            case RIGHT:
                if (player.getAvatar().getTranslateX() < 1000) {
                    player.getAvatar().setTranslateX(player.getAvatar().getTranslateX() + 20);
                }
                break;
            case LEFT:
                if (player.getAvatar().getTranslateX() > 0) {
                    player.getAvatar().setTranslateX(player.getAvatar().getTranslateX() - 20);
                }
                break;
            case W:
                if (player.getMaker().hasAmmo()) {
                    pane.getChildren().add(player.getMaker().initProjectile(player.getAvatar().getTranslateX(), player.getAvatar().getTranslateY(), 0, -3));
                }
                break;
            case A:
                if (player.getMaker().hasAmmo()) {
                    pane.getChildren().add(player.getMaker().initProjectile(player.getAvatar().getTranslateX(), player.getAvatar().getTranslateY(), -3, 0));
                }
                break;
            case S:
                if (player.getMaker().hasAmmo()) {
                    pane.getChildren().add(player.getMaker().initProjectile(player.getAvatar().getTranslateX(), player.getAvatar().getTranslateY(), 0, 3));
                }
                break;
            case D:
                if (player.getMaker().hasAmmo()) {
                    pane.getChildren().add(player.getMaker().initProjectile(player.getAvatar().getTranslateX(), player.getAvatar().getTranslateY(), 3, 0));
                }
                break;
            default:
                break;
        }
    }
}
