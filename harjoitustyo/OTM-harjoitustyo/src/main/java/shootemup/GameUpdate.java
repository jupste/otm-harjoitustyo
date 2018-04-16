/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author jussiste
 */
public class GameUpdate {
    private Random rng;
    private ProjectileMaker maker;
    private ArrayList<Enemy> enemies;
    private int score;
    private Pane root;
    private Label scores;
    private long counter;
    private Player player;
    private AnimationTimer timer;
    
    public GameUpdate(ProjectileMaker maker, ArrayList<Enemy> enemies, Pane root, Label scores, Player player) {
        this.maker = maker;
        this.enemies = enemies;
        this.root = root;
        this.scores = scores;
        this.rng=new Random();
        this.score=0;
        this.counter=0;
        this.player=player;
        this.timer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();
    }

    public AnimationTimer getTimer() {
        return timer;
    }

    public void checkState(){
        for(Enemy e: enemies){
            for(Node p: maker.getProjectiles()){
                if(e.getEnemy().getBoundsInParent().intersects(p.getBoundsInParent())){
                    enemies.remove(e);
                    maker.removeProjectile(p);
                    root.getChildren().remove(p);
                    root.getChildren().remove(e.getEnemy());
                    this.score++;
                    scores.setText("Score: "+Integer.toString(this.score));
                }
            }
            if(e.getEnemy().getBoundsInParent().intersects(player.getAvatar().getBoundsInParent())){
                
            }
        }
    }
    public void onUpdate(){
        int r=rng.nextInt(1000);
        counter++;
        ArrayList<Node> projectiles=maker.getProjectiles();
        ArrayList<Speed> speeds=maker.getSpeeds();
        if(r==0 && enemies.size()<15){
            Idler idler= new Idler(player.getAvatar());
            enemies.add(idler);
            root.getChildren().add(idler.getEnemy());
        }
        if(r==1 && enemies.size()<15){
            Drone drone= new Drone(player.getAvatar());
            enemies.add(drone);
            root.getChildren().add(drone.getEnemy());
        }
        
        for(int i=0; i<projectiles.size(); i++){
            Node projectile= projectiles.get(i);
            Speed speed= speeds.get(i);
            projectile.setTranslateX(projectile.getTranslateX()+speed.getxSpeed());
            projectile.setTranslateY(projectile.getTranslateY()+speed.getySpeed());
            checkState();
        }
        if(counter%100==0){
            for(Enemy e: enemies){
                e.move();
            }
        }
    }
}

