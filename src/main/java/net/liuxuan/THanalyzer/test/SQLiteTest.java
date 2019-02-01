/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.THanalyzer.test;

import net.liuxuan.THanalyzer.utils.database.SQLiteHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class SQLiteTest {

    public static void main(String[] args) {

        try {
            String fileName = "D:/dd.db";
            String sqlstr = "jdbc:sqlite:" + fileName;
            SQLiteHelper helper = new SQLiteHelper(sqlstr);
            Connection conn = helper.getConnection();
            Statement stat = conn.createStatement();
            String sqlexists = "SELECT COUNT(*)count FROM sqlite_master where type='table' and name='TempHumLog1'";
            ResultSet rs = stat.executeQuery(sqlexists);
            
            if(rs.next()){
                System.out.println(rs.getString("count"));
            }
//            while (rs.next()) { //将查询到的数据打印出来
//                rs.
//                System.out.print("name = " + rs.getString("name") + " "); //列属性一
//
//                System.out.println("salary = " + rs.getString("salary")); //列属性二
//
//            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    public static void main1(String[] args) {
        try {

            String fileName = "D:/dd.db";
            String sqlstr = "jdbc:sqlite:" + fileName;
            SQLiteHelper helper = new SQLiteHelper(sqlstr);
            Connection conn = helper.getConnection();
            Statement stat = conn.createStatement();
            /*
             CREATE TABLE "TempHumLog" (
             "id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
             "TimeStamp"  TEXT DEFAULT NULL,
             "Temprature"  INTEGER,
             "Humidity"  INTEGER
             )
             ;

             INSERT INTO "main"."sqlite_sequence" (name, seq) VALUES ('NewTable', 0);


             */
            String createsql = "CREATE TABLE \"TempHumLog\" (\n"
                    + "            \"id\"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
                    + "             \"TimeStamp\"  TEXT DEFAULT NULL,\n"
                    + "             \"Temprature\"  INTEGER,\n"
                    + "             \"Humidity\"  INTEGER\n"
                    + "             )";
            stat.executeUpdate(createsql); //创建一个表，两列
            String insertsql = "insert into TempHumLog values(NULL,'2015-09-08 09:56:12.123',8000,1000);";

            long start = System.currentTimeMillis();

            stat.executeUpdate("begin;\n" + insertsql);

            for (int i = 0; i < 31536; i++) {
                for (int j = 0; j < 1000; j++) {
                    stat.addBatch(insertsql);
                }
                stat.executeBatch();
                System.out.println("处理了" + (i + 1) * 1000 + "条，用时" + (System.currentTimeMillis() - start));
            }

            stat.executeUpdate(insertsql + "commit;\n");
//            stat.executeUpdate(insertsql); //插入数据

//            ResultSet rs = stat.executeQuery("select * from TempHumLog;"); //查询数据
//
//            while (rs.next()) { //将查询到的数据打印出来
//
//                System.out.print("name = " + rs.getString("name") + " "); //列属性一
//
//                System.out.println("salary = " + rs.getString("salary")); //列属性二
//
//            }
//            rs.close();
            conn.close(); //结束数据库的连接
//            // Create a Statement object for the database connection, dunno what this stuff does though.
//            Statement stmt = conn.createStatement();
//
//            // Create a result set object for the statement
//            ResultSet rs = stmt.executeQuery("SELECT * FROM TempHumLog ORDER BY id ASC");
//            // Iterate the result set, printing each column
//            // if the column was an int, we could do rs.getInt(column name here) as well, etc.
//            while (rs.next()) {
//                String id = rs.getString("country_id");   // Column 1
//                String code = rs.getString("country_code"); // Column 2
//                String name = rs.getString("country_name"); // Column 3
//                System.out.println("ID: " + id + " Code: " + code + " Name: " + name);
//
//            }
//            // Close the connection
//            conn.close();
        } catch (Exception e) {
            // Print some generic debug info
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println(e.toString());
        }
    }
}
