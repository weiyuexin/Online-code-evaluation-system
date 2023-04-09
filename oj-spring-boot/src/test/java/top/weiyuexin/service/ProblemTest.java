package top.weiyuexin.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.weiyuexin.Application;
import top.weiyuexin.mapper.ProblemMapper;
import top.weiyuexin.pojo.Problem;

/**
 * @PackageName: top.weiyuexin.service
 * @ProjectName: oj-spring-boot
 * @ClassName: ProblemTest
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/10 1:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProblemTest {

    @Autowired
    private ProblemService problemService;
    @Autowired
    private ProblemMapper problemMapper;

    @Test
    public void test() {
        Problem problem = new Problem();
        problem.setUserId(2);
        problem.setTitle("222");
        problem.setDescription("2e3rdfsds");
//        problemService.save(problem);
        Problem problem1 = problemMapper.selectById(3);
        System.out.println(problem1.toString());
    }
}
