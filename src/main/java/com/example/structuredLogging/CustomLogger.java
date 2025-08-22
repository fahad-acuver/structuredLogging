package com.example.structuredLogging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("withStructure")
public class CustomLogger implements CommandLineRunner {

    private static final Logger log= LoggerFactory.getLogger(CustomLogger.class);
    @Override
    public void run(String... args) throws Exception {
//        MDC.put("id","beans");
//        log.info("custom message sim take 1");
//        MDC.remove("id");
        log.atInfo().setMessage("wazzaaap").addKeyValue("someKey","someVal").log();
    }
}
