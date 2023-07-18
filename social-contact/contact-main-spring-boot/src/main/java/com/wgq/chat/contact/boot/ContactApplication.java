package com.wgq.chat.contact.boot;


import com.wgq.chat.contact.po.Qun;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

import static com.wgq.sql.generator.CodeGenerator.generaCreateDDL;

@SpringBootApplication(scanBasePackages = {"com.wgq.*","com.sheep.*"})
@MapperScan("com.wgq.chat.contact.dao")
public class ContactApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactApplication.class, args);
        try {
            generaCreateDDL(Qun.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
