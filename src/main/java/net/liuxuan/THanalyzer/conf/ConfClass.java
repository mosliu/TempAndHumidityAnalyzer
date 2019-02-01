/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.THanalyzer.conf;

import java.io.File;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.liuxuan.THanalyzer.utils.ConfUtils;

/**
 *
 * @author Administrator
 */
 abstract  public class ConfClass implements IConf {

     private static final long serialVersionUID = 17895768923756L;
     
    transient private static String SavePath = System.getProperty("user.dir") + "\\";
    transient private File confFile;
    transient IConf  confObj;
    
    /**
     * 获取存储位置
     * @return
     */
    @Override
    public String getSavepath() {
        return this.SavePath+this.getClass().getSimpleName()+".conf";
    }
    
    /**
     *获取存储文件
     * @return
     */
    protected File getSaveFile(){
        if (confFile == null) {
            confFile = new File(getSavepath());
        }
        return confFile;
    }
    
//    @Override
    /**
     * 保存配置
     */
    public  void save() {
        ConfUtils.writeObjectToFile(this,getSaveFile());
    }
    
//    @Override
    /**
     * 读取配置
     */
    public  void load() {
        if(!getSaveFile().exists()){
            confObj =null;
            return;
        }
        confObj = (IConf) ConfUtils.readObjectFromFile(getSaveFile());
         try {
             reloadObjectValue();
             //ConfUtils.saveSerialConf(this);
         } catch (Exception ex) {
             Logger.getLogger(ConfClass.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
//    
//     public static void main(String[] args) {
//         ConfClass cc = new ConfClass();
//         System.out.println(cc.getSavepath());
//                 
//     }


    // 把一个字符串的第一个字母大写、效率是最高的、
    private static String changeGet2Set(String fildeName) throws Exception {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) 's';
        return new String(items);
    }

    /**
     * 自动设置参数属性 按照get和set配对的原则 仅对有get的目标进行set
     *
     * @throws Exception
     */
    public void reloadObjectValue() throws Exception {
        if (confObj != null) {//if (confObj!=null )  ----begin
            // 拿到该类
            Class<?> clz_this = this.getClass();
            Class<?> clz_load = confObj.getClass();

            Method[] ms_this = clz_this.getMethods();
            Method[] ms_load = clz_load.getMethods();
            for (Method m : ms_load) {
                if (m.getName().startsWith("get")) {
                    for (Method m1 : ms_this) {
                        if (m1.getName().equals(changeGet2Set(m.getName()))) {
                            //当getter 和 setter 成对出现时，使用setter
//                            System.out.println(m.getName());
//                            System.out.println(m1.getName());

                            Object[] arguments = new Object[m1.getParameterTypes().length];
                            //System.out.println(arguments.length);
                            arguments[0] = m.invoke(confObj);
                            m1.invoke(this, arguments);

                        }
                    }
                    //System.out.println(m.getName());
                }
            }

        }//if (object!=null )  ----end
    }

}
