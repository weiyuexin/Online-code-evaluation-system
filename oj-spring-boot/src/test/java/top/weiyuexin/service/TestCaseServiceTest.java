package top.weiyuexin.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.weiyuexin.Application;
import top.weiyuexin.pojo.TestCase;

/**
 * @PackageName: top.weiyuexin.service
 * @ProjectName: oj-spring-boot
 * @ClassName: TestCaseServiceTest
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/8 1:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestCaseServiceTest {
    @Autowired
    private TestCaseService testCaseService;

    @Test
    public void test() {
        TestCase testCase = new TestCase();
        testCase.setProblemId(2);
        testCase.setInput("2 3\n");
        testCase.setOutput("3");
        testCaseService.save(testCase);
    }
}
