package com.data.big.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatHelper {
    public static Date String2Date(String time, String format) {
        Date date = null;
        try {

            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;

    }

    public static String date2String(Date time, String format) {
        String datestr = null;
        try {

            SimpleDateFormat sdf = new SimpleDateFormat(format);

            datestr = sdf.format(time);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return datestr;

    }

    public static String getCurrenttime4String() {
        String datestr = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            datestr = sdf.format(new Date());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return datestr;
    }

    /*
     *
     * 获取当前时间之前或之后几秒钟
     */

    public static String getTimeBySecond(int second) {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.SECOND, second);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar
                .getTime());

    }

    /**
     * 获取时间多少秒时间
     */
    public static String getDateBeforSecond(Date date, int second) {

        Calendar calendar = new java.util.GregorianCalendar();

        calendar.add(Calendar.SECOND, second);

        date = calendar.getTime();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateString = formatter.format(date);

        return dateString;

    }

    /**
     * 获取时间多少秒时间
     */
    public static String getDateBeforDay(Date date, int day) {

        Calendar calendar = new java.util.GregorianCalendar();

        calendar.add(Calendar.DATE, day);

        date = calendar.getTime();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = formatter.format(date);

        return dateString;

    }

    /*
     *
     * 获取当前时间之前或之后几秒钟
     */
    public static String getTimeBySecond(String time, int second) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date timeDate = String2Date(time, "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timeDate);
        calendar.add(Calendar.SECOND, -second);
        return formater.format(calendar
                .getTime());

    }

    public static void main(String a[]) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//		System.out.println(sdf.format(new Date()));
//		System.out.println(date2String(String2Date("2002-10-8 15:30:22",
//				"yyyy-MM-dd HH:mm:ss"), "yyMMddHHmmss"));
        System.out.println(getDateBeforDay(new Date(), -30));
    }
}
