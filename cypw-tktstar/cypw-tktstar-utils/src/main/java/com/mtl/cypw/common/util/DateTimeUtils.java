package com.mtl.cypw.common.util;

import com.juqitech.service.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-15 16:08
 */
public class DateTimeUtils {


    /**
     * 判断 target 是否在日期 begin ~ end 之内
     * (target == null || begin == null || end == null) 默认为 false
     * @param target
     * @param begin
     * @param end
     * @return
     */
    public static boolean withinValidityPeriod(Date target, Date begin, Date end) {
        if (target == null || begin == null || end == null) {
            return false;
        }
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(DateUtils.now());

        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(begin);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);

        return targetCalendar.after(beginCalendar) && targetCalendar.before(endCalendar);

    }
}
