package net.liuxuan.THanalyzer.serial.mina.base;

public class CommonParameters1 {
    public static final short PACKET_HEADER = (short) 0xAAAA;
    
    public static final short PACKET_TAIL = (short) 0xBBBB;

    public static final int PACKET_FULL_LEN = 18;
    
    public static final int PACKET_HEADER_LEN = 2;
    
    public static final int PACKET_TAIL_LEN = 2;

    public static final int PACKET_BODY_LEN = 14;

    public static final int BYTE_LENGTH = 1;

    public static final int SHORT_LENGTH = 2;

    public static final int INTEGER_LENGTH = 4;

    public static final int LONG_LENGTH = 8;

    public static final String NEWLINE=System.getProperty("line.separator");
    /**
     * not used put below
     */
}
