package com.sao.sentryio.starter;

import io.sentry.Sentry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan(basePackages = "com.sao")
@ComponentScan(basePackages = "com.sao")
@SpringBootApplication
public class SentryApplicationStarter implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SentryApplicationStarter.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Sentry.init(options -> {
            options.setDsn("https://190715c453c0c45b36be3359d3063dfa@o4509184650379264.ingest.de.sentry.io/4509196735348816");
            options.setTracesSampleRate(1.0);
        });
    }
}
