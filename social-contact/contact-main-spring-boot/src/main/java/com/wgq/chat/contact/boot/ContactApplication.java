package com.wgq.chat.contact.boot;


import com.wgq.passport.dao.UserDao;
import com.wgq.passport.po.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.wgq.*","com.sheep.*"})
@MapperScan(basePackages = {"com.wgq.chat.contact.dao","com.wgq.passport.dao"})
public class ContactApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ContactApplication.class, args);
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        User wgq = userDao.getUserByUserName("wgq");
        System.out.println(wgq);
//        try {
//            generaCreateDDL(Qun.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
