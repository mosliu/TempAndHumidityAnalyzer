/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.THanalyzer.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.liuxuan.THanalyzer.conf.IConf;
import net.liuxuan.THanalyzer.serial.SerialWrapper;

/**
 *
 * @author Administrator
 */
public class ConfUtils {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ConfUtils.class);

    private ConfUtils() {

    }

    
    public static void writeObjectToFile(Object obj,File file) {
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
            objOut.close();
            logger.debug("write object success!"+obj.getClass().getName());            
        } catch (IOException e) {
            logger.error("============write object failed:"+obj.toString());
            e.printStackTrace();
            
//            logger.error(e.getMessage());
//            
//            System.out.println("write object failed");
//            e.printStackTrace();
        }
    }

    public static Object readObjectFromFile(File file) {
        Object temp = null;
//        File file = new File("test.dat");
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(in);
            temp = objIn.readObject();
            objIn.close();
            logger.debug("read object success!"+file.getAbsolutePath());
            
//            System.out.println("read object success!");
        } catch (IOException e) {
            logger.error("============read object failed:"+file.getAbsolutePath());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }
    

}
