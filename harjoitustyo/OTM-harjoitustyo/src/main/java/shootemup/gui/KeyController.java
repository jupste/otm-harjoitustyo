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
    
    public void processInput(Node player, ProjectileMaker maker, KeyCode key, Pane root){
        Speed speed;
            switch(key){
                case UP:
                    if(player.getTranslateY()>0){
                        player.setTranslateY(player.getTranslateY()-20);
                    }
                    break;
                case DOWN:
                    if(player.getTranslateY()<1000){
                        player.setTranslateY(player.getTranslateY()+20);
                    }
                    break;
                case RIGHT:
                    if(player.getTranslateX()<1000){
                        player.setTranslateX(player.getTranslateX()+20);
                    }
                    break;
                case LEFT:
                    if(player.getTranslateX()>0){
                        player.setTranslateX(player.getTranslateX()-20);
                    }
                    break;
                case W:
                    speed= new Speed(key);
                        root.getChildren().add(maker.initProjectile(player.getTranslateX(), player.getTranslateY(), speed));
                    break;
                case A:
                    speed= new Speed(key);
                        root.getChildren().add(maker.initProjectile(player.getTranslateX(), player.getTranslateY(), speed));
                    break;
                case S:
                    speed= new Speed(key);
                    root.getChildren().add(maker.initProjectile(player.getTranslateX(), player.getTranslateY(), speed));
                    break;
                case D:
                    speed= new Speed(key);
                    root.getChildren().add(maker.initProjectile(player.getTranslateX(), player.getTranslateY(), speed));
                    break;
                default:
                    break;
            }
    }
}
