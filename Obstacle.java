import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Obstacle  extends GameComponent
{
	ImageView iv,iv2;
	
	private double xball=300;
	private double or=90.5;
    public Obstacle(double y,String imgSource ) //for ring
    
    {
    	super(y);
    	iv = new ImageView();
        try
        {
        	setImage(imgSource);
        }
        catch(Exception e) {System.out.println("obstacle image not set");}
        iv.setImage(getImage());
        iv.setX(xball-or);
        iv.setY(y);
        
        RotateTransition rt = new RotateTransition(Duration.millis(4000), iv);  // in 4s 1 rotation done
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
        
        
    }
public Obstacle(double y,String imgSource, int x ) //for windmill
   
    {
    	super(y);
    	iv = new ImageView();
        try
        {
        	setImage(imgSource);
        }
        catch(Exception e) {System.out.println("obstacle image not set");}
        iv.setImage(getImage());
        iv.setX(xball-or-85);
        iv.setY(y);
        
        RotateTransition rt = new RotateTransition(Duration.millis(4000), iv);  // in 4s 1 rotation done
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
        
    }
    public Obstacle(double y,String imgSource,String reverse ) ///for 2nd of double ring

    {

    	super(y);
    	iv = new ImageView();
        try
        {
        	setImage(imgSource);
        }
        catch(Exception e) {System.out.println("obstacle image not set");}
        iv.setImage(getImage());
        iv.setX(xball-or);
        iv.setY(y);
        
        RotateTransition rt = new RotateTransition(Duration.millis(4000), iv);  // in 4s 1 rotation done
        rt.setByAngle(-360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }
}

