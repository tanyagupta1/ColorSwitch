import javafx.event.ActionEvent;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;
/*
 * This class is the App class and has all the main menu display methods, list of saved games
 */
public class App
{
	static Stage stage;
	List<Game> savedGames=new ArrayList<>();
	static ChoiceBox<String> choicebox=new ChoiceBox<>();
	public App(Stage s)
	{
		stage=s;
	}
	public static void addSavedGamesToChoiceBox()
	{
		try {
			saveNames data = (saveNames) ResourceManager.load("listofgames.save");
			if(data.len>=1)
			{
				choicebox.getItems().add(data.game1);
			}
			if(data.len>=2)
			{
				choicebox.getItems().add(data.game2);
			}
			if(data.len>=3)
			{
				choicebox.getItems().add(data.game3);
			}
		}
		catch (Exception e) {
			System.out.println("No saved games");
		}
		//savedGames.add(g);
		//choicebox.getItems().add(gameName);
	}
	public static void saveGamesFromChoiceBox()
	{
		if(choicebox.getItems().isEmpty()) return;
		
		int len=choicebox.getItems().size();
		System.out.println("length=="+len);
		saveNames names=new saveNames();
		if(len>=1) {names.game1=choicebox.getItems().get(0); names.len=1;}
		if(len>=2) {names.game2=choicebox.getItems().get(1); names.len=2;}
		if(len>=3) {names.game3=choicebox.getItems().get(2); names.len=3;}
		try {
            //ResourceManager.save(data, "2.save");
        	ResourceManager.save(names, "listofgames.save");

        }
        catch (Exception e) {
            System.out.println("Couldn't save: " + e.getMessage());
        }
	}

public static void  bNewGame() 
{
	VBox newGameLayout = new VBox(20);
	Label newG=new Label("NEW GAME!!!");
	newGameLayout.getChildren().addAll(newG);
	Scene startNewGame=new Scene(newGameLayout,300,250);
	stage.setScene(startNewGame);
	Game g=new Game(stage);
	try {
		
	g.displayGameWindow();
		} 
		catch(Exception e) {System.out.println("exceptionnnnnn");}
	
}
public static void  bChristmasGame() 
{
	VBox newGameLayout = new VBox(20);
	Label newG=new Label("NEW GAME!!!");
	newGameLayout.getChildren().addAll(newG);
	Scene startNewGame=new Scene(newGameLayout,300,250);
	stage.setScene(startNewGame);
	christmasGame g=new christmasGame(stage);
	try {
		
	g.displayGameWindow();
		} 
		catch(Exception e) {System.out.println("exceptionnnnnn");}
	
}
public static void bSavedGames() 
{
	 Button goBack=new Button("go back"); 
	 Label savedG=new Label("Saved Games");
	 //dummy entries
	 choicebox.setMinHeight(50);choicebox.setMinWidth(100);choicebox.setPrefWidth(100);
	// choicebox.getItems().add("Game 1"); choicebox.getItems().add("Game 2");
	//button formating
		goBack.setMinWidth(300);goBack.setMinHeight(50);savedG.setMinWidth(300);savedG.setMinHeight(50);
		goBack.setStyle("-fx-font-size: 2em;");
		savedG.setStyle("-fx-font-size: 4em;-fx-text-fill: #ffffff");
	    //button formating finish
	 VBox savedGameLayout = new VBox(20); savedGameLayout.setAlignment(Pos.CENTER);
	 savedGameLayout.setSpacing(75);
	 savedGameLayout.setStyle("-fx-background-color: #000000;");
	 savedGameLayout.getChildren().addAll(savedG,choicebox,goBack);
	 Scene savedGames= new Scene(savedGameLayout,600,600);
	 choicebox.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->loadGame(newValue+".save"));
	 stage.setScene(savedGames);
	 goBack.setOnAction(e->{bStartMenu();});
}


public static void loadGame(String filename){
	try {
		SaveData data = (SaveData) ResourceManager.load(filename);
		VBox newGameLayout = new VBox(20);
		Label newG=new Label("NEW GAME!!!");
		newGameLayout.getChildren().addAll(newG);
		Scene startNewGame=new Scene(newGameLayout,300,250);
		stage.setScene(startNewGame);
		Game g=new Game(stage,data);
		//temporarily disabled load saved game
		//Game g=new Game(stage);
		try {

			g.displayGameWindow();
		}
		catch(Exception e) {System.out.println("exceptionnnnnn");}


	}
	catch (Exception e) {
		e.printStackTrace();
	}
	
}
public static void bStartMenu()
{
	stage.setTitle("Color Switch!");
	Button newGame = new Button("New Game");
	Button christmasGame = new Button("New Christmas Game");
	Button existingGame=new Button("Resume Existing Game");
	Button exit=new Button("Exit");
	Label myHeading=new Label("Color Switch"); 
	myHeading.setStyle("-fx-font-size: 5em;-fx-text-fill: #ffffff");
	VBox startMenuLayout = new VBox(20); startMenuLayout.setSpacing(75);startMenuLayout.setStyle("-fx-background-color: #000000;");
	//button formating
	myHeading.setMinWidth(300);myHeading.setMinHeight(50);
	exit.setMinWidth(300);newGame.setMinWidth(300);existingGame.setMinWidth(300);christmasGame.setMinWidth(300);
	exit.setMinHeight(50);newGame.setMinHeight(50);existingGame.setMinHeight(50);christmasGame.setMinHeight(50);
	exit.setStyle("-fx-font-size: 2em;");newGame.setStyle("-fx-font-size: 2em;");existingGame.setStyle("-fx-font-size: 2em;");
	christmasGame.setStyle("-fx-font-size: 2em;");
    //button formating finish
	startMenuLayout.getChildren().addAll(myHeading,newGame,christmasGame,existingGame,exit);startMenuLayout.setAlignment(Pos.CENTER);
    Scene startMenu=new Scene(startMenuLayout,600,600,Color.RED);
    stage.setScene(startMenu);
    newGame.setOnAction(e->{bNewGame();});
    christmasGame.setOnAction(e->{bChristmasGame();});
    existingGame.setOnAction(e->{bSavedGames();});
	//existingGame.setOnAction(e->{loadGame("game1.save");});
	exit.setOnAction(e->{ saveGamesFromChoiceBox(); stage.close();});
}



}

