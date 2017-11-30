package com.xuan.qingya.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zhouzhixuan on 2017/11/30.
 */

public class DateUtil {
    public static String getDate() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        return ft.format(calendar.getTime());
    }
}
