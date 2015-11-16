package com.example.chenf.screenoncount;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenf on 2015/10/21.
 */
public class TimeTransform {
    public static Long StringToLong(String str_time) throws ParseException {
        Calendar c = Calendar.getInstance();

        c.setTime(new SimpleDateFormat("HH:mm:ss").parse(str_time));

        return c.getTimeInMillis();
    }

    public static String LongToString(Long long_time){
        Date date = new Date(long_time);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        return sdf.format(date);
    }

    public static String getDate(Long millisecond){
        Date date = new Date(millisecond);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(date);
    }
    public static Long getDate_mill(String millisecond){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf.parse(millisecond);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
    public static String getYesterday(Long millisecond){
        Date date = new Date(millisecond-24*60*60*1000);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(date);
    }
}
