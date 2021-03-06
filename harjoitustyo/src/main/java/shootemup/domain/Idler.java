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
public class Idler implements Enemy{
    private Node enemy;
    private Random rng;
    
    /**
     * Constructor for Idler class. Creates an enemy and positions it to a random place on a board.
     * @param player used so that the enemy doesn't spawn directly on top of player.
     * @param x x-coordinates
     * @param y y-coordinates
     */
    public Idler(Node player, double x, double y) {
        this.rng=new Random();
        Circle mold= new Circle(100);
        mold.setTranslateY(y);
        mold.setTranslateX(x);
        while(mold.getBoundsInParent().intersects(player.getBoundsInParent())){
            x=rng.nextInt(50)*20;
            y=rng.nextInt(50)*20;
            mold.setTranslateY(y);
            mold.setTranslateX(x);
        }
        this.enemy = new Rectangle(20, 20, Color.GREEN);
        this.enemy.setTranslateY(y);
        this.enemy.setTranslateX(x);
    }
    
    public Node getEnemy() {
        return enemy;
    }
    @Override
    public void move(int move) {
        switch(move){
            case 0:
                if(this.enemy.getTranslateY()>0){
                    this.enemy.setTranslateY(this.enemy.getTranslateY()-10);
                }
                break;
            case 1:
                if(this.enemy.getTranslateY()<1000){
                    this.enemy.setTranslateY(this.enemy.getTranslateY()+10);
                }
                break;
            case 2:
                if(this.enemy.getTranslateX()>0){
                    this.enemy.setTranslateX(this.enemy.getTranslateX()-10);
                }
                break;
            case 3:
                if(this.enemy.getTranslateX()<1000){
                    this.enemy.setTranslateX(this.enemy.getTranslateX()+10);
                }
        }
    }    
}
