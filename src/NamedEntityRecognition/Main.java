package NamedEntityRecognition;

/**
 * @author Marouane Zoubir Guettatfi
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    //Variables of setting window's coordinates
    private static double xOffset = 0;
    private static double yOffset = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Resources/fxml/MainWindow.fxml"));

        //Making the stage without borders
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //Setting the stage's coordinates
        primaryStage.setX(80);
        primaryStage.setY(80);
        //Making the window draggable
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() + xOffset);
                primaryStage.setY(event.getScreenY() + yOffset);
            }
        });

        //Setting title of the window
        primaryStage.setTitle("SLOA - University of Miskolc");
        //Setting the application's icon
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("Resources/images/icons8.png")));
        //Setting the scene to the stage
        primaryStage.setScene(new Scene(root));
        //Making the stage non-resizable
        primaryStage.setResizable(false);
        //Getting focus for the scene
        root.requestFocus();
        //Showing the stage to the user
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

}
