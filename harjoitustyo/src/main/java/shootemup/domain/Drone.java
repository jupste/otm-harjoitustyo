/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.domain;

import java.util.Random;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jussiste
 */
public class Drone implements Enemy{

    private Random rng;
    private int direction;
    private Node enemy;
     /**
     * Constructor for Drone class. Creates an enemy and positions it to a random place on a board.
     * @param player used so that the enemy doesn't spawn directly on top of player.
     * @param x x-coordinates
     * @param y y-coordinates
     */
    public Drone(Node player, double x, double y) {
        this.rng=new Random();
        this.direction= 0;
        Circle mold= new Circle(100);
        mold.setTranslateY(y);
        mold.setTranslateX(x);
        while(mold.getBoundsInParent().intersects(player.getBoundsInParent())){
            x=rng.nextInt(50)*20;
            y=rng.nextInt(50)*20;
            mold.setTranslateY(y);
            mold.setTranslateX(x);
        }
        this.enemy = new Rectangle(20, 20, Color.PURPLE);
        this.enemy.setTranslateY(y);
        this.enemy.setTranslateX(x);
    }
    @Override
    public void move(int dir) {
        if(dir==0){
            direction=(direction+1)%4;
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
        
    @Override
    public Node getEnemy() {
        return enemy;
    }
    
}

