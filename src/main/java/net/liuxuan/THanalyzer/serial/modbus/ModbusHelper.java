/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.THanalyzer.serial.modbus;

import com.serotonin.io.serial.SerialParameters;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersResponse;
import com.serotonin.modbus4j.msg.ReadInputRegistersRequest;
import com.serotonin.modbus4j.msg.ReadInputRegistersResponse;
import com.serotonin.modbus4j.msg.WriteRegistersRequest;
import com.serotonin.modbus4j.msg.WriteRegistersResponse;
import java.util.Arrays;
import java.util.Date;
import net.liuxuan.THanalyzer.conf.Parameters;
import org.slf4j.Logger;

/**
 *
 * @author Administrator
 */
public class ModbusHelper {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ModbusHelper.class);
    private static ModbusMaster master = null;

    public static SerialParameters getModbusSerialParam(Parameters paras) {
        SerialParameters serialParameters = new SerialParameters();
        // 设定MODBUS通讯的串行口
        serialParameters.setCommPortId(paras.getSerialPort().getPortName());
        // 设定成无奇偶校验
        serialParameters.setParity(paras.getSerialPort().getParity());
        // 设定成数据位是8位
        serialParameters.setDataBits(paras.getSerialPort().getDataBits());
        // 设定为1个停止位
        serialParameters.setStopBits(paras.getSerialPort().getStopBits());
        // 设定端口名称
        serialParameters.setPortOwnerName(paras.getModbusinfo().getOwnerName());
        // 设定端口波特率
        serialParameters.setBaudRate(paras.getSerialPort().getBaudRate());
        return serialParameters;
    }

    public static ModbusMaster getModbusMaster(Parameters paras) {
        SerialParameters serialParameters = getModbusSerialParam(paras);

        // 创建ModbusFactory工厂实例
        ModbusFactory modbusFactory = new ModbusFactory();
        // 创建ModbusMaster实例
        if (master == null) {
            master = modbusFactory.createRtuMaster(serialParameters);
            master.setTimeout(paras.getModbusinfo().getTimeout());
        }
        return master;
    }

    /**
     * 读输入寄存器上的内容
     *
     * @param master 主站
     * @param slaveId 从站地址
     * @param start 起始地址的偏移量
     * @param len 待读寄存器的个数
     */
    public static short[] readInputRegistersTest(ModbusMaster master,
            int slaveId, int start, int len) throws ModbusTransportException {
        short[] list = new short[2];
        ReadInputRegistersRequest request = new ReadInputRegistersRequest(
                slaveId, start, len);
        ReadInputRegistersResponse response = (ReadInputRegistersResponse) master
                .send(request);
        if (response.isException()) {
            logger.error("Exception response: message=" + response.getExceptionMessage());
        } else {
//            System.out.println(Arrays.toString(response.getShortData()));
            logger.debug("获取数值为：{}", Arrays.toString(response.getShortData()));
            list = response.getShortData();
//                jLabel_showTemp.setText(list[0] / 100 + "." + list[0] % 100);
//                jLabel_ShowHumidity.setText(list[1] / 100 + "." + list[1] % 100);
//
//                System.out.println("");
        }

        return list;
    }

    /**
     * 批量写数据到保持寄存器
     *
     * @param master 主站
     * @param slaveId 从站地址
     * @param start 起始地址的偏移量
     * @param values 待写数据
     */
    public static void writeRegistersTest(ModbusMaster master, int slaveId, int start, short[] values) {
        try {
            WriteRegistersRequest request = new WriteRegistersRequest(slaveId, start, values);
            WriteRegistersResponse response = (WriteRegistersResponse) master.send(request);
            if (response.isException()) {
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            } else {
                System.out.println("Success");
            }
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读保持寄存器上的内容
     *
     * @param master 主站
     * @param slaveId 从站地址
     * @param start 起始地址的偏移量
     * @param len 待读寄存器的个数
     */
    private static void readHoldingRegistersTest(ModbusMaster master,
            int slaveId, int start, int len) {
        try {
            ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(
                    slaveId, start, len);
            ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse) master
                    .send(request);
            if (response.isException()) {
                System.out.println("Exception response: message="
                        + response.getExceptionMessage());
            } else {
                System.out.println(Arrays.toString(response.getShortData()));
                short[] list = response.getShortData();
                for (int i = 0; i < list.length; i++) {
                    System.out.print(list[i] + " ");
                }
            }
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }

}
