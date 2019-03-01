package com.realtech.pandora.common.utils;

import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by zhuqq
 */
public class DateCompareUtil {

    /**
     * 比较字符串型时间大小
     * @param timeA
     * @param timeB
     * @return
     */
    public static int compareDate(String timeA, String timeB) {
        long flag = convertTimeToLong(timeA) - convertTimeToLong(timeB);
        if (flag == 0) {
            return 0;
        } else if (flag > 0) {
            return 1;
        } else {
            return -1;
        }
    }


    public static Long convertTimeToLong(String time) {
        Assert.notNull(time, "time is null");
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(time, ftf);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
