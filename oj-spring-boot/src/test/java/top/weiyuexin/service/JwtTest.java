package top.weiyuexin.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.weiyuexin.Application;
import top.weiyuexin.config.JwtConfig;

import javax.annotation.Resource;

/**
 * @PackageName: top.weiyuexin.service
 * @ProjectName: oj-spring-boot
 * @ClassName: JwtTest
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/1 18:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JwtTest {
    @Resource
    private JwtConfig jwtConfig;

    @Test
    public void testJwt() {
        String token = jwtConfig.createToken("weiyuexin66666");
        System.out.println(token);
    }

    @Test
    public void testgetNameFromJwt() {
//        String usernameFromToken = jwtConfig.getUsernameFromToken(request.getHeader("token"));
        String usernameFromToken = jwtConfig.getUsernameFromToken("");
        System.out.println(usernameFromToken);
    }
}
