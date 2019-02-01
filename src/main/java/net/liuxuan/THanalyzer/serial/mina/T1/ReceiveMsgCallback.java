/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.THanalyzer.serial.mina.T1;

/**
 *
 * @author Moses
 */
public interface ReceiveMsgCallback {
    public void onReceiveMsgEnd(short[] list);  
}
