package net.liuxuan.THanalyzer.serial.mina.T1;

//~--- non-JDK imports --------------------------------------------------------


import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

/**
 * OxygenTransducer's ProtocolCodecFactory
 *
 * @version        1.0.0.0, Jul 29, 2011
 * @author         Moses
 */
public class T1ProtocolCodecFactory extends DemuxingProtocolCodecFactory {

    /**
     * Constructs ...
     *
     */
    public T1ProtocolCodecFactory() {
        
        this.addMessageDecoder(T1ErrorMessageDecoder.class);
        this.addMessageDecoder(T1MessageDecoder.class);
        
//        this.addMessageEncoder(T1OutMessage.class, T1MessageEncoder.class);
    }
}
