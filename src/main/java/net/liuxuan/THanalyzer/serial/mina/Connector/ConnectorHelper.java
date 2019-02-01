/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.THanalyzer.serial.mina.Connector;

import net.liuxuan.THanalyzer.conf.Parameters;
import net.liuxuan.THanalyzer.serial.SerialWrapper;
import org.apache.mina.transport.serial.SerialAddress;

/**
 *
 * @author Administrator
 */
public class ConnectorHelper {

    /**
     * 
     * @param para
     * @return
     */
    public static SerialAddress convertSerialWrapper2SerialAddress(SerialWrapper port) {
        SerialAddress portAddress = new SerialAddress(
                port.getPortName(), //"COM1"
                port.getBaudRate(),//9600
                getDatabits(port),//SerialAddress.DataBits.DATABITS_8, 
                getStopBits(port), //SerialAddress.StopBits.BITS_1, 
                getParity(port),//SerialAddress.Parity.NONE,
                SerialAddress.FlowControl.NONE);

        return portAddress;
    }

    private static SerialAddress.DataBits getDatabits(SerialWrapper port) {
        if (port.getDataBits() == 8) {
            return SerialAddress.DataBits.DATABITS_8;
        } else if (port.getDataBits() == 7) {
            return SerialAddress.DataBits.DATABITS_7;
        } else if (port.getDataBits() == 6) {
            return SerialAddress.DataBits.DATABITS_6;
        } else if (port.getDataBits() == 5) {
            return SerialAddress.DataBits.DATABITS_5;
        } else {
            return SerialAddress.DataBits.DATABITS_8;
        }
    }

    private static SerialAddress.StopBits getStopBits(SerialWrapper port) {
        if (port.getStopBits() == 1) {
            return SerialAddress.StopBits.BITS_1;
        } else if (port.getStopBits() == 2) {
            return SerialAddress.StopBits.BITS_2;
        } else {
            return SerialAddress.StopBits.BITS_1_5;
        }
    }

    private static SerialAddress.Parity getParity(SerialWrapper port) {
        if (port.getParity() == 0) {
            return SerialAddress.Parity.NONE;
        } else if (port.getParity() == 1) {
            return SerialAddress.Parity.ODD;
        } else if (port.getParity() == 2) {
            return SerialAddress.Parity.EVEN;
        } else {
            return SerialAddress.Parity.SPACE;
        }
    }
}
