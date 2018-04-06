package shootemup;



import java.util.ArrayList;
import java.util.Random;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author jussiste
 */


public class ShootEmUp extends Application {
    private static final int SIZE=1000;
    private Pane root;
    private Node player;
    private int score;
    static private long counter;
    private KeyController keycontroller;
    private Button start;
    private Button hiscores;
    private Button instructions;
    private Label scores;
    private AnimationTimer timer;
    private ProjectileMaker projectilemaker;
    private ArrayList<Enemy> enemies;
    private Random rng;
    
    private Parent startingScreen(){
        root=new Pane();
        root.setPrefSize(SIZE, SIZE);
        start=new Button("Start the slaughter");
        hiscores=new Button("Hiscores");
        instructions= new Button("Instructions");
        start.relocate(400, 400);
        hiscores.relocate(550,400);
        instructions.relocate(500, 450);
        root.getChildren().add(start);
        root.getChildren().add(hiscores);
        root.getChildren().add(instructions);
        counter=0;
        return root;
    }
    private Parent createContent(){
        root=new Pane();
        root.setPrefSize(SIZE, SIZE);
        keycontroller=new KeyController();
        player=initPlayer();
        projectilemaker=new ProjectileMaker();
        enemies=new ArrayList<>();
        root.getChildren().add(player);
        Idler idler= new Idler(player);
        enemies.add(idler);
        root.getChildren().add(idler.getEnemy());
        score=0;
        scores=new Label("Score: "+ Integer.toString(score));
        scores.relocate(900, 10);
        root.getChildren().add(scores);
        rng=new Random();
        timer=new AnimationTimer(){
        @Override
        public void handle(long now){
            onUpdate();
        }};
        timer.start();
        return root;
    }
    private Node initPlayer(){
        Rectangle rect= new Rectangle(20, 20 , Color.BLACK);
        rect.setTranslateY(300);
        rect.setTranslateX(300);
        return rect;
    }
    private void checkState(){
        for(Enemy e: enemies){
            for(Node p: projectilemaker.getProjectiles()){
                if(e.getEnemy().getBoundsInParent().intersects(p.getBoundsInParent())){
                    enemies.remove(e);
                    projectilemaker.removeProjectile(p);
                    root.getChildren().remove(p);
                    root.getChildren().remove(e.getEnemy());
                    score++;
                    scores.setText("Score: "+Integer.toString(score));
                }
            }
        }
    }
    private void onUpdate(){
        int r=rng.nextInt(1000);
        counter++;
        ArrayList<Node> projectiles=projectilemaker.getProjectiles();
        ArrayList<Speed> speeds=projectilemaker.getSpeeds();
        if(r==0 && enemies.size()<15){
            Idler idler= new Idler(player);
            enemies.add(idler);
            root.getChildren().add(idler.getEnemy());
        }
        if(r==1 && enemies.size()<15){
            Drone drone= new Drone(player);
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
    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(startingScreen()));
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            stage.setScene(new Scene(createContent()));
            stage.getScene().setOnKeyPressed(event ->{
            keycontroller.processInput(player, projectilemaker, event.getCode(), root);            
        });
        }});
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}