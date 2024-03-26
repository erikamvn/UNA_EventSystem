package com.event.fileSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManipulator {
	
	public static void save(Object object, String path) {
		try {
            FileOutputStream saveFile = new FileOutputStream(path);
            ObjectOutputStream stream = new ObjectOutputStream(saveFile);

            stream.writeObject(object);
            stream.close();
          } catch (Exception exc) {
            exc.printStackTrace();
          }
	}
	
	  public static Object read(String caminho) {
          Object object = null;
         
          try {
                 FileInputStream restFile = new FileInputStream(caminho);
                 ObjectInputStream stream = new ObjectInputStream(restFile);

                 object = stream.readObject();
                 stream.close();
          } catch (Exception e) {
                 e.printStackTrace();
          }
          return object;
   }

}
