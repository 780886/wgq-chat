package com.wgq.chat.contact.boot;


import com.wgq.chat.contact.po.SparrowExample;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

import static com.wgq.chat.contact.po.generator.CodeGenerator.generaCreateDDL;

@SpringBootApplication(scanBasePackages = "com.wgq.*")
@MapperScan("com.wgq.chat.contact.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        try {
            generaCreateDDL(SparrowExample.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
