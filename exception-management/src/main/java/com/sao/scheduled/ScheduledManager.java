package com.sao.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 05 Şub 2025
 * <p>
 * @description:
 */
@Component
public class ScheduledManager {

    /**
     * Bu metot belirtilen saat, dakika ve saniye için her gün çalışır.
     * @Scheduled(cron = "mm ss hh D M DoW")
     * mm:Saniye
     * ss: Dakika
     * hh: Saat
     * D: gün
     * M: ay
     * DoW: haftanın Günü
     */
    //@Scheduled(cron = "0 28 09 * * ?")
    public void writeConsoleLogByTime() {
        System.out.println("writeConsoleLogByTime");
        for (int i=0;i<10;i++) {
            System.out.print(i + " ");
        }
    }

    /**
     * 0/5 ifadesi her 5 dakikada anlamında kullanılır.
     */
    //@Scheduled(cron = "0 0/5 * * * ?")
    public void writeConsoleLogEveyFiveMinute() {
        System.out.println("writeConsoleLogEveyFiveMinute");
        for (int i=0;i<10;i++) {
            System.out.print(i + " ");
        }
    }
}
