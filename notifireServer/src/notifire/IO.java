package notifire;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.*;
import java.util.*;

public class IO {

    private File file;
    private final String dir = "savedData/";
    private final String ext = ".ser";

    public IO() throws IOException {
        
        new File(dir).mkdirs();
        this.file = new File(dir);
        System.out.println("file set");
    }

    public boolean exist(String fName) throws FileNotFoundException, IOException {
        String dest = dir + fName + ext;
        return new File(dest).exists();
    }

    public void save(String fName, Object obj) throws FileNotFoundException, IOException {
        String dest = dir + fName +ext;
        File tmp = new File(dest);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dest));
        oos.writeObject(obj);
        System.out.println("Saved");
    }

    public Object load(String fName) throws FileNotFoundException, IOException, ClassNotFoundException {
        String dest = dir + fName +ext;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dest));
            System.out.println("Loaded");
            return ois.readObject();

        } catch (IOException e) {
            System.out.println("File not found");
            return null;
        }
    }

}
