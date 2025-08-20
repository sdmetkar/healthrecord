package com.openguv.healthrecord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HeathRecordAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeathRecordAppApplication.class, args);
    }

}
