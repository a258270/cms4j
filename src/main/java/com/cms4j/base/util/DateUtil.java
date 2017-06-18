package com.cms4j.base.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * Created by zmj on 2017/6/15.
 */
public class DateUtil {
    public static Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String date2Str(Date date){
        return date2Str(date,"yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
     * @param date
     * @return
     */
    public static Date str2Date(String date){
        if(!(date.isEmpty())){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new Date();
        }else{
            return null;
        }
    }

    /**
     * 按照参数format的格式，字符串转日期
     * @param date,format
     * @return
     */
    public static Date str2Date(String date, String format){
        if(!(date.isEmpty())){
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                return sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new Date();
        }else{
            return null;
        }
    }

    /**
     * 按照参数format的格式，日期转字符串
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date,String format){
        if(date!=null){
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }else{
            return "";
        }
    }

    /**
     * 把时间根据时、分、秒转换为时间段
     * @param StrDate
     */
    public static String getTimes(String StrDate){
        String resultTimes = "";

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date now;

        try {
            now = new Date();
            java.util.Date date=df.parse(StrDate);
            long times = now.getTime()-date.getTime();
            long day  =  times/(24*60*60*1000);
            long hour = (times/(60*60*1000)-day*24);
            long min  = ((times/(60*1000))-day*24*60-hour*60);
            long sec  = (times/1000-day*24*60*60-hour*60*60-min*60);

            StringBuffer sb = new StringBuffer();
            //sb.append("发表于：");
            if(hour>0 ){
                sb.append(hour+"小时前");
            } else if(min>0){
                sb.append(min+"分钟前");
            } else{
                sb.append(sec+"秒前");
            }

            resultTimes = sb.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return resultTimes;
    }
}
