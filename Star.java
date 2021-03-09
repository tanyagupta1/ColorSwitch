import javafx.scene.image.ImageView;

public class Star extends GameComponent 
{
	ImageView ivStar;
	;
	 double starPos;
	
	public Star(double y)
	{
        super(y);
        try
        {
        setImage("Images/star.jpg");
        } catch(Exception e) {System.out.println("star image not set");}
        starPos=y-50;
        ivStar = new ImageView();
        ivStar.setImage(getImage());
        ivStar.setX(275);
        ivStar.setFitHeight(50);
        ivStar.setY(starPos);
        ivStar.setFitWidth(50);
       
    }
	public Star(double y,String image)
	{
        super(y);
        try
        {
        //setImage("Images/star.jpg");
        	setImage(image);	
        } catch(Exception e) {System.out.println("star image not set");}
        starPos=y-50;
        ivStar = new ImageView();
        ivStar.setImage(getImage());
        ivStar.setX(275);
        ivStar.setFitHeight(50);
        ivStar.setY(starPos);
        ivStar.setFitWidth(50);
       
    }
	

}
