/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.THanalyzer.serial.mina.T1;

import net.liuxuan.utils.BytePlus;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * T1 温湿度信号处理
 *
 * @version 2015.09.15
 * @author Moses
 */
public class T1Hanler extends IoHandlerAdapter {

    ReceiveMsgCallback _callback;

    public T1Hanler(ReceiveMsgCallback callBack) {
        _callback = callBack;
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        T1InMessage msg = (T1InMessage) message;
//        byte[] incontent = msg.getContent();
        byte[] incontent = msg.getFullContent();
//        if (msg.CheckState()) {
//            System.out.println("包合法");
//        } else {
//            System.out.println("包不合法");
//
//        }
//        System.out.println("X1 X2 X3 X4 X5 X6 X7 X8 X9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24");
//        System.out.println(BytePlus.byteArray2String(incontent));

        //无论X4等于多少，温湿度位置不变
        byte[] temp = new byte[2];
        temp[0] = incontent[8];
        temp[1] = incontent[7];
        int iTemp = BytePlus.bcd2Int(temp) * 10;
        temp[0] = incontent[10];
        temp[1] = incontent[9];
        int iHum = BytePlus.bcd2Int(temp) * 10;
//        System.out.println(BytePlus.bcd2Str(temp));
//        System.out.println(BytePlus.bcd2Int(temp));
        short[] list = new short[2];
        list[0] = (short) iTemp;
        list[1] = (short) iHum;
        _callback.onReceiveMsgEnd(list);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("session closed");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("session idled:" + status.toString());
    }

}
