import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ResourceManager {

    public static void save(Serializable data, String fileName) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            oos.writeObject(data);

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static Object load(String fileName) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) 
        {
            //ArrayList<Double> list = (ArrayList<Double>) ois.readObject();
            Object data=ois.readObject();
            System.out.println(data);
            return data;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

}
