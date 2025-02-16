package com.sao.schedule;

import com.sao.service.IRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 17 Şub 2025
 * <p>
 * @description: refresh tokenları belli aralıkla silmesi içn yazıldı.
 */
@Component
public class ScheduleManager {

    @Autowired
    IRefreshTokenService refreshTokenService;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void deleteExpiredRefreshTokenEveryFiveMinutes() {
        refreshTokenService.deleteExpiredRefreshToken();
    }
}
