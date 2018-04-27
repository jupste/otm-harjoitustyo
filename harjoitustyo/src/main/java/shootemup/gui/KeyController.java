/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.gui;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import shootemup.domain.ProjectileMaker;
import shootemup.domain.Speed;

/**
 *
 * @author jussiste
 */
public class KeyController {

    public void processInput(GameUpdate game, KeyCode key) {
        Speed speed;
        switch (key) {
            case UP:
                if (game.getPlayer().getAvatar().getTranslateY() > 0) {
                    game.getPlayer().getAvatar().setTranslateY(game.getPlayer().getAvatar().getTranslateY() - 20);
                }
                break;
            case DOWN:
                if (game.getPlayer().getAvatar().getTranslateY() < 1000) {
                    game.getPlayer().getAvatar().setTranslateY(game.getPlayer().getAvatar().getTranslateY() + 20);
                }
                break;
            case RIGHT:
                if (game.getPlayer().getAvatar().getTranslateX() < 1000) {
                    game.getPlayer().getAvatar().setTranslateX(game.getPlayer().getAvatar().getTranslateX() + 20);
                }
                break;
            case LEFT:
                if (game.getPlayer().getAvatar().getTranslateX() > 0) {
                    game.getPlayer().getAvatar().setTranslateX(game.getPlayer().getAvatar().getTranslateX() - 20);
                }
                break;
            case W:
                if (game.getMaker().hasAmmo()) {
                    speed = new Speed(key);
                    game.getLoader().getRoot().getChildren().add(game.getMaker().initProjectile(game.getPlayer().getAvatar().getTranslateX(), game.getPlayer().getAvatar().getTranslateY(), speed));
                    game.getLoader().getAmmo().setText("Ammo: "+game.getMaker().getAmmo());
                }
                break;
            case A:
                if (game.getMaker().hasAmmo()) {
                    speed = new Speed(key);
                    game.getLoader().getRoot().getChildren().add(game.getMaker().initProjectile(game.getPlayer().getAvatar().getTranslateX(), game.getPlayer().getAvatar().getTranslateY(), speed));
                    game.getLoader().getAmmo().setText("Ammo: "+game.getMaker().getAmmo());
                }
                break;
            case S:
                if (game.getMaker().hasAmmo()) {
                    speed = new Speed(key);
                    game.getLoader().getRoot().getChildren().add(game.getMaker().initProjectile(game.getPlayer().getAvatar().getTranslateX(), game.getPlayer().getAvatar().getTranslateY(), speed));
                    game.getLoader().getAmmo().setText("Ammo: "+game.getMaker().getAmmo());
                }
                break;
            case D:
                if (game.getMaker().hasAmmo()) {
                    speed = new Speed(key);
                    game.getLoader().getRoot().getChildren().add(game.getMaker().initProjectile(game.getPlayer().getAvatar().getTranslateX(), game.getPlayer().getAvatar().getTranslateY(), speed));
                    game.getLoader().getAmmo().setText("Ammo: "+game.getMaker().getAmmo());
                }
                break;
            default:
                break;
        }
    }
}
