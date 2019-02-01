/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.THanalyzer.serial;

import java.io.Serializable;

/**
 *
 * @author Moses
 */
public interface SerialWrapper  extends Serializable{

    /**
     * 
     * @return 
     */
    String getPortName();
    void setPortName(String num);
    
    /**
     * 
     * @return 
     */
    int getPortNameNum();
    void setPortNameNum(int num);
    /**
     * @return
     */
    int getBaudRate();

    void setBaudRate(int baudrate);
    
 
    /**
     * @return
     */
    int getDataBits();

    void setDataBits(int databits);
    /**
     * @return
     */
    int getStopBits();
    void setStopBits(int stopbits);
    /**
     * @return
     */
    int getParity();
    void setParity(int parity);
}
