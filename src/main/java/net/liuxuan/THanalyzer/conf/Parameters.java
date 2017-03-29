/* * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.THanalyzer.conf;

import net.liuxuan.THanalyzer.serial.modbus.ModbusInfo;
import net.liuxuan.THanalyzer.serial.modbus.SerialModbus;
import net.liuxuan.THanalyzer.serial.SerialWrapper;

/**
 * 参数存储文件
 *
 * @version 2015.09.06
 * @author Moses
 */
public class Parameters extends ConfClass {

    /**
     * commType 通讯类型，MODBUS:0 T1:1
     */
    int commType;
    SerialWrapper serialPort;
    //Modbus信息保存
    ModbusInfo modbusinfo;
    //记录保存路径
    String logSavePath;
    //取点间隔
    int Interval;
    //是否存库的同时存盘文件以供直接查看
    boolean isSaveFile;
    // 数据压缩至多少点  1000即表示表格压缩至1000个数据点
    int compressnum;
    //主界面数据显示多少时间的
    int showChartTime;
    
    //间隔最小值
    transient private static final int IntervalMinValue = 200;
    
    /**
     * 新参数
     */
    public void init() {
        commType = 0;
        serialPort = new SerialModbus();
        modbusinfo = new ModbusInfo();
        logSavePath = "D:\\templog";
        Interval = 1000;
        isSaveFile=true;
        compressnum=1000;
        showChartTime=86400;
    }

    public int getShowChartTime() {
        return showChartTime;
    }

    public void setShowChartTime(int showChartTime) {
        this.showChartTime = showChartTime;
    }
    
    public int getCompressnum() {
        return compressnum;
    }

    public void setCompressnum(int compressnum) {
        this.compressnum = compressnum;
    }

    
    
    public boolean getIsSaveFile() {
        return isSaveFile;
    }

    public void setIsSaveFile(boolean isSaveFile) {
        this.isSaveFile = isSaveFile;
    }

        
    public int getInterval() {
        if (Interval < this.modbusinfo.getTimeout()) {
            Interval =  this.modbusinfo.getTimeout() + 50;
        }
        //间隔必须不小于200
        if(Interval<IntervalMinValue){
            Interval=IntervalMinValue;
        }
        return Interval;
    }

    public void setInterval(int Interval) {
        this.Interval = Interval;
    }

    public String getLogSavePath() {
        return logSavePath;
    }

    public void setLogSavePath(String logSavePath) {
        this.logSavePath = logSavePath;
    }

    public ModbusInfo getModbusinfo() {
        return modbusinfo;
    }

    public void setModbusinfo(ModbusInfo modbusinfo) {
        this.modbusinfo = modbusinfo;
    }

    public int getCommType() {
        return commType;
    }

    public void setCommType(int commType) {
        this.commType = commType;
    }

    public SerialWrapper getSerialPort() {
        return serialPort;
    }

    public void setSerialPort(SerialWrapper serialPort) {
        this.serialPort = serialPort;
    }

    public void reload() {
        this.load();
        //无存储则新建一套参数
        if (confObj == null) {
            init();
        }
    }

//    public static void main(String[] args) {
//        Parameters p = new Parameters();
//        p.setCommType(3333);
//        p.save();
//        p.commType = 4444;
//        p.reload();
//        System.out.println(p.getCommType());
//
//    }

}
