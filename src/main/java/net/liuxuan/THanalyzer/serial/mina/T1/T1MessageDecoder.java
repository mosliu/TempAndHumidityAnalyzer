package net.liuxuan.THanalyzer.serial.mina.T1;

import net.liuxuan.THanalyzer.serial.mina.base.codec.AbstractPacketDecoder;
import net.liuxuan.THanalyzer.serial.mina.base.message.AbstractPacket;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

/**
 * OxygenTransducer's MessageDecoder
 *
 * @version 1.0.0.0, Jul 29, 2011
 * @author Moses
 */
public class T1MessageDecoder extends AbstractPacketDecoder {

    public T1MessageDecoder() {
        this(new T1InMessage());
    }

    
    public T1MessageDecoder(T1InMessage stat) {
        super(stat);
    }

   
}
