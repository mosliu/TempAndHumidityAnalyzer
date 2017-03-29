package net.liuxuan.THanalyzer.serial.mina.base.codec;

import java.util.Arrays;
import net.liuxuan.THanalyzer.serial.mina.base.message.AbstractPacket;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

public abstract class AbstractPacketDecoder implements MessageDecoder {

    protected boolean readHeader = false;
    AbstractPacket stat;

    /**
     * 禁止无参数初始化
     */
    private AbstractPacketDecoder() {

    }

    /**
     * 初始化一个decoder，
     *
     * @param header 包头
     * @param tail 包尾
     */
    public AbstractPacketDecoder(AbstractPacket stat) {
        this.stat = stat;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.mina.filter.codec.demux.MessageDecoder#decodable(org.apache
     * .mina.core.session.IoSession, org.apache.mina.core.buffer.IoBuffer)
     */
    public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
        // Return NEED_DATA if the whole header is not read yet.
        // if (in.remaining() < CommonParameters.PACKET_HEADER_LEN) {
        // return MessageDecoderResult.NEED_DATA;
        // }

        if (in.remaining() < stat.getFullLength()) {
            return MessageDecoderResult.NEED_DATA;
        }

        if (stat.isHasHeader()) {
            //检查头
            byte[] rightHeader = stat.getHeader();
            byte[] checkHeader = new byte[rightHeader.length];
            in.get(checkHeader);
            boolean headerSame = Arrays.equals(rightHeader, checkHeader);
            if (headerSame == false) {
                // 设计：将多余的字节数从buffer中移除
                in.clear();
                return MessageDecoderResult.NEED_DATA;
            }
        }

        byte[] content = new byte[stat.getLength()];

        if (stat.isHasTail()) {
            byte[] rightTail = stat.getTail();
            byte[] checkTail = new byte[rightTail.length];
            in.get(checkTail);
            boolean tailSame = Arrays.equals(rightTail, checkTail);
            if (tailSame) {
                return MessageDecoderResult.OK;
            }

        } else {
            return MessageDecoderResult.OK;
        }

        // Return NOT_OK if not matches.
        return MessageDecoderResult.NOT_OK;
    }

    public MessageDecoderResult decode(IoSession session, IoBuffer in,
            ProtocolDecoderOutput out) throws Exception {
        //能到这个方法的，长度已经合适了，不做过多检查

        AbstractPacket rtnPKT = (AbstractPacket) stat.clone();

        if (rtnPKT.isHasHeader()) {
            //检查头
            byte[] header = rtnPKT.getHeader();
            in.get(header);
            rtnPKT.setHeader(header);
        }
        byte[] content = new byte[rtnPKT.getLength()];
        in.get(content);
        rtnPKT.setContentWithoutReset(content);

        if (rtnPKT.isHasTail()) {
            byte[] tail = rtnPKT.getTail();
            in.get(tail);
            rtnPKT.setTail(tail);
        }
        out.write(rtnPKT);
        return MessageDecoderResult.OK;
    }
     public void finishDecode(IoSession session, ProtocolDecoderOutput out)
            throws Exception {
    }
}
