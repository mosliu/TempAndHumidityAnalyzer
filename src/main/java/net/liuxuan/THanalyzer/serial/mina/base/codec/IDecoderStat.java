/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.THanalyzer.serial.mina.base.codec;

/**
 *
 * @author Administrator
 */
public interface IDecoderStat {

    /**
     * 获取总长
     *
     * @return
     */
    public int getFullLength();

    /**
     *
     * @return
     */
    public byte[] getHeader();

    /**
     *
     * @param header
     */
    public void setHeader(byte[] header);

    /**
     *
     * @return
     */
    public byte[] getTail();

    /**
     *
     * @param tail
     */
    public void setTail(byte[] tail);

    /**
     *
     * @return
     */
    public boolean isHasHeader();

//    public void setHasHeader(boolean hasHeader);
    /**
     *
     * @return
     */
    public boolean isHasTail();

    /**
     *
     * @return
     */
    public byte[] getContent();
    
    
    public int getLength();
}
