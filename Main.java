import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
//new comment to test git , it works
/*
 * This is the GUI initiator class, it takes you to the APP class
 */
public class Main extends Application{
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception 
    {
    	
    	
    	App app=new App(primaryStage);
    	app.addSavedGamesToChoiceBox();
        app.bStartMenu();
        primaryStage.show();
        
    }
   
   
}