package com.crm4j.base.util;

import java.sql.Date;
import java.sql.Timestamp;

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
}
