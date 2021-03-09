import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Iterator;

import static javafx.application.Application.launch;

//NOTES
// Obstacle outer radius 90.5px
// Obstacle inner radius 63.5 px
// width = 27
// start height 149px
public class christmasGame {

   
    Stage stage;
    private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
    private HashMap<String,Integer> colorCode=new HashMap<>();
    String name;


    private double or=90.5;
    private double ir=63.5;
    private double thickness=or-ir;
    private Pane appRoot ;
    private Pane gameRoot ;
    private Pane uiRoot ;
    private double starPos=300-or+50;   //star height
    private double colorSwitcherPos;
    private long timeStart;
    private double ballRadius=15;
    ImageView ivStar;
    private boolean ENTER_pressed=false;
    ImageView iv;
    private Player ball;
    int star1;
    int colorSwitcherIndex;
    int obstacleIndex;
    private Label score;
    Button pause;
    boolean paused=false;
    Button saveState;
    ArrayList<Obstacle> obstacle=new ArrayList<>();
    ArrayList<Star> star=new ArrayList<>();
    ArrayList<ColorSwitcher> colorSwitcher=new ArrayList<>();
    AnimationTimer timer;
    int currentObstacle=0;

    long highscoregame=0;
    long maxscore=0;
    MediaPlayer starsound;
    MediaPlayer mediaPlayer;
    public christmasGame(Stage s)
    {
        this.stage=s;
        appRoot = new Pane();
        gameRoot = new Pane();
        uiRoot = new Pane();
        ball=new Player(800);

        String musicFile = "ctheme.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
       
        mediaPlayer = new MediaPlayer(sound);
        MediaView mediaView = new MediaView(mediaPlayer);
        gameRoot.getChildren().add(mediaView);
        mediaPlayer.play();
        

          

        int yCor =300;
        for(int i=0;i<10;i++)
        {
        	
        	obstacle.add(new Obstacle(yCor,"Images/obstacle11.png"));
        	//obstacle.add(new Obstacle(yCor,"Images/obs2.JPG"));
        	if(i==0)star.add(new Star(yCor+120));
        	else if(i==1)star.add(new Star(yCor+120,"Images/greenrib.png"));
        	else if(i==2)star.add(new Star(yCor+120,"Images/ornament.JPG"));
        	else if(i==3)star.add(new Star(yCor+120,"Images/bell.JPG"));
    		else if(i==4)star.add(new Star(yCor+120,"Images/cane.JPG"));
    		else if(i==5)star.add(new Star(yCor+120,"Images/gift.JPG"));
    		else star.add(new Star(yCor+120));
        	
        	gameRoot.getChildren().add(obstacle.get(i).iv);
        	gameRoot.getChildren().add(star.get(i).ivStar);
        	
        
        	
        	if(i%2==0)
        	{
        		colorSwitcher.add(new ColorSwitcher(yCor-75));
        		gameRoot.getChildren().add(colorSwitcher.get(i/2).colorsw);
        	}
        	yCor-=300;
        }
       colorSwitcherPos=colorSwitcher.get(0).getPositionY();
       gameRoot.getChildren().addAll(ball.ballImage);
       star1=0;
       starPos=star.get(star1).starPos;
       colorSwitcherIndex=0;
       obstacleIndex=0;
       
    }
    
   
    private void initContent() throws FileNotFoundException {

        // Top menu in game
        
        HBox hbox = new HBox(); hbox.setSpacing(20);
        BackgroundFill background_fill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        hbox.setBackground(background);
        pause=new Button("Pause"); saveState=new Button("See Your Tree");
        javafx.scene.control.Label ycor=new javafx.scene.control.Label("");ycor.setTextFill(Color.web("#0076a3"));
        ycor.setFont(new javafx.scene.text.Font("Arial", 15));

        saveState.setOnAction(e->App.bStartMenu());
        score=new Label("Score : "+ball.score);score.setTextFill(Color.web("#0076a3"));
        score.setFont(new Font("Arial", 15));
        hbox.getChildren().addAll(pause,saveState,score,ycor);
        hbox.setLayoutY(0);
        uiRoot.getChildren().add(hbox);


        Rectangle bg = new Rectangle(600, 900);
     
        timeStart = System.currentTimeMillis();

 

        
        colorCode.put("pink",4);
        colorCode.put("purple",1);
        colorCode.put("yellow",2);
        colorCode.put("blue",3);


        // Moving screen with ball (scrolling property)
        ball.ballImage.translateYProperty().addListener((observable, oldValue, newValue) -> {
            int offset=newValue.intValue();
            System.out.println(offset);
            if(offset<150 && offset%150<=3){
                System.out.println(offset);
                gameRoot.setLayoutY(150-offset);
            }
        });

        appRoot.getChildren().addAll(bg, gameRoot, uiRoot);


    }
    public void displayGameWindow() throws FileNotFoundException 
    {
        initContent();

        Scene scene = new Scene(appRoot);
        scene.setOnKeyPressed(key->{
                    KeyCode keyCode=key.getCode();
                    if(keyCode.equals(keyCode.CONTROL)){
                        ENTER_pressed=true;
                    }
                }
        );

        scene.setOnKeyReleased(key->
        {
                    KeyCode keyCode=key.getCode();
                    if(keyCode.equals(keyCode.CONTROL))
                    {
                        ENTER_pressed=false;
                    }
        }
        );
        stage.setTitle("Color Switch!");
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() 
        {
            @Override
            public void handle(long now) {
                // Making ball move according to key input
                try {
                    update();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        };
        this.timer=timer;
        pause.setOnAction(e->{paused =true;timer.stop();pauseWindow(timer);});
       // saveState.setOnAction(e->App.bStartMenu());
        saveState.setOnAction(e->{paused =true;timer.stop();obstacleHitWindow(timer);});
        timer.start();
    }

    private void update() throws IOException {

            // Changing position of ball
            double speed=0;
            if(ENTER_pressed==true){
                speed=-150;                // want 150 pixels per second move up

            }
            else if(ball.ballImage.getTranslateY()<600-25)
            {
                speed=75;
            }
            ball.ballImage.setTranslateY(ball.ballImage.getTranslateY()+speed/60.0);
            checkStarTouched();
            checkColorSwitcherTouched();
            //checkObstacleTouched();
    }
    
    
    
    void checkStarTouched()
    {
    	 if(ball.ballImage.getTranslateY()>=starPos && ball.ballImage.getTranslateY()<=starPos+50){
             gameRoot.getChildren().remove(star.get(star1).ivStar);
             star1++;
             ball.score++;
             String musicFile2 = "santa.mp3";     // For example
             Media sound1 = new Media(new File(musicFile2).toURI().toString());
             starsound = new MediaPlayer(sound1);
             starsound.play();   
             if(ball.score>highscoregame){
                 highscoregame= ball.score;
             }
             score.setText("Score: "+ball.score);
             starPos=100000;
             if(star1<star.size()) starPos=star.get(star1).starPos;

         }
    }
    void checkColorSwitcherTouched()
    {
    	 if(ball.ballImage.getTranslateY()>=colorSwitcherPos && ball.ballImage.getTranslateY()<=colorSwitcherPos+50)
    	 {
    		 gameRoot.getChildren().remove(colorSwitcher.get(colorSwitcherIndex).colorsw);
    		 ball.changeColor();
             colorSwitcherIndex++;
             colorSwitcherPos=100000;
             if(colorSwitcherIndex<colorSwitcher.size()) colorSwitcherPos=colorSwitcher.get(colorSwitcherIndex).getPositionY();
         }
    }
    
    void checkObstacleTouched() throws IOException {
    	
    	
    	
    	
    	long elapsedTime=System.currentTimeMillis()-timeStart;
        long r=elapsedTime/1000;    // number of quarter rotations completed
       
        //int presentBottomColor=1+(int)(r%4);
        int presentBottomColor=1+(((int)r)%4+1)%4;
        int presentTopColor=1+(presentBottomColor+1)%4;
        System.out.println("Present bottom col:"+presentBottomColor+"index:"+obstacleIndex);
        double ballTopPosition=ball.ballImage.getTranslateY()-ballRadius;
        double ballBottomPosition=ball.ballImage.getTranslateY()+ballRadius;

        
        
        	// if ball is in top part of obstacle
        if((ballBottomPosition>=obstacle.get(obstacleIndex).iv.getY() &&ballBottomPosition<=obstacle.get(obstacleIndex).iv.getY()+thickness) ||
                (ballTopPosition>=obstacle.get(obstacleIndex).iv.getY() &&ballTopPosition<=obstacle.get(obstacleIndex).iv.getY()+thickness))
        
        {
        	
          
        	if(presentTopColor!=ball.getColor())
        	{
        	
                System.out.println("obstacle touched");
                timer.stop();
                obstacleHitWindow(timer);
                
            }
            else{System.out.println("passing");}
            
            obstacleIndex++;
        	if(obstacleIndex==obstacle.size()) {obstacleIndex--; }
        }

        // if ball is in bottom part of obstacle

    if((ballBottomPosition>=obstacle.get(obstacleIndex).iv.getY()+2*or-thickness &&ballBottomPosition<=obstacle.get(obstacleIndex).iv.getY()+2*or) ||
            (ballTopPosition>=obstacle.get(obstacleIndex).iv.getY()+2*or-thickness &&ballTopPosition<=obstacle.get(obstacleIndex).iv.getY()+2*or))
    {
       
    	if(presentBottomColor!=ball.getColor())
        {
    		
            System.out.println("obstacle touched"+presentBottomColor+" "+"ball"+ball.getColor());
            timer.stop();
            obstacleHitWindow(timer);
        }
        else
        {
            System.out.println("passing");
        }
    }
        
     
    	
    }
    
    public void obstacleHitWindow(AnimationTimer timer)  {
    	Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Your Tree!");
        window.setMinWidth(250);
       
        Button q=new Button("Quit");
        Button resume=new Button("Resume");
        q.setOnAction(e->{this.timer.stop();mediaPlayer.stop(); App.bStartMenu(); window.close();});
        resume.setOnAction(e->{timer.start(); window.close();});
        VBox pauseLayout=new VBox();
        Image image=null;
        iv = new ImageView();
        Label l=new Label();
        if(star1==0)image=new Image("Images/tree_1.png");
        else if(star1==1)image=new Image("Images/tree2.png");
        else if(star1==2)image=new Image("Images/tree3.png");
        else if(star1==3)image=new Image("Images/tree4.png");
        else if(star1==4)image=new Image("Images/tree5.png");
        else if(star1==5)image=new Image("Images/tree6.png");
        else
        	{
        	
        	l=new Label("Merry Christmas!!!");
        	l.setStyle("-fx-font-size: 5em;-fx-text-fill: #ff0000");
        	image=new Image("Images/treefinal.jpg");
        	
        	}
        iv.setImage(image);
        iv.setFitHeight(400);iv.setFitWidth(400);
        pauseLayout.getChildren().addAll(q,resume,l,iv);
        window.setScene(new Scene(pauseLayout,600,600));
        window.show();
    	
    }
   

    public void pauseWindow(AnimationTimer gameloop)
    {
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Pause");
        window.setMinWidth(250);
        javafx.scene.control.Button b=new Button("Resume");
        b.setOnAction(e->{ gameloop.start(); window.close();});
        VBox pauseLayout=new VBox(); pauseLayout.getChildren().add(b);
        window.setScene(new Scene(pauseLayout,100,100));
        window.showAndWait();

    }
   
    

    public static void main(String[] args) {
        launch(args);
    }
}
