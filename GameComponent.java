import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
//import com.sun.javafx.jmx.MXNodeAlgorithm;
//import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public  class GameComponent  {


    public double positionY;
    private Image image;
    public GameComponent(double y){
        positionY=y;
    }
    public void setPosition(double y){
        positionY=y;
    }



    public void setImage(String filename) throws FileNotFoundException {
        image=new Image(filename);

    }

    public Image getImage(){
        return image;
    }
    public void render(GraphicsContext context,int x){
        context.drawImage(image,x,positionY);
    }

    public void addToPosition(double v){
        this.positionY+=v;
    }
    public double getPositionY(){
        return positionY;
    }


}
