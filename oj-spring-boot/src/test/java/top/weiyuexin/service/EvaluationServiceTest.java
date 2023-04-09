package top.weiyuexin.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.weiyuexin.Application;
import top.weiyuexin.pojo.Evaluation;
import top.weiyuexin.utils.Time;

/**
 * @PackageName: top.weiyuexin.service
 * @ProjectName: oj-spring-boot
 * @ClassName: EvaluationServiceTest
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/8 1:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EvaluationServiceTest {
    @Autowired
    private EvaluationService evaluationService;

    @Test
    public void test() {
        Evaluation evaluation = new Evaluation();
        evaluation.setUserId(1);
        evaluation.setProblemId(2);
        evaluation.setCreateTime(Time.CurrentTime());
        evaluation.setLanguage("C");
        evaluation.setPassedTestCaseNum(3);
        evaluation.setAllTestCaseNum(10);
        evaluation.setIsPassed(1);
        evaluationService.save(evaluation);
    }
}
