/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.THanalyzer.UI;

import java.io.File;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.filechooser.FileSystemView;
import net.liuxuan.THanalyzer.conf.Parameters;

/**
 *
 * @author Administrator
 */
public class SystemSettingWindow extends javax.swing.JDialog {

    Parameters para;

    /**
     * Creates new form SystemSettingWindow
     */
    public SystemSettingWindow(java.awt.Frame parent, boolean modal, Parameters para) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.para = para;
        init();
    }

    private void init() {

        jTextField_savelog.setText(para.getLogSavePath());
        jFormattedTextField_Interval.setValue(para.getInterval());
        jCheckBox_filesave.setSelected(para.getIsSaveFile());
        
        jFormattedTextField_compressNum.setValue(para.getCompressnum());
        
        jFormattedTextField_chartshowTime.setValue(para.getShowChartTime());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_savelog = new javax.swing.JPanel();
        jTextField_savelog = new javax.swing.JTextField();
        jLabel_savelog = new javax.swing.JLabel();
        jButton_savelog = new javax.swing.JButton();
        jFormattedTextField_Interval = new javax.swing.JFormattedTextField();
        jLabel_Interval = new javax.swing.JLabel();
        jCheckBox_filesave = new javax.swing.JCheckBox();
        jButton_ok = new javax.swing.JButton();
        jButton_cancle = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel_compress = new javax.swing.JLabel();
        jFormattedTextField_compressNum = new javax.swing.JFormattedTextField(NumberFormat.getIntegerInstance());
        jPanel2 = new javax.swing.JPanel();
        jFormattedTextField_chartshowTime = new JFormattedTextField(NumberFormat.getIntegerInstance());
        jLabel_chartshowTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("系统参数设置");
        setModal(true);

        jPanel_savelog.setBorder(javax.swing.BorderFactory.createTitledBorder("记录选项"));

        jTextField_savelog.setText("d:\\templog\\");

            jLabel_savelog.setText("存储位置：");

            jButton_savelog.setText("...");
            jButton_savelog.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_savelogActionPerformed(evt);
                }
            });

            jFormattedTextField_Interval.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
            jFormattedTextField_Interval.setText("1000");
            jFormattedTextField_Interval.setToolTipText("单位：ms，应大于超时设置，否则会出现问题");

            jLabel_Interval.setText("取点间隔：");

            jCheckBox_filesave.setSelected(true);
            jCheckBox_filesave.setText("保存文件记录");

            javax.swing.GroupLayout jPanel_savelogLayout = new javax.swing.GroupLayout(jPanel_savelog);
            jPanel_savelog.setLayout(jPanel_savelogLayout);
            jPanel_savelogLayout.setHorizontalGroup(
                jPanel_savelogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_savelogLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel_savelogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel_savelogLayout.createSequentialGroup()
                            .addComponent(jLabel_savelog)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField_savelog, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton_savelog, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel_savelogLayout.createSequentialGroup()
                            .addComponent(jLabel_Interval)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jFormattedTextField_Interval, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBox_filesave)))
                    .addContainerGap(14, Short.MAX_VALUE))
            );
            jPanel_savelogLayout.setVerticalGroup(
                jPanel_savelogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_savelogLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel_savelogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField_savelog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_savelog)
                        .addComponent(jButton_savelog))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel_savelogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_Interval)
                        .addComponent(jFormattedTextField_Interval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox_filesave))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jButton_ok.setText("OK");
            jButton_ok.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_okActionPerformed(evt);
                }
            });

            jButton_cancle.setText("CANCEL");
            jButton_cancle.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton_cancleActionPerformed(evt);
                }
            });

            jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("查询选项"));

            jLabel_compress.setText("压缩点数");

            jFormattedTextField_compressNum.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
            jFormattedTextField_compressNum.setText("1000");
            jFormattedTextField_compressNum.setToolTipText("单位：ms，应大于超时设置，否则会出现问题");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel_compress)
                    .addGap(18, 18, 18)
                    .addComponent(jFormattedTextField_compressNum, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_compress)
                        .addComponent(jFormattedTextField_compressNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(28, Short.MAX_VALUE))
            );

            jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("数据展示选项"));

            jFormattedTextField_chartshowTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
            jFormattedTextField_chartshowTime.setText("86400");
            jFormattedTextField_chartshowTime.setToolTipText("单位：s，默认86400s");

            jLabel_chartshowTime.setText("显示时间");

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel_chartshowTime)
                    .addGap(18, 18, 18)
                    .addComponent(jFormattedTextField_chartshowTime, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_chartshowTime)
                        .addComponent(jFormattedTextField_chartshowTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(69, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel_savelog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_cancle, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_savelog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_ok)
                        .addComponent(jButton_cancle))
                    .addContainerGap())
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void jButton_savelogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_savelogActionPerformed
        String path = jTextField_savelog.getText();
        File pathf = new File(path);
        JFileChooser jfc = new JFileChooser();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        if (pathf.exists()) {
            jfc.setCurrentDirectory(pathf);
        } else {
            //得到桌面路径
            jfc.setCurrentDirectory(fsv.getHomeDirectory());
        }
        jfc.setDialogTitle("选择文件存储位置");
        jfc.setMultiSelectionEnabled(false);
        jfc.setDialogType(JFileChooser.OPEN_DIALOG);
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        jfc.setFileFilter(filter);
        int result = jfc.showOpenDialog(this);  // 打开"打开文件"对话框
        if (result == JFileChooser.APPROVE_OPTION) {
            String filepath = jfc.getSelectedFile().getAbsolutePath();
            jTextField_savelog.setText(filepath);
        }
        para.setLogSavePath(path);
    }//GEN-LAST:event_jButton_savelogActionPerformed

    private void jButton_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_okActionPerformed

        //取点间隔
        try {
            jFormattedTextField_Interval.commitEdit();
        } catch (ParseException ex) {

        }

//        Long interval = (Long) jFormattedTextField_Interval.getValue();
        ;
        para.setInterval(Integer.parseInt( jFormattedTextField_Interval.getText()));
        //存盘文件
        para.setIsSaveFile(jCheckBox_filesave.isSelected());
        
        
//        Long compressnum =(Long) jFormattedTextField_compressNum.getValue();
        para.setCompressnum(Integer.parseInt( jFormattedTextField_compressNum.getText()));
        
//        Long chartshowtime = (Long) jFormattedTextField_chartshowTime.getValue();
        para.setShowChartTime(Integer.parseInt( jFormattedTextField_chartshowTime.getText()));
        
        para.save();
        this.dispose();
    }//GEN-LAST:event_jButton_okActionPerformed

    private void jButton_cancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancleActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton_cancleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_cancle;
    private javax.swing.JButton jButton_ok;
    private javax.swing.JButton jButton_savelog;
    private javax.swing.JCheckBox jCheckBox_filesave;
    private javax.swing.JFormattedTextField jFormattedTextField_Interval;
    private javax.swing.JFormattedTextField jFormattedTextField_chartshowTime;
    private javax.swing.JFormattedTextField jFormattedTextField_compressNum;
    private javax.swing.JLabel jLabel_Interval;
    private javax.swing.JLabel jLabel_chartshowTime;
    private javax.swing.JLabel jLabel_compress;
    private javax.swing.JLabel jLabel_savelog;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_savelog;
    private javax.swing.JTextField jTextField_savelog;
    // End of variables declaration//GEN-END:variables
}
