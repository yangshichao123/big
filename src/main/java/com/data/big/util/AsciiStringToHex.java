package com.data.big.util;

import java.io.UnsupportedEncodingException;

public class AsciiStringToHex {

    /**
     * ascii 转16进制
     *
     * @param str
     * @return
     */
    public static String convertStringToHex(String str) {

        char[] chars = str.toCharArray();

        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString((int) chars[i]));
        }

        return hex.toString();
    }

    /**
     * 16进制转ASCII
     *
     * @param hex
     * @return
     */
    public static String convertHexToString(String hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char) decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }

    /**
     * 字符串 转 ascii
     *
     * @param value
     * @return
     */
    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1) {
                sbu.append((int) chars[i]).append(",");
            } else {
                sbu.append((int) chars[i]);
            }
        }
        return sbu.toString();
    }

    /**
     * ascii  转字符串
     *
     * @param value
     * @return
     */
    public static String asciiToString(String value) {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }

    /**
     * 16 转 10进制
     *
     * @param value
     * @return
     */
    public static String HexTo10(String value) {
        int i;
        if ("ffff".equals(value)) {
            value = "0";
            i = Integer.parseInt(value, 16);
        } else {
            i = Integer.parseInt(value, 16);
            i++;
        }
        String s = Integer.toHexString(i);
        if (s.length() == 1) {
            s = "000" + s;
        } else if (s.length() == 2) {
            s = "00" + s;
        } else if (s.length() == 3) {
            s = "0" + s;
        }

        return s;
    }

    /**
     * UTF-8编码 转换为对应的 汉字
     * <p>
     * URLEncoder.encode("上海", "UTF-8") ---> %E4%B8%8A%E6%B5%B7
     * URLDecoder.decode("%E4%B8%8A%E6%B5%B7", "UTF-8") --> 上 海
     * <p>
     * convertUTF8ToString("E4B88AE6B5B7")
     * E4B88AE6B5B7 --> 上海
     *
     * @param s
     * @return
     */
    public static String convertUTF8ToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }

        try {
            s = s.toUpperCase();

            int total = s.length() / 2;
            int pos = 0;

            byte[] buffer = new byte[total];
            for (int i = 0; i < total; i++) {

                int start = i * 2;

                buffer[i] = (byte) Integer.parseInt(
                        s.substring(start, start + 2), 16);
                pos++;
            }

            return new String(buffer, 0, pos, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
     *
     * @param s 原串
     * @return
     */
    public static String convertStringToUTF8(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        try {
            char c;
            for (int i = 0; i < s.length(); i++) {
                c = s.charAt(i);
                if (c >= 0 && c <= 255) {
                    sb.append(c);
                } else {
                    byte[] b;

                    b = Character.toString(c).getBytes("utf-8");

                    for (int j = 0; j < b.length; j++) {
                        int k = b[j];
                        if (k < 0)
                            k += 256;
                        sb.append(Integer.toHexString(k).toUpperCase());
                        // sb.append("%" +Integer.toHexString(k).toUpperCase());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return sb.toString();
    }


    //504F533838383834  POS88884
    public static void main(String[] args) {
        String test = "3c4a494e475745495f4a494e475a48414e475849414e2c4a494e475745495f4a494e475a48414e475849414e3e";

        System.out.println("\n-----ASCII码转换为16进制 -----");
        String str = "32";
        System.out.println("字符串: " + str);
        String hex = convertStringToHex(str);
        System.out.println("转换为16进制 : " + hex);

        System.out.println("\n***** 16进制转换为ASCII *****");
        System.out.println("Hex : " + hex);
        String s1 = convertHexToString("00");
        System.out.println("ASCII : " + s1);
        String s2 = convertUTF8ToString(test);
        System.out.println("汉字 : " + s2);


        /*String s = Integer.toHexString(-1);
        System.out.println(s);
        Integer.parseInt("c0",16);
        System.out.println(Integer.parseInt("32",16));*/


        /*String sd="0";
        for(int i=0;i<1000000;i++){
            sd= HexTo10(sd);
            System.out.println(sd);
        }*/
    }
}