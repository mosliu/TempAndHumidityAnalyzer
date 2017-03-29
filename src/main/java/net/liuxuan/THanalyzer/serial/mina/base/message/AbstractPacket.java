package net.liuxuan.THanalyzer.serial.mina.base.message;

import java.io.Serializable;
import net.liuxuan.THanalyzer.serial.mina.base.codec.IDecoderStat;

import net.liuxuan.utils.BytePlus;

/**
 * A base Packet for protocol messages.
 *
 * @author Moses
 */
public abstract class AbstractPacket implements Serializable, IDecoderStat, Cloneable {

    public static final int CONST_CHECK_SUM_NONE = -1;
    public static final int CONST_CHECK_SUM_SINGLE_BYTE = 0;
    public static final int CONST_CHECK_CRC = 1;

    //默认conten length
    public static final int CONST_DEFAULT_LENGTH = 10;

    /**
     * 头
     */
    public byte[] header = null;

    /**
     * 尾
     */
    public byte[] tail = null;

    /**
     * 内容长度，除去头尾的长度
     */
    public int length = 0;

    /**
     * 内容
     */
    public byte[] content;

    /**
     *
     */
    public int checkMethod = CONST_CHECK_SUM_SINGLE_BYTE;

    /**
     * 默认构造函数,禁止使用
     */
    private AbstractPacket() {
//        reset();
    }

    /**
     * 构造函数，设定内容content
     *
     * @param _content
     */
    public AbstractPacket(byte[] _content) {
        defaultPacket(_content);
    }

    /**
     * 获取头，返回值可能为null
     *
     * @return
     */
    public byte[] getHeader() {
        return header;
    }

    /**
     * 设置头
     *
     * @param header
     */
    public void setHeader(byte[] header) {
        this.header = header;
    }

    /**
     * 获取尾，返回值可能为null
     *
     * @return
     */
    public byte[] getTail() {
        return tail;
    }

    /**
     * 设置尾
     *
     * @param tail
     */
    public void setTail(byte[] tail) {
        this.tail = tail;
    }

    /**
     * 获取内容长度
     *
     * @return
     */
    public int getLength() {
        if (content != null) {
            length = content.length;
        }
        return length;
    }

//    /**
//     * 设置内容长度---无意义 TODO！！
//     *
//     * @param length
//     */
//    public void setLength(int length) {
//        this.length = length;
//    }

    public byte[] getFullContent() {
        byte[] rtn = new byte[getFullLength()];
        if (isHasHeader()) {
            System.arraycopy(header, 0, rtn, 0, header.length);
        }
        System.arraycopy(getContent(), 0, rtn, header.length, getContent().length);

        if (isHasTail()) {
            System.arraycopy(tail, 0, rtn, getHeader().length+getContent().length, tail.length);
        }
        return rtn;
    }

    /**
     * 获取内容，可能为null
     *
     * @return
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * 设定内容，会重设校验值
     *
     * @param content
     */
    public void setContent(byte[] content) {
        this.content = content;
        resetState();
    }

    /**
     * 设定内容，会重设校验值
     *
     * @param content
     */
    public void setContentWithoutReset(byte[] content) {
        this.content = content;
    }

    /**
     * 获取检验方法
     *
     * @return
     */
    public int getCheckMethod() {
        return checkMethod;
    }

    /**
     * 设定校验方法
     *
     * @param checkmethod
     */
    public void setCheckMethod(int checkMethod) {
        this.checkMethod = checkMethod;
    }

    /**
     * 重设校验值
     */
    public void resetState() {
        if (checkMethod == CONST_CHECK_SUM_SINGLE_BYTE) {
            content[content.length - 1] = caculateChecksum_SingleValue();
        } else {
            //TODO 等待添加其他校验方式
        }
    }

    /**
     * 计算校验值
     *
     * @return
     */
    public byte caculateChecksum_SingleValue() {
        int count = 0;
        for (int i = 0; i < content.length - 1; i++) {
            count += content[i];
        }
        count = count & 0xff;
        return (byte) count;
    }

    /**
     * 检查校验值是否正确 未查出错误或指定了未实现的检查方法均会返回true
     *
     * @return
     */
    public boolean CheckState() {
        if (checkMethod == CONST_CHECK_SUM_SINGLE_BYTE) {
            byte old = content[content.length - 1];
            byte now = caculateChecksum_SingleValue();
            if (old == now) {
                return true;
            } else {
                return false;
            }
        } else {
            //TODO 等待添加其他校验方式
        }
        return true;

    }

    /**
     * 重置包，用空数据
     */
//    public void reset() {
//        defaultPacket(null);
//    }
    /**
     * 重置为默认包体内容
     *
     * @param _content 重置时的content内容。如传入null则初始化为new byte[PACKET_BODY_LEN];
     */
    protected void defaultPacket(byte[] _content) {
//        this.length = PACKET_FULL_LEN;
        if (_content == null) {
            this.content = new byte[CONST_DEFAULT_LENGTH];
        } else {
            this.content = _content;
        }
    }

    ;

    @Override
    public String toString() {
        return "包体:(" + BytePlus.byteArray2String(content) + ')';
    }

    @Override
    public int getFullLength() {
        int rtn = 0;
        if (header != null) {
            rtn += header.length;
        }
        if (tail != null) {
            rtn += tail.length;
        }
        rtn += getLength();
        return rtn;
    }

    @Override
    public boolean isHasHeader() {
        if (header == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isHasTail() {
        if (tail == null) {
            return false;
        }
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

}
