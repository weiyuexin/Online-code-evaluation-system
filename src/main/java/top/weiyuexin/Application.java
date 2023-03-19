package top.weiyuexin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
//mvn spring-boot:run 根目录下运行
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
