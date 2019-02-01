/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.THanalyzer.serial.modbus;

import net.liuxuan.THanalyzer.serial.SerialWrapper;

/**
 *
 * @author Administrator
 */
public class SerialModbus implements SerialWrapper{

    String portName;
     int portNameNum;
    int baudRate;
    int stopBits;
    int dataBits;
    int parity;


    public SerialModbus() {
        portName = "COM1";
        portNameNum=1;
        baudRate =9600;
        stopBits=1;
        dataBits =8;
        parity =0;
        
    }

   
    
    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public int getPortNameNum() {
        return portNameNum;
    }

    public void setPortNameNum(int portNameNum) {
        this.portNameNum = portNameNum;
    }

    public int getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public int getStopBits() {
        return stopBits;
    }

    public void setStopBits(int stopBit) {
        this.stopBits = stopBit;
    }

    public int getDataBits() {
        return dataBits;
    }

    public void setDataBits(int dataBits) {
        this.dataBits = dataBits;
    }

    public int getParity() {
        return parity;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }

   
    
    
    
}
