/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.gui;


import static java.awt.SystemColor.text;
import static java.lang.Integer.min;
import java.util.ArrayList;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.ObservableList;
import shootemup.domain.GameUpdate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import shootemup.*;
import shootemup.dao.DatabaseManager;
import shootemup.dao.EntryObject;
/**
 *
 * @author jussiste
 */
public class ScreenLoader {
    private static final int SIZE=1000;
    private Pane root;
    private BorderPane borderRoot;
    private Button start;
    private Button hiscores;
    private Button instructions;
    private Button exit;
    private Label scores;
    private Stage stage;    
    private ButtonBar buttons;
    private DatabaseManager dbManager; 
    private Button clear;
    
    public ScreenLoader(Stage stage) {
        this.root = new Pane();
        root.setPrefSize(SIZE, SIZE);
        borderRoot= new BorderPane();
        start= new Button();
        hiscores= new Button();
        instructions= new Button();
        exit= new Button();
        scores=new Label();
        this.stage=stage;
        dbManager= new DatabaseManager("jdbc:sqlite:hiscoreTable.db");
    }

    public Stage getStage() {
        return stage;
    }
    
    public Label getScores() {
        return scores;
    }

    public Parent startingScreen(Stage stage){
        borderRoot=new BorderPane();
        borderRoot.setPrefSize(SIZE, SIZE);
        buttons=new ButtonBar();
        this.stage=stage;
        start=new Button("Start the slaughter");
        hiscores=new Button("Hiscores");
        instructions= new Button("Instructions");
        buttons.getButtons().addAll(start, hiscores, instructions);
        borderRoot.setCenter(buttons);
        ScreenLoader load=this;

        
        hiscores.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    stage.setScene(new Scene(hiscoresScreen()));                        
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
            @Override 
            public void handle(ActionEvent e) {
                TextField namefield= new TextField();
                buttons.getButtons().clear();
                start.setText("READY!");
                Label error= new Label();
                buttons.getButtons().addAll(namefield, start, error);
                
                start.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        if(namefield.getText().length()>0){
                            stage.setScene(new Scene(createContent()));
                            GameUpdate session= new GameUpdate(load, namefield.getText()); 
                        }else{
                            error.setText("Must insert a name");
                            error.setTextFill(Color.RED);
                        }                    
                }});
            }});
        return borderRoot;
    }
    public Parent createContent(){
        root=new Pane();
        root.setPrefSize(SIZE, SIZE);
        scores=new Label("Score: "+ 0);
        scores.relocate(900, 10);
        root.getChildren().add(scores);
        return root;
    }
    public Parent hiscoresScreen(){
        borderRoot=new BorderPane();
        borderRoot.setPrefSize(SIZE, SIZE);
        exit=new Button("Exit");
        clear= new Button("Clear scores");
        buttons.getButtons().clear();
        ObservableList<EntryObject> scoreboard=dbManager.getScores();
        TableView table=new TableView();
        TableColumn names= new TableColumn("Name");
        names.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn scores= new TableColumn("Score");
        scores.setCellValueFactory(new PropertyValueFactory("score"));
        table.getColumns().addAll(names, scores);
        exit.setOnAction(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent e) {
                            stage.setScene(new Scene(startingScreen(stage)));
                        }
                    });
                    clear.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            dbManager.clearTable(); 
                            table.getColumns().clear();
                        }
                    });

        borderRoot.setLeft(table);
        table.setItems(scoreboard);
        buttons.getButtons().addAll(clear, exit);
        borderRoot.setCenter(buttons);
        return borderRoot;
    }

    public DatabaseManager getDbManager() {
        return dbManager;
    }

    public Pane getRoot() {
        return root;
    }
    public Parent instructionScreen(){
        borderRoot=new BorderPane();
        borderRoot.setPrefSize(SIZE, SIZE);
        exit=new Button("Exit");
        borderRoot.setCenter(exit);
        return borderRoot;
    }
}