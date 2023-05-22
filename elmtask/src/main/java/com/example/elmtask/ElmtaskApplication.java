package com.example.elmtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.example"})
public class ElmtaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElmtaskApplication.class, args);
    }

}
