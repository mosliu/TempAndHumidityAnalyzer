/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.THanalyzer.serial.mina.T1;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.liuxuan.THanalyzer.serial.mina.base.message.AbstractPacket;
import net.liuxuan.utils.BytePlus;

/**
 *
 * @author Administrator
 */
public class T1InMessage extends AbstractPacket {

    public static final int PACKET_FULL_LEN = 24;

    public static final int PACKET_HEADER_LEN = 1;

    public static final int PACKET_TAIL_LEN = 0;

    public static final int PACKET_BODY_LEN = 23;
    public String a;

    public T1InMessage() {
        this(new byte[PACKET_BODY_LEN]);
    }

    public T1InMessage(byte[] _content) {
        super(_content);
        byte[] head = new byte[]{(byte) 0xFF};

        this.setHeader(head);
    }

    /**
     * TSY-T1H 通信协议
     *
     * 波特率：9600，8，n，1
     *
     * 方 向：单向 * 各帧发送顺序： 16分钟到了，发送一帧重量数据，同时附带温湿度，开始结束时间信息
     * 30分钟到了，发送一帧过程透过率数据，同时附带温湿度，开始结束时间信息 温湿度的发送大约为1次/秒 。
     *
     * 帧格式： X1 X2 X3 X4 X5 X6 X7 X8 X9 X10 X11 X12 X13 X14 X15 X16 X17 X18 X19
     * X20 X21 X22 X23 X24
     *
     * X1=FFH X2=00 X3=16H
     *
     * X4=0 温湿度，重量
     *
     * 重量 X5,X6,X7 十六进制低位在前高位在后
     *
     * 温度 X8,X9 十进制低位在前高位在后
     *
     * 湿度 X10,X11 十进制低位在前高位在后
     *
     * 开始时间 X12,X13,X14,X15,X16,X17
     *
     * 结束时间 X18 X19 X20 X21 X22,X23
     *
     * 校验和：X24=X2+X3+ ………X22+X23
     *
     * X4=1 结束 透湿量
     *
     * 透湿量X5,X6,X7 十进制低位在前高位在后
     *
     * 温度 X8,X9 十进制低位在前高位在后
     *
     * 湿度 X10,X11 十进制低位在前高位在后
     *
     * 开始时间 X12,X13,X14,X15,X16,X17
     *
     * 结束时间 X18 X19 X20 X21 X22,X23
     *
     * 校验和：X24=X2+X3+ ………X22+X23
     *
     * X4=2 30分钟 透湿量（每隔30分钟发一次）
     *
     * 透湿量X5,X6,X7 十进制低位在前高位在后
     *
     * 温度 X8,X9 十进制低位在前高位在后
     *
     * 湿度 X10,X11 十进制低位在前高位在后
     *
     * 开始时间 X12,X13,X14,X15,X16,X17
     *
     * 结束时间 X18 X19 X20 X21 X22,X23
     *
     * 校验和：X24=X2+X3+ ………X22+X23
     *
     *
     * X4=3 16分钟 重量（每隔16分钟发一次）
     *
     * 重量 X5,X6,X7 十六进制低位在前高位在后
     *
     * 温度 X8,X9 十进制低位在前高位在后
     *
     * 湿度 X10,X11 十进制低位在前高位在后
     *
     * 开始时间 X12,X13,X14,X15,X16,X17
     *
     * 结束时间 X18 X19 X20 X21 X22,X23
     *
     * 校验和：X24=X2+X3+ ………X22+X23
     *
     *
     *
     *
     */
    /**
     * 默认构造函数
     */
//    public T1InMessage() {
//        this.checkMethod = CONST_CHECK_SUM_NONE;
//    }
    /**
     * 重置为默认包体内容
     *
     * @param _content 重置时的content内容。如传入null则初始化为new
     * byte[CommonParameters.PACKET_BODY_LEN];
     */
    @Override
    protected void defaultPacket(byte[] _content) {
        this.length = PACKET_FULL_LEN;
        if (_content == null) {
            this.content = new byte[PACKET_BODY_LEN];
        } else {
            this.content = _content;
        }
    }
//
//    public static void main(String[] args) {
//        try {
//            T1InMessage a = new T1InMessage(new byte[]{3, 4});
////            a.setContent(new byte[]{3, 4});
//            a.a="asd";
//            T1InMessage b= (T1InMessage) a.clone();
//            b.a="dsa";
//            b.setContent(new byte[]{4, 4});
//            
//            System.out.println(a.getContent()[0]);
//            System.out.println(b.getContent()[0]);
//            System.out.println(a.a);
//            System.out.println(b.a);
//        } catch (CloneNotSupportedException ex) {
////            Logger.getLogger(T1InMessage.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
}
