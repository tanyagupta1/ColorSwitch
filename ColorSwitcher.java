import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ColorSwitcher extends GameComponent 
{
	ImageView colorsw;
	private double or=90.5;
	public ColorSwitcher(double y)
	{
		super(y);
		colorsw=new ImageView();
        Image cs=new Image("Images/colorswitcher.png");
        colorsw.setImage(cs);
        colorsw.setX(300-7.5);
        colorsw.setFitHeight(30);
        //colorsw.setY(300-2*or-25);
        colorsw.setY(y);
        colorsw.setFitWidth(30);
      
	}

}
