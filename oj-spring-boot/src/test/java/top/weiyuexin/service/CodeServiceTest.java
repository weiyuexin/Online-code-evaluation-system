package top.weiyuexin.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.weiyuexin.Application;
import top.weiyuexin.pojo.Code;
import top.weiyuexin.utils.Time;

/**
 * @PackageName: top.weiyuexin.service
 * @ProjectName: oj-spring-boot
 * @ClassName: CodeServiceTest
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/8 1:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CodeServiceTest {
    @Autowired
    private CodeService codeService;

    @Test
    public void test() {
        Code code = new Code();
        code.setProblemId(2);
        code.setUserId(1);
        code.setCodePath("/data/code/java/1/uuid/main.java");
        code.setLanguage("C");
        code.setCreateTime(Time.CurrentTime());
        codeService.save(code);
    }
}
