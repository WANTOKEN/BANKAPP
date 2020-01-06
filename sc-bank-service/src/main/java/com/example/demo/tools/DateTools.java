package com.example.demo.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class DateTools {
    /**
     * 单号构造器
     * @param prefix 前缀
     * @return 单号
     */
    public String createNum(String prefix){
        Random rand=new Random();//随机数
        int j=rand.nextInt(99);
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyMMddhhmmss");
        String num = prefix+j+ft.format(dNow);
        return num;
    }
    public String currentDateTime(){
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        ft.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String dateString = ft.format(dNow);
        return dateString;
    }
    public String currentDate(){
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        String dateString = ft.format(dNow);
        return dateString;
    }

}
