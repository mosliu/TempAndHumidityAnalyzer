/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.THanalyzer.utils.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.liuxuan.THanalyzer.THAnalyzer;
import org.sqlite.SQLiteConfig;

/**
 *
 * @author Administrator
 */
public class SQLiteHelper {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SQLiteHelper.class);
    private Connection conn = null;
    boolean init = false;
    SQLiteConfig config;
    String dbstr;
    
    
    int year =0;
    private SQLiteHelper() {
    }

    /**
     * 
     * @param str 
     */
    public SQLiteHelper(String str) {
        config = new SQLiteConfig();
        // config.setReadOnly(true);   
        config.setSharedCache(true);
        //config.recursiveTriggers(true);
        dbstr = str;
        year = getThisYear();
        init();
    }
    /**
     * 
     * @param year  年份
     * @param path  存储路径
     */
    public SQLiteHelper(int year,String path) {
        String connstr = this.getdbConnStr(path, year);
        config = new SQLiteConfig();
        // config.setReadOnly(true);   
        config.setSharedCache(true);
        //config.recursiveTriggers(true);
        dbstr = connstr;
        init();
        this.year=year;
    }

    /**
     *
     * @param str
     * @param conf
     */
    public SQLiteHelper(String str, SQLiteConfig conf) {
        this.config = conf;
        dbstr = str;
        init();
    }

    /**
     *
     * @return
     */
    public static  int getThisYear(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int startyear = cal.get(Calendar.YEAR);
        return startyear;
    }
    
    /**
     * 需要做返回值的null检查，如有错误则返回null
     *
     * @return
     */
    public Connection getConnection() {
        //解决跨年不重启出问题！！！！！
        if(year==getThisYear()){
            
        }else{
            dbstr = dbstr.replaceAll(Integer.toString(year),Integer.toString(year+1));
            connect();
            System.out.println("新一年了");
            year=getThisYear();
            PrepareTable();
        }
        
        if (dbstr.toLowerCase().startsWith("jdbc:sqlite:")) {
            //姑且认为是正常的
        } else {
            logger.error("传入初始化数据库的connstr错误");
            return null;
        }

        connect();

        return conn;
    }

    private void init() {
        try {
            //连接SQLite的JDBC
            Class.forName("org.sqlite.JDBC");
            reconnect();
        } catch (ClassNotFoundException ex) {
            logger.error("ERROR:{}", ex);
        }
    }

    private void connect() {
        if (!init) {
            init();
        }
        if (conn == null) {
            reconnect();
        }
        try {
            if (conn.isClosed()) {
                reconnect();
            }
        } catch (SQLException ex) {
            logger.error("ERROR:{}", ex);
        }
    }

    private void reconnect() {

        try {
            //            Connection conn = DriverManager.getConnection("jdbc:sqlite:zieckey.db", config.toProperties());
            conn = DriverManager.getConnection(dbstr, config.toProperties());
        } catch (SQLException ex) {
            logger.error("ERROR:{}", ex);
        }
    }

    /**
     * 获得sqlite 链接字符串
     *
     * @param path
     * @param year
     * @return
     */
    public  String getdbConnStr(String path,int year) {
        //确定数据库
        String dbpath = "jdbc:sqlite:" + path+ File.separator + year + "Temperaure.db";
        return dbpath;
    }
    
    /**
     *
     * @param tableName
     * @return
     */
    public boolean isTableExist(String tableName) {
        try {
            connect();
            Statement stat = conn.createStatement();
            String sqlexists = "SELECT COUNT(*)count FROM sqlite_master where type='table' and name='" + tableName + "'";
            ResultSet rs = stat.executeQuery(sqlexists);
            String count = "0";
            if (rs.next()) {
                count = rs.getString("count");
            }

//            while (rs.next()) { //将查询到的数据打印出
            rs.close();

            if (count.equals("1")) {
                return true;
            }
        } catch (SQLException ex) {
            logger.error("ERROR:{}", ex);
        }
        return false;
    }

    /**
     * 准备好插入的表
     */
    public  void PrepareTable() {
        boolean istbl = isTableExist("TempHumLog");
        if (!istbl) {
            try {
                getConnection();
                Statement stat = conn.createStatement();
                String createsql = "CREATE TABLE \"TempHumLog\" (\n"
                        + "            \"id\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
                        + "             \"TimeStamp\"  INTEGER ,\n"
                        + "             \"Temprature\"  INTEGER,\n"
                        + "             \"Humidity\"  INTEGER\n"
                        + "             )";
                stat.executeUpdate(createsql); //创建一个表，两列
                conn.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(THAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
