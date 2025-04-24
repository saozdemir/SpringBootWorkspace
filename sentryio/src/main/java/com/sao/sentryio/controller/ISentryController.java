package com.sao.sentryio.controller;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 22 Apr 2025
 * <p>
 * @description:
 */
public interface ISentryController {
    String testSentry();
    String testSentryError() throws Exception;
    String testSentryLoggerWithLog4j();
}
