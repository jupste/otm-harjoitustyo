/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.gui;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import shootemup.*;
/**
 *
 * @author jussiste
 */
public class ScreenLoader {
    private static final int SIZE=1000;
    private Pane root;
    private BorderPane borderRoot;
    private Player player;
    static private long counter;
    private KeyController keycontroller;
    private Button start;
    private Button hiscores;
    private Button instructions;
    private Button exit;
    private Label scores;
    private ProjectileMaker maker;
    private ArrayList<Enemy> enemies;
    private AnimationTimer timer;
    private GameUpdate update;
    
    public ScreenLoader() {
    }
    public Parent startingScreen(Stage stage){
        borderRoot=new BorderPane();
        borderRoot.setPrefSize(SIZE, SIZE);
        HBox buttons=new HBox();
        start=new Button("Start the slaughter");
        hiscores=new Button("Hiscores");
        instructions= new Button("Instructions");
        buttons.getChildren().addAll(start, hiscores, instructions);
        borderRoot.setCenter(buttons);
        player=new Player("");       
        hiscores.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    stage.setScene(new Scene(hiscoresScreen()));
                    exit.setOnAction(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent event) {
                            stage.setScene(new Scene(startingScreen(stage)));
                        }
                    });
                };
            });
        instructions.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(new Scene(instructionScreen()));
                exit.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        stage.setScene(new Scene(startingScreen(stage)));
                    }
                });
            };
        });
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            TextField namefield= new TextField();
            buttons.getChildren().clear();
            start.setText("READY!");
            Label error= new Label();
            buttons.getChildren().addAll(namefield, start, error);

                start.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    if(namefield.getText().length()>0){
                        stage.setScene(new Scene(createContent()));
                        player.setName(namefield.getText());
                        stage.getScene().setOnKeyPressed(e ->{
                        keycontroller.processInput(player.getAvatar(), maker, e.getCode(), root);
                        });
                    }else{
                        error.setText("Must insert a name");
                        error.setTextFill(Color.RED);
                    }
            }});
            }});

        counter=0;
        return borderRoot;
    }
    public Parent createContent(){
        root=new Pane();
        root.setPrefSize(SIZE, SIZE);
        keycontroller=new KeyController();
        maker=new ProjectileMaker();
        enemies=new ArrayList<>();
        root.getChildren().add(player.getAvatar());
        Idler idler= new Idler(player.getAvatar());
        enemies.add(idler);
        root.getChildren().add(idler.getEnemy());
        scores=new Label("Score: "+ 0);
        scores.relocate(900, 10);
        root.getChildren().add(scores);
        update=new GameUpdate(maker, enemies, root, scores, player);
        return root;
    }
    public Parent hiscoresScreen(){
        borderRoot=new BorderPane();
        borderRoot.setPrefSize(SIZE, SIZE);
        exit=new Button("Exit");
        borderRoot.setCenter(exit);
        return borderRoot;
    }
    public Parent instructionScreen(){
        borderRoot=new BorderPane();
        borderRoot.setPrefSize(SIZE, SIZE);
        exit=new Button("Exit");
        borderRoot.setCenter(exit);
        return borderRoot;
    }
}