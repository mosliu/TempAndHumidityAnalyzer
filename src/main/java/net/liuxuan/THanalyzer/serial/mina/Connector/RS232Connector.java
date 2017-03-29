package net.liuxuan.THanalyzer.serial.mina.Connector;


import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.serial.SerialAddress;
import org.apache.mina.transport.serial.SerialConnector;
import org.apache.mina.transport.serial.SerialAddress.DataBits;
import org.apache.mina.transport.serial.SerialAddress.FlowControl;
import org.apache.mina.transport.serial.SerialAddress.Parity;
import org.apache.mina.transport.serial.SerialAddress.StopBits;


/**
 * @author Moses
 * @version 2015.09.15
 *
 */
public class RS232Connector {
    // 使用单例模式创建

    private static RS232Connector rs232connector = null;
    IoConnector connector = null;
    IoHandlerAdapter receiverHandler;
    /**
     * 默认：COM1,9600,8,NONE,1
     */
    SerialAddress portAddress = new SerialAddress("COM1", 9600,
            DataBits.DATABITS_8, StopBits.BITS_1, Parity.NONE, FlowControl.NONE);
    ConnectFuture cf = null;
    IoSession receiverSession = null;

    /**
     * 单例模式获取RS232Connector
     * 
     * @param receiverHandler
     * @return
     */
    public static synchronized RS232Connector getInstance(
            IoHandlerAdapter receiverHandler) {
        if (rs232connector == null) {
            rs232connector = new RS232Connector(receiverHandler,null);
        }
        return rs232connector;
    }

    /**
     * 单例模式获取RS232Connector
     * 
     * @param receiverHandler
     * @param portAddress
     * @return
     */
    public static synchronized RS232Connector getInstance(
            IoHandlerAdapter receiverHandler, SerialAddress portAddress) {
        if (rs232connector == null) {
            rs232connector = new RS232Connector(receiverHandler, portAddress);
        }
        return rs232connector;
    }

    public static synchronized RS232Connector newInstance(
            IoHandlerAdapter receiverHandler, SerialAddress portAddress) {
        if (rs232connector != null) {
            rs232connector.endListen();
        }
        rs232connector = new RS232Connector(receiverHandler, portAddress);
        return rs232connector;
    }

    private RS232Connector() {
    }

//    private RS232Connector(IoHandlerAdapter receiverHandler) {
//        this(receiverHandler, null);
//    }

    private RS232Connector(IoHandlerAdapter receiverHandler,
            SerialAddress portAddress) {
        this.connector = new SerialConnector();
        this.receiverHandler = receiverHandler;
        if (portAddress != null) {
            this.portAddress = portAddress;
        }
        this.connector.setHandler(receiverHandler);
    }

    /**
     * 添加过滤器
     * 一般需要添加
     * @param name
     * @param filter
     */
    public void addFilter(String name, IoFilter filter) {
        this.connector.getFilterChain().addLast(name, filter);
    }

    public void removeFilter(String name) {
        if (this.connector.getFilterChain().contains(name)) {
            this.connector.getFilterChain().remove(name);
        }
    }

    public ConnectFuture startListen() {
        cf = connector.connect(portAddress);
        
        try {
            cf.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        receiverSession = cf.getSession();
        return cf;
    }
    
    public ConnectFuture resetcomm(SerialAddress portAddress) {
        if (portAddress != null) {
            this.portAddress = portAddress;
        }
        receiverSession.close(true);
        cf = connector.connect(portAddress);
        
        try {
            cf.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        receiverSession = cf.getSession();
        return cf;
    }

    public void endListen() {
        if (receiverSession == null) {
//            return;
        } else {
            receiverSession.close(true);
            CloseFuture clf = receiverSession.getCloseFuture();
            clf.awaitUninterruptibly();
        }
        //等待连接断开
        connector.dispose();
        rs232connector = null;
        // return clf;
    }

//    public static void main(String[] args) {
//        SerialAddress portAddress = new SerialAddress("COM3", 9600,
//                DataBits.DATABITS_8, StopBits.BITS_1, Parity.NONE,
//                FlowControl.NONE);
//        IoHandlerAdapter handler = new Httl1handler();
//        //为测试方便，采用的构造函数构造，正式使用需要使用单例模式调用。
//        RS232Connector receiver = new RS232Connector(handler, portAddress);
//        receiver.addFilter("logger", new LoggingFilter());
//        receiver.addFilter("codec", new ProtocolCodecFilter(
//                new HttL1ProtocolCodecFactory())); // 设置编码过滤器
////	receiver.addFilter("codec", new ProtocolCodecFilter(
////		new TextLineCodecFactory(Charset.forName("UTF-8")))); // 设置编码过滤器
//        receiver.startListen();
//
//        receiver.getReceiverSession().getCloseFuture().awaitUninterruptibly();// 等待连接断开
//    }

    public IoConnector getConnector() {
        return connector;
    }

    public void setConnector(IoConnector connector) {
        this.connector = connector;
    }

    public IoHandlerAdapter getReceiverHandler() {
        return receiverHandler;
    }

    public void setReceiverHandler(IoHandlerAdapter receiverHandler) {
        this.receiverHandler = receiverHandler;
    }

    public SerialAddress getPortAddress() {
        return portAddress;
    }

    public void setPortAddress(SerialAddress portAddress) {
        this.portAddress = portAddress;
    }

    public ConnectFuture getCf() {
        return cf;
    }

    public void setCf(ConnectFuture cf) {
        this.cf = cf;
    }

    public IoSession getReceiverSession() {
        return receiverSession;
    }

    public void setReceiverSession(IoSession receiverSession) {
        this.receiverSession = receiverSession;
    }
}
