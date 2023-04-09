package top.weiyuexin.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.weiyuexin.pojo.*;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.service.*;
import top.weiyuexin.utils.FileUtils;
import top.weiyuexin.utils.Time;

import java.util.List;

/**
 * @PackageName: top.weiyuexin.controller
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: RunCodeController
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/16 21:05
 */
@RestController
@RequestMapping("/code")
public class RunCodeController {
    @Autowired
    private CodeService codeService;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private TestCaseService testCaseService;
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private UserService userService;

    /**
     * 编译运行Java代码
     *
     * @param code
     * @return
     */
    @PostMapping("/java")
    public R runJava(String code) {

        return R.success();
    }

    /**
     * 编译运行C代码
     *
     * @param code
     * @return
     */
    @PostMapping("/c")
    public R runC(String code, Integer problemId, Integer userId) {
        // 获取用户信息
        User user = userService.getById(userId);
        user.setSubmitNum(user.getSubmitNum() + 1);
        // 读取题目详情
        Problem problem = problemService.getById(problemId);
        problem.setSubmitNum(problem.getSubmitNum() + 1);
        // new一次判题记录
        Evaluation evaluation = new Evaluation();
        evaluation.setProblemId(problemId);
        evaluation.setUserId(userId);
        evaluation.setCreateTime(Time.CurrentTime());
        evaluation.setLanguage("C");
        evaluation.setPassedTestCaseNum(0);

        // 1、先将代码保存到服务器和数据库
        String UUID = IdUtil.simpleUUID();
        boolean b = FileUtils.WriteToFile(FilePath.C.getPath() + problemId + "/" + UUID + "/" + "main.c", code);
        if (!b) {
            return R.error("代码写入文件失败");
        }
        Code code1 = new Code();
        code1.setCodePath(FilePath.C.getPath() + problemId + "/" + UUID + "/");
        code1.setCreateTime(Time.CurrentTime());
        code1.setUserId(userId);
        code1.setProblemId(problemId);
        code1.setLanguage("C");
        if (!codeService.save(code1)) {
            return R.error("代码保存到数据库时发生错误");
        }
        // 2、编译代码
        R r = codeService.compileC(code1);
        if (r.getCode() == 400) {
            // 将错误信息记录
            evaluation.setError(r.getData().toString());
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            //编译发送错误
            return r;
        }
        // 3、运行代码，测试测试用例

        // 获取测试用例
        List<TestCase> testCaseList = testCaseService.getByProblemId(problemId);
        evaluation.setAllTestCaseNum(testCaseList.size());

        // 循环测试所以测试用例
        for (int i = 0; i < testCaseList.size(); i++) {
            r = codeService.runC(code1, testCaseList.get(i));
            if (r.getCode() == 400) {
                evaluation.setError("测试用例未通过");
                break;
            }
            // 通过的用例数加一
            evaluation.setPassedTestCaseNum(evaluation.getPassedTestCaseNum() + 1);
            System.out.println(r.getData());
        }

        // 4、判断所以测试用例是否已经全部通过
        if (evaluation.getPassedTestCaseNum() == evaluation.getAllTestCaseNum()) {
            evaluation.setIsPassed(1);
            user.setSolvedNum(user.getSolvedNum() + 1);
            problem.setSolvedNum(problem.getSolvedNum() + 1);

            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.success("通过");
        } else {
            evaluation.setIsPassed(0);
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.error("未通过");
        }
    }

    /**
     * 编译运行C++代码
     *
     * @param code
     * @return
     */
    @PostMapping("/cpp")
    public R runCpp(String code) {
        return R.success();
    }


    /**
     * 运行python3代码
     *
     * @param code
     * @return
     */
    @PostMapping("/python3")
    public R runPython3(String code) {
        return R.success();
    }

    /**
     * 编译运行Golang代码
     *
     * @param code
     * @return
     */
    @PostMapping("/golang")
    public R runGolang(String code) {
        return R.success();
    }
}
