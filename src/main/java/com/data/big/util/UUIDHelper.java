package com.data.big.util;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 提供唯一键值
 *
 * @author ghj
 */
public class UUIDHelper {

    public static String[] datas = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "A", "B", "C", "D", "E", "F"};
    public static String[] datas2 = {"1", "2", "3", "4", "5", "6", "7",
            "8", "9", "A", "B", "C", "D", "E", "F"};

    private UUIDHelper() {

    }

    /**
     * 重置计数器
     *
     * @author ghj
     */
    public static void resetCounter() {

        fileCounter = 0;
    }

    /**
     * @author ghj
     */
    private static int fileCounter = 0;

    /**
     * @author ghj
     */
    private static long alarmCounter = 0;

    /**
     * 获取唯一键值
     *
     * @author ghj
     */
    public static String getUUID() {

        return "" + new Random().nextInt(2147483647);
        // return "" + UUID.randomUUID();
    }

    public static void main(String a[]) {
        for (int i = 0; i < 20; i++) {
            System.out.println(UUIDHelper.getUUID());
        }

    }

    /**
     * 字符串前补0
     *
     * @author ghj
     */
    public static String append0(String src, int length) {
        int tmpLength = src.trim().length();
        for (int j = 0; j < length - tmpLength; j++) {
            src = "0" + src;
        }
        return src;
    }

    /**
     * 字符串前补0
     *
     * @author ghj
     */
    public static String getFileCode() {
        fileCounter++;
        return DateFormatHelper.date2String(new Date(), "yyMMddHH")
                + append0(fileCounter + "", 6);
    }

    /**
     * 获取随机整数
     *
     * @author ghj
     */
    public static int getUUID4Int() {

        return new Random().nextInt(2147483647);
    }

    /**
     * 获取cuid
     *
     * @author ghj
     */
    public static String getCuId(String code, int length) {
        String result = code;
        int tempLength = length = code.length();
        if (tempLength > 0) {
            result = result
                    + append0(DateFormatHelper.date2String(new Date(),
                    "MMddHHmmss")
                    + (new Random().nextInt(999999)), tempLength);
        }
        return result;
    }

    public static String getUserCRCCCode(String header) {
        String result = "";
        tagB:
        for (String d1 : datas) {
            for (String d2 : datas) {
                for (String d3 : datas) {
                    for (String d4 : datas) {
                        for (String d5 : datas) {
                            for (String d6 : datas) {
                                result = header + d1 + d2 + d3 + d4 + d5 + d6;
                                break tagB;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 获取唯一键值
     *
     * @author ghj
     */
    public static String getLogUUID() {
        return DateFormatHelper.date2String(new Date(), "yyyyMMddHHmmss")
                + (new Random().nextInt(999999));
    }

    public static String getUUIDStr() {
        UUID uuid = UUID.randomUUID();
        return DateFormatHelper.date2String(new Date(), "yyyyMMddHHmmss") + uuid.toString();
    }
}