package shootemup;


import javafx.application.Application;
import javafx.scene.Scene;
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
        loader=new ScreenLoader(stage);
   
        stage.setScene(new Scene(loader.startingScreen(stage)));
        stage.show();
    }
    /**
     * Main method.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}