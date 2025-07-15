package com.sao.galleria.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 15 Tem 2025
 * <p>
 * @description:
 */
public final class DateUtils {
    public static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(new Date());
    }

    public static String getYesterdayDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date yesterday = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        return simpleDateFormat.format(yesterday);
    }
}
