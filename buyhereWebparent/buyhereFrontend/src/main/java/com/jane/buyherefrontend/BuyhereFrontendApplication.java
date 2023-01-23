package com.jane.buyherefrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.jane.buyherecommon.entity"})
public class BuyhereFrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuyhereFrontendApplication.class, args);
    }

}
