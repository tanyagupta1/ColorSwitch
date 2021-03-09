import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player extends GameComponent {
    //private double speed;
    int score=0;
    int xball=300;
    private double ballRadius=15;
    Circle ballImage;
    int currentColor=2;
    
    void changeColor()
    {
    	if(currentColor%4==1) ballImage.setFill(Color.YELLOW);
    	else if(currentColor%4==3) ballImage.setFill(Color.rgb(255,0,129)); //pink
    	else if(currentColor%4==0) ballImage.setFill(Color.rgb(141,19,252)); //violet
    	else if(currentColor%4==2) ballImage.setFill(Color.rgb(93,188,210));  //blue
    	currentColor++;
    }
    void setColor()
    {
    	if(currentColor%4==2) ballImage.setFill(Color.YELLOW);
    	else if(currentColor%4==0) ballImage.setFill(Color.rgb(255,0,129)); //pink
    	else if(currentColor%4==1) ballImage.setFill(Color.rgb(141,19,252)); //violet
    	else if(currentColor%4==3) ballImage.setFill(Color.rgb(93,188,210));  //blue
    	
    }
    int getColor() { return 1+(currentColor)%4;}


    public Player(double y)
    {
        super(y);
    
    	ballImage=new Circle();
        ballImage.setRadius(ballRadius);
        ballImage.setTranslateY(y);
        ballImage.setTranslateX(xball);
        ballImage.setFill(Color.YELLOW);
        
    }


}
