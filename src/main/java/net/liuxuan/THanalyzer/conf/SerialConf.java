/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.THanalyzer.conf;

import java.io.Serializable;
import net.liuxuan.THanalyzer.serial.modbus.SerialModbus;
import net.liuxuan.THanalyzer.serial.SerialWrapper;
import net.liuxuan.THanalyzer.utils.ConfUtils;

/**
 *
 * @author Administrator
 */
public class SerialConf extends ConfClass {
    
    
    SerialWrapper serialPort;

    public SerialWrapper getSerialPort() {
        //if(serialPort==null){
            //reload();
        //}
        return serialPort;
    }

    public void setSerialPort(SerialWrapper serialPort) {
        this.serialPort = serialPort;
        //save();
        
    }


    
    public void reload() {
        this.load();
        serialPort = ((SerialConf)this.confObj).serialPort;
    }
        
    public static void main(String[] args) {
         SerialConf sc = new SerialConf();
         SerialModbus s = new SerialModbus();
         sc.setSerialPort(s);
         sc.save();
         s.setPortName("!!!!!!!!!!!");
         
         sc.load();
         SerialWrapper s1 =sc.getSerialPort();
         
         System.out.println(s1.getPortName());
         
         //System.out.println(sc.getSavepath());
                 
     }
}
