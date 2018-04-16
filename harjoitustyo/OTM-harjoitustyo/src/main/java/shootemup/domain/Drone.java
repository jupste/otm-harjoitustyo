/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.domain;

import java.util.Random;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jussiste
 */
public class Drone implements Enemy{

    public Node getEnemy() {
        return enemy;
    }
    Random rng;
    int direction;
    Node enemy;

    public Drone(Node player) {
        this.rng=new Random();
        this.direction= rng.nextInt(4);
        int x=rng.nextInt(50)*20;
        int y=rng.nextInt(50)*20;
        this.enemy = new Rectangle(20, 20, Color.PURPLE);
        this.enemy.setTranslateY(y);
        this.enemy.setTranslateX(x);
    }
    
    @Override
    public void move() {
        int random=rng.nextInt(10);
        if(random==0){
            direction=rng.nextInt(4);
        }
        switch(direction){
            case 0:
                if(this.enemy.getTranslateY()>0){
                    this.enemy.setTranslateY(this.enemy.getTranslateY()-15);
                }
                break;
            case 1:
                if(this.enemy.getTranslateY()<1000){
                    this.enemy.setTranslateY(this.enemy.getTranslateY()+15);
                }
                break;
            case 2:
                if(this.enemy.getTranslateX()>0){
                    this.enemy.setTranslateX(this.enemy.getTranslateX()-15);
                }
                break;
            case 3:
                if(this.enemy.getTranslateX()<1000){
                    this.enemy.setTranslateX(this.enemy.getTranslateX()+15);
                }
        }
    }
    
}

