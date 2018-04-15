package shootemup;


import shootemup.*;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import shootemup.gui.ScreenLoader;

/**
 *
 * @author jussiste
 */


public class ShootEmUp extends Application {
    private ScreenLoader loader;    
    @Override
    public void start(Stage stage) {
        loader=new ScreenLoader();
        stage.setScene(new Scene(loader.startingScreen(stage)));
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}