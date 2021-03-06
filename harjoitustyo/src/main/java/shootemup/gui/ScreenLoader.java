/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootemup.gui;

import javafx.collections.ObservableList;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import shootemup.dao.DatabaseManager;
import shootemup.dao.EntryObject;

/**
 *
 * @author jussiste
 */
public class ScreenLoader {

    private static final int SIZE = 1000;
    private Pane root;
    private BorderPane borderRoot;
    private Button start;
    private Button hiscores;
    private Button instructions;
    private Button exit;
    private Label scores;
    private Label ammo;
    private Stage stage;
    private ButtonBar buttons;
    private DatabaseManager dbManager;
    private GameUpdate session;
    private Button clear;

    public ScreenLoader(Stage stage) {
        this.root = new Pane();
        root.setPrefSize(SIZE, SIZE);
        borderRoot = new BorderPane();
        start = new Button();
        hiscores = new Button();
        instructions = new Button();
        exit = new Button();
        scores = new Label();
        this.session = new GameUpdate(this);
        this.stage = stage;
        dbManager = new DatabaseManager("jdbc:sqlite:hiscoreTable.db");
    }

    public Stage getStage() {
        return stage;
    }

    public Label getScores() {
        return scores;
    }

    /**
     * Sets up a Pane that contains the starting screen. Also sets up all
     * listeners for menu swapping.
     *
     * @param stage the stage passed down from main
     * @return a new BorderPane
     */
    public Parent startingScreen(Stage stage) {
        borderRoot = new BorderPane();
        borderRoot.setPrefSize(SIZE, SIZE);
        buttons = new ButtonBar();
        this.stage = stage;
        start = new Button("Start the slaughter");
        hiscores = new Button("Hiscores");
        instructions = new Button("Instructions");
        buttons.getButtons().addAll(start, hiscores, instructions);
        borderRoot.setCenter(buttons);

        hiscores.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(new Scene(hiscoresScreen()));
            }
        ;
        });
        instructions.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(new Scene(instructionScreen()));
            }
        ;
        });
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                TextField namefield = new TextField();
                buttons.getButtons().clear();
                start.setText("READY!");
                Label error = new Label();
                buttons.getButtons().addAll(namefield, start, error);

                start.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (namefield.getText().length() > 0) {
                            stage.setScene(new Scene(createContent()));
                            session.startGame(namefield.getText());
                        } else {
                            error.setText("Must insert a name");
                            error.setTextFill(Color.RED);
                        }
                    }
                });
            }
        });
        return borderRoot;
    }

    /**
     * Creates a new Pane that is used as the stage of the game. After this is
     * used startingScreen gives control to GameUpdate.
     *
     * @return a new Pane
     */
    public Parent createContent() {
        root = new Pane();
        root.setPrefSize(SIZE, SIZE);
        ammo = new Label("Ammo: " + 50);
        scores = new Label("Score: " + 0);
        ammo.relocate(900, 30);
        scores.relocate(900, 10);
        root.getChildren().addAll(scores, ammo);
        return root;
    }

    public Label getAmmo() {
        return ammo;
    }

    /**
     * Changes the screen to hiscore screen. Hiscores are recovered by the DAO-object.
     * @return a new BorderPane
     */
    public Parent hiscoresScreen() {
        borderRoot = new BorderPane();
        borderRoot.setPrefSize(SIZE, SIZE);
        exit = new Button("Exit");
        clear = new Button("Clear scores");
        buttons.getButtons().clear();
        ObservableList<EntryObject> scoreboard = dbManager.getScores();
        TableView table = new TableView();
        TableColumn names = new TableColumn("Name");
        names.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn scores = new TableColumn("Score");
        scores.setCellValueFactory(new PropertyValueFactory("score"));
        table.getColumns().addAll(names, scores);
        exit.setOnAction(new EventHandler<ActionEvent>() {
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

    /**
     * Changes the menu to instruction screen.
     *
     * @return a new BorderPane
     */
    public Parent instructionScreen() {
        borderRoot = new BorderPane();
        borderRoot.setPrefSize(SIZE, SIZE);
        ImageView image = new ImageView(new Image("https://raw.githubusercontent.com/jupste/otm-harjoitustyo/master/harjoitustyo/images/gameplay.png"));
        image.setFitHeight(800);
        image.setFitWidth(800);
        exit = new Button("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(new Scene(startingScreen(stage)));
            }
        });
        borderRoot.setCenter(exit);
        borderRoot.setRight(image);
        return borderRoot;
    }
}
