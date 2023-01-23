package com.jane.buyherebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.jane.buyherecommon.entity"})
public class BuyhereBackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(BuyhereBackendApplication.class, args);
    }

}
