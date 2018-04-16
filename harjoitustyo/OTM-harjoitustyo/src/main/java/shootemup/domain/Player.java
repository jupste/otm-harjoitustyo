/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jussiste
 */
public class Player{
    String name;
    Rectangle avatar;

    public Player(String name) {
        this.name = name;
        this.avatar= new Rectangle(20, 20 , Color.BLACK);
        avatar.setTranslateY(300);
        avatar.setTranslateX(300);
    }

    public String getName() {
        return name;
    }

    public Rectangle getAvatar() {
        return avatar;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
