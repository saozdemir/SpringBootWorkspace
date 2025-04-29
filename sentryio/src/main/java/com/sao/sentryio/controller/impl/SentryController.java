package com.sao.sentryio.controller.impl;

import com.sao.sentryio.controller.ISentryController;
import io.sentry.Sentry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 22 Apr 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping("/sentryio")
public class SentryController implements ISentryController {
    private static final Logger logger = LogManager.getLogger(SentryController.class);

    @GetMapping(path = "/test-sentry")
    @Override
    public String testSentry() {
        return "Test sentry success!";
    }

    @GetMapping(path = "/test-sentry-error")
    @Override
    public String testSentryError() throws Exception {
        try {
            throw new Exception("Sentry test exception!");
        } catch (Exception e) {
            Sentry.captureException(e);
            throw e;
        }
    }

    @GetMapping(path = "/test-sentry-logger")
    @Override
    public String testSentryLoggerWithLog4j() {
        try {
            throw new RuntimeException("Test Exception for Logger!");
        } catch (RuntimeException e) {
            logger.error("Test Exception for Logger CAPTURED!");
            logger.info("Sentry INFO message");
            logger.warn("Sentry WARN message");
            
            throw new RuntimeException(e);
        }
    }

}
