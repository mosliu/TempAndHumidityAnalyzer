/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.THanalyzer.serial.modbus;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class ModbusInfo implements Serializable {
    String OwnerName = "MosesProducts";
    int timeout =500;
    //设定MODBUS网络上从站地址
    int slaveAddress =1;
    


    
    
    public int getSlaveAddress() {
        return slaveAddress;
    }

    public void setSlaveAddress(int slaveAddress) {
        this.slaveAddress = slaveAddress;
    }

    
    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String OwnerName) {
        this.OwnerName = OwnerName;
    }
    
    
}
