package net.liuxuan.THanalyzer.serial.mina.T1;


import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

/**
 * T1's ErrorMessageDecoder
 * 当有错误帧时，抛弃错误帧。
 *
 * @version        1.0.0.0, Jul 29, 2011
 * @author         Moses
 */
public class T1ErrorMessageDecoder implements MessageDecoder {

    boolean readHeader = false;

    public MessageDecoderResult decodable(IoSession session, IoBuffer in) {

        if (in.remaining() > T1InMessage.PACKET_FULL_LEN) {
            return MessageDecoderResult.OK;
        } else {
            return MessageDecoderResult.NEED_DATA;
        }
    }

    public MessageDecoderResult decode(IoSession session, IoBuffer in,
            ProtocolDecoderOutput out) throws Exception {
        // Try to skip header if not read.
        return MessageDecoderResult.OK;
//        if (!readHeader) {
//            in.getShort(); // 跳过 'header'.
//            readHeader = true;
//        }
//        if (in.get() == 0xBB) {
//            if (readHeader == false) {
//                readHeader = true;
//                return MessageDecoderResult.NEED_DATA;
//            } else {
//                return MessageDecoderResult.OK;
//            }
//        } else {
//            return MessageDecoderResult.NEED_DATA;
//        }
    }

    public void finishDecode(IoSession session, ProtocolDecoderOutput out)
            throws Exception {
    }
}
