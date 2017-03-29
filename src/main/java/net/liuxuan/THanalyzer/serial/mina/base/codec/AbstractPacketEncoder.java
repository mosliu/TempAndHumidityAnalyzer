package net.liuxuan.THanalyzer.serial.mina.base.codec;

import net.liuxuan.THanalyzer.serial.mina.base.message.AbstractPacket;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

/**
 * A {@link MessageEncoder} that encodes message header and forwards the
 * encoding of body to a subclass.
 *
 * @author The Apache MINA Project (dev@mina.apache.org)
 */
public abstract class AbstractPacketEncoder<T extends AbstractPacket>
        implements MessageEncoder<T> {

    /**
     * 使用默认初始化参数
     */
    public AbstractPacketEncoder() {

    }

    public void encode(IoSession session, T message, ProtocolEncoderOutput out)
            throws Exception {
        int l = ((AbstractPacket) message).getLength() != 0 ? ((AbstractPacket) message)
                .getLength()
                : AbstractPacket.CONST_DEFAULT_LENGTH;

        IoBuffer buf = IoBuffer.allocate(l);
        buf.setAutoExpand(true); // Enable auto-expand for easier encoding

        // Encode a header
        // buf.putShort(header);
        // 测试用语句
        if (message.isHasHeader()) {
            buf.put(message.getHeader());
        }

        // Encode a bodyc
        encodeBody(session, message, buf);

        if (message.isHasTail()) {
            buf.put(message.getTail());
        }

        buf.flip();
        out.write(buf);
    }

    protected void encodeBody(IoSession session, T message,
            IoBuffer out) {
        out.put(message.getContent());
    }
;
}
