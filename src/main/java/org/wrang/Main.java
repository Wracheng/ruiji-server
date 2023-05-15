package org.wrang;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.wrang.reggie.entity.Employee;

@SpringBootApplication
@Slf4j
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}