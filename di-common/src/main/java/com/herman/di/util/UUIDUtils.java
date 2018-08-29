package com.herman.di.util;

import java.util.UUID;

/**
 * UUID辅助工具
 *
 * @author hsh
 * @create 2018-08-29 15:43
 **/
public class UUIDUtils {
    public UUIDUtils() {
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
}
