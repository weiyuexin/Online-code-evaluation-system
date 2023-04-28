package top.weiyuexin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {
    //mvn spring-boot:run 根目录下运行
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
