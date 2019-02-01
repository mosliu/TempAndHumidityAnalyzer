/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.THanalyzer.utils;

/**
 *
 * @author Administrator
 */
public class StatisticUtil {

    double max = Double.MIN_VALUE;
    double min = Double.MAX_VALUE;
    double avg = 0;

//    double pfh=0;//平方和
    double total = 0;
    double times = 0;

    double variance = 0;

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getAvg() {
        return avg;
    }

    public double getTotal() {
        return total;
    }

    public double getTimes() {
        return times;
    }

    public double getVariance() {
        return variance;
    }

    
    
    public StatisticUtil() {
    }

    public static void main(String[] args) {
        StatisticUtil u = new StatisticUtil();
        int[] l = new int[]{2, 2, 2,2,2,2,2};
        for (int i = 0; i < l.length; i++) {
            System.out.println("=============" + i + "===============");
            u.put(l[i]);

        }
    }

    public void put(double k) {

        max = max > k ? max : k;
        min = min < k ? min : k;
        total = total + k;
//        variance = (((times++) * variance - avg * avg) + k * k - (total / times) * (total / times)) / (times + 1);
        if (times == 0) {
            variance = 0;
        } else {
            double newavg = total / (times + 1);
            variance = (times/(times+1))*variance+ (k*k/(times+1))+(times/(times+1))*avg*avg-newavg*newavg;
        }
        times++;
        avg = total / times;

//        System.out.print("avg:" + avg);
//        System.out.println("  var:" + variance);
//        variance =( pfh+k*k-2*total*avg + avg*avg*times);

    }
    
    @Override
    public String toString(){
        return String.format("总共统计数据量%.0f个，最大值：%3.2f，最小值：%3.2f，平均值：%3.2f，方差：%3.2f，标准差：%3.2f%n", times,max,min,avg,variance,Math.sqrt(variance));
    }
}
