package com.herman.di.util;

import java.util.Random;

/**
 * 随机数工具类
 * @author hsh
 * @create 2018-08-29 15:44
 **/
public class RandomUtils {

    public static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBER_CHAR = "0123456789";

    public RandomUtils() {
    }

    public static String generateString(int length, String rangeChar) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < length; ++i) {
            sb.append(rangeChar.charAt(random.nextInt(rangeChar.length())));
        }

        return sb.toString();
    }

    public static String generateMixString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < length; ++i) {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length())));
        }

        return sb.toString();
    }

    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }

    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }

    public static String generateZeroString(int length) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; ++i) {
            sb.append('0');
        }

        return sb.toString();
    }

    public static String toFixdLengthString(int num, int fixdlenth) throws Exception {
        StringBuilder sb = new StringBuilder();
        String strNum = String.valueOf(num);
        if(fixdlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixdlenth - strNum.length()));
            sb.append(strNum);
            return sb.toString();
        } else {
            throw new Exception("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常!");
        }
    }
}
