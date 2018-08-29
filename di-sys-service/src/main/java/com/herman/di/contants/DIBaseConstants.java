package com.herman.di.contants;

/**
 * 常量类
 *
 * @author hsh
 * @create 2018-08-29 16:03
 **/
public class DIBaseConstants {

    /**
     * 缓存过期时间
     */
    public static class RedisExpireTime {
        // 一天（秒）
        public static int ONEDAYSECOND = 24 * 60 * 60;
        // 一个月（秒）
        public static int ONEMONTHSECOND = 30 * 24 * 60 * 60;
        // 一个月（毫秒）
        public static Long ONEMONTHMSEC = 30 * 24 * 60 * 60 * 1000L;
        // 一周（毫秒）
        public static Long ONEWEEKMSEC = 7 * 24 * 60 * 60 * 1000L;
        // 半小时（秒）
        public static int HALFHOURSECOND = 30 * 60;
    }
}
