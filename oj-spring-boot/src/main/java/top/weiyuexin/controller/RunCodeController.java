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
    public R runJava(String code, Integer problemId, Integer userId) {
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
        evaluation.setLanguage("Java");
        evaluation.setPassedTestCaseNum(0);
        // 1、先将代码保存到服务器和数据库
        String UUID = IdUtil.simpleUUID();
        boolean b = FileUtils.WriteToFile(FilePath.JAVA.getPath() + problemId + "/" + UUID + "/" + "Main.java", code);
        if (!b) {
            return R.error("代码写入文件失败");
        }
        Code code1 = new Code();
        code1.setCodePath(FilePath.JAVA.getPath() + problemId + "/" + UUID + "/");
        code1.setCreateTime(Time.CurrentTime());
        code1.setUserId(userId);
        code1.setProblemId(problemId);
        code1.setLanguage("Java");
        if (!codeService.save(code1)) {
            return R.error("代码保存到数据库时发生错误");
        }
        // 2、编译代码
        R r = codeService.compileJava(code1);
        if (r.getCode() == 400) {
            //编译发送错误
            evaluation.setError(r.getData().toString());
            evaluation.setStatus("Compile Error");
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.error(evaluation, "编译失败");
        }
        // 3、运行代码，测试测试用例
        // 获取测试用例
        List<TestCase> testCaseList = testCaseService.getByProblemId(problemId);
        evaluation.setAllTestCaseNum(testCaseList.size());
        // 循环测试所有测试用例
        for (int i = 0; i < testCaseList.size(); i++) {
            r = codeService.runJava(code1, testCaseList.get(i));
            if (r.getCode() == 400 || !r.getData().toString().equals(testCaseList.get(i).getOutput())) {
                evaluation.setError("测试用例未通过");
                break;
            }
            // 通过的用例数加一
            evaluation.setPassedTestCaseNum(evaluation.getPassedTestCaseNum() + 1);
        }
        // 4、判断所以测试用例是否已经全部通过
        if (evaluation.getPassedTestCaseNum() == evaluation.getAllTestCaseNum()) {
            // 通过全部测评用例
            evaluation.setIsPassed(1);
            evaluation.setStatus("Accepted");
            user.setSolvedNum(user.getSolvedNum() + 1);
            problem.setSolvedNum(problem.getSolvedNum() + 1);

            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.success(evaluation, "通过");
        } else if (evaluation.getPassedTestCaseNum() > 0) {
            //通过部分测评用例
            evaluation.setIsPassed(0);
            evaluation.setStatus("Partial Accepted");
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.error(evaluation, "测试用例未全部通过");
        } else {
            //没有通过任何测评用例
            evaluation.setIsPassed(0);
            evaluation.setStatus("Wrong Answer");
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.error(evaluation, "答案错误");
        }
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
            evaluation.setStatus("Compile Error");
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            //编译发送错误
            return R.error(evaluation, "编译失败");
        }
        // 3、运行代码，测试测试用例
        // 获取测试用例
        List<TestCase> testCaseList = testCaseService.getByProblemId(problemId);
        evaluation.setAllTestCaseNum(testCaseList.size());
        // 循环测试所有测试用例
        for (int i = 0; i < testCaseList.size(); i++) {
            r = codeService.runC(code1, testCaseList.get(i));
            if (r.getCode() == 400 || !r.getData().toString().equals(testCaseList.get(i).getOutput())) {
                evaluation.setError("测试用例未通过");
                break;
            }
            // 通过的用例数加一
            evaluation.setPassedTestCaseNum(evaluation.getPassedTestCaseNum() + 1);
        }
        // 4、判断所以测试用例是否已经全部通过
        if (evaluation.getPassedTestCaseNum() == evaluation.getAllTestCaseNum()) {
            // 题目通过
            evaluation.setIsPassed(1);
            evaluation.setStatus("Accepted");
            user.setSolvedNum(user.getSolvedNum() + 1);
            problem.setSolvedNum(problem.getSolvedNum() + 1);

            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.success(evaluation, "通过");
        } else if (evaluation.getPassedTestCaseNum() > 0) {
            //通过部分测评用例
            evaluation.setIsPassed(0);
            evaluation.setStatus("Partial Accepted");
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.error(evaluation, "测试用例未全部通过");
        } else {
            //答案错误，没有通过任何测评用例
            evaluation.setIsPassed(0);
            evaluation.setStatus("Wrong Answer");
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.error(evaluation, "答案错误");
        }
    }

    /**
     * 编译运行C++代码
     *
     * @param code
     * @return
     */
    @PostMapping("/cpp")
    public R runCpp(String code, Integer problemId, Integer userId) {
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
        evaluation.setLanguage("C++");
        evaluation.setPassedTestCaseNum(0);

        // 1、先将代码保存到服务器和数据库
        String UUID = IdUtil.simpleUUID();
        boolean b = FileUtils.WriteToFile(FilePath.CPP.getPath() + problemId + "/" + UUID + "/" + "main.cpp", code);
        if (!b) {
            return R.error("代码写入文件失败");
        }
        Code code1 = new Code();
        code1.setCodePath(FilePath.CPP.getPath() + problemId + "/" + UUID + "/");
        code1.setCreateTime(Time.CurrentTime());
        code1.setUserId(userId);
        code1.setProblemId(problemId);
        code1.setLanguage("C++");
        if (!codeService.save(code1)) {
            return R.error("代码保存到数据库时发生错误");
        }
        // 2、编译代码
        R r = codeService.compileCpp(code1);
        if (r.getCode() == 400) {
            // 将错误信息记录
            evaluation.setError(r.getData().toString());
            evaluation.setStatus("Compile Error");
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            //编译发送错误
            return R.error(evaluation, "编译失败");
        }
        // 3、运行代码，测试测试用例

        // 获取测试用例
        List<TestCase> testCaseList = testCaseService.getByProblemId(problemId);
        evaluation.setAllTestCaseNum(testCaseList.size());

        // 循环测试所有测试用例
        for (int i = 0; i < testCaseList.size(); i++) {
            r = codeService.runCpp(code1, testCaseList.get(i));
            if (r.getCode() == 400 || !r.getData().toString().equals(testCaseList.get(i).getOutput())) {
                evaluation.setError("测试用例未通过");
                break;
            }
            // 通过的用例数加一
            evaluation.setPassedTestCaseNum(evaluation.getPassedTestCaseNum() + 1);
        }

        // 4、判断所以测试用例是否已经全部通过
        if (evaluation.getPassedTestCaseNum() == evaluation.getAllTestCaseNum()) {
            evaluation.setIsPassed(1);
            evaluation.setStatus("Accepted");
            user.setSolvedNum(user.getSolvedNum() + 1);
            problem.setSolvedNum(problem.getSolvedNum() + 1);

            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.success(evaluation, "通过");
        } else if (evaluation.getPassedTestCaseNum() > 0) {
            evaluation.setIsPassed(0);
            evaluation.setStatus("Partial Accepted");
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.error(evaluation, "测试用例未全部通过");
        } else {
            evaluation.setIsPassed(0);
            evaluation.setStatus("Wrong Answer");
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.error(evaluation, "答案错误");
        }
    }


    /**
     * 运行python3代码
     *
     * @param code
     * @return
     */
    @PostMapping("/python")
    public R runPython(String code, Integer problemId, Integer userId) {
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
        evaluation.setLanguage("Python");
        evaluation.setPassedTestCaseNum(0);

        // 1、先将代码保存到服务器和数据库
        String UUID = IdUtil.simpleUUID();
        boolean b = FileUtils.WriteToFile(FilePath.PYTHON.getPath() + problemId + "/" + UUID + "/" + "main.py", code);
        if (!b) {
            return R.error("代码写入文件失败");
        }
        Code code1 = new Code();
        code1.setCodePath(FilePath.PYTHON.getPath() + problemId + "/" + UUID + "/");
        code1.setCreateTime(Time.CurrentTime());
        code1.setUserId(userId);
        code1.setProblemId(problemId);
        code1.setLanguage("Python");
        if (!codeService.save(code1)) {
            return R.error("代码保存到数据库时发生错误");
        }
        // 3、运行代码，测试测试用例

        // 获取测试用例
        List<TestCase> testCaseList = testCaseService.getByProblemId(problemId);
        evaluation.setAllTestCaseNum(testCaseList.size());

        // 循环测试所有测试用例
        for (int i = 0; i < testCaseList.size(); i++) {
            R r = codeService.runPython(code1, testCaseList.get(i));
            if (r.getCode() == 400 || !r.getData().toString().equals(testCaseList.get(i).getOutput())) {
                evaluation.setError("测试用例未通过");
                break;
            }
            // 通过的用例数加一
            evaluation.setPassedTestCaseNum(evaluation.getPassedTestCaseNum() + 1);
        }

        // 4、判断所以测试用例是否已经全部通过
        if (evaluation.getPassedTestCaseNum() == evaluation.getAllTestCaseNum()) {
            evaluation.setIsPassed(1);
            evaluation.setStatus("Accepted");
            user.setSolvedNum(user.getSolvedNum() + 1);
            problem.setSolvedNum(problem.getSolvedNum() + 1);

            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.success(evaluation, "通过");
        } else if (evaluation.getPassedTestCaseNum() > 0) {
            evaluation.setIsPassed(0);
            evaluation.setStatus("Partial Accepted");
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.error(evaluation, "测试用例未全部通过");
        } else {
            evaluation.setIsPassed(0);
            evaluation.setStatus("Wrong Answer");
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.error(evaluation, "答案错误");
        }
    }

    /**
     * 编译运行Golang代码
     *
     * @param code
     * @return
     */
    @PostMapping("/go")
    public R runGolang(String code, Integer problemId, Integer userId) {
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
        evaluation.setLanguage("Golang");
        evaluation.setPassedTestCaseNum(0);

        // 1、先将代码保存到服务器和数据库
        String UUID = IdUtil.simpleUUID();
        boolean b = FileUtils.WriteToFile(FilePath.GOLANG.getPath() + problemId + "/" + UUID + "/" + "main.go", code);
        if (!b) {
            return R.error("代码写入文件失败");
        }
        Code code1 = new Code();
        code1.setCodePath(FilePath.GOLANG.getPath() + problemId + "/" + UUID + "/");
        code1.setCreateTime(Time.CurrentTime());
        code1.setUserId(userId);
        code1.setProblemId(problemId);
        code1.setLanguage("Golang");
        if (!codeService.save(code1)) {
            return R.error("代码保存到数据库时发生错误");
        }
        // 2、编译代码
        R r = codeService.compileGolang(code1);
        if (r.getCode() == 400) {
            // 将错误信息记录
            evaluation.setError(r.getData().toString());
            evaluation.setStatus("Compile Error");
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            //编译发送错误
            return R.error(evaluation, "编译失败");
        }
        // 3、运行代码，测试测试用例

        // 获取测试用例
        List<TestCase> testCaseList = testCaseService.getByProblemId(problemId);
        evaluation.setAllTestCaseNum(testCaseList.size());

        // 循环测试所有测试用例
        for (int i = 0; i < testCaseList.size(); i++) {
            r = codeService.runGolang(code1, testCaseList.get(i));
            if (r.getCode() == 400 || !r.getData().toString().equals(testCaseList.get(i).getOutput())) {
                evaluation.setError("测试用例未通过");
                break;
            }
            // 通过的用例数加一
            evaluation.setPassedTestCaseNum(evaluation.getPassedTestCaseNum() + 1);
        }

        // 4、判断所以测试用例是否已经全部通过
        if (evaluation.getPassedTestCaseNum() == evaluation.getAllTestCaseNum()) {
            evaluation.setIsPassed(1);
            evaluation.setStatus("Accepted");
            user.setSolvedNum(user.getSolvedNum() + 1);
            problem.setSolvedNum(problem.getSolvedNum() + 1);

            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.success(evaluation, "通过");
        } else if (evaluation.getPassedTestCaseNum() > 0) {
            evaluation.setIsPassed(0);
            evaluation.setStatus("Partial Accepted");
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.error(evaluation, "测试用例未全部通过");
        } else {
            evaluation.setIsPassed(0);
            evaluation.setStatus("Wrong Answer");
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.error(evaluation, "答案错误");
        }
    }

    @PostMapping("/js")
    public R runJavaScript(String code, Integer problemId, Integer userId) {
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
        evaluation.setLanguage("JavaScript");
        evaluation.setPassedTestCaseNum(0);

        // 1、先将代码保存到服务器和数据库
        String UUID = IdUtil.simpleUUID();
        boolean b = FileUtils.WriteToFile(FilePath.JAVASCRIPT.getPath() + problemId + "/" + UUID + "/" + "main.js", code);
        if (!b) {
            return R.error("代码写入文件失败");
        }
        Code code1 = new Code();
        code1.setCodePath(FilePath.JAVASCRIPT.getPath() + problemId + "/" + UUID + "/");
        code1.setCreateTime(Time.CurrentTime());
        code1.setUserId(userId);
        code1.setProblemId(problemId);
        code1.setLanguage("JavaScript");
        if (!codeService.save(code1)) {
            return R.error("代码保存到数据库时发生错误");
        }
        // 3、运行代码，测试测试用例

        // 获取测试用例
        List<TestCase> testCaseList = testCaseService.getByProblemId(problemId);
        evaluation.setAllTestCaseNum(testCaseList.size());

        // 循环测试所有测试用例
        for (int i = 0; i < testCaseList.size(); i++) {
            R r = codeService.runJavaScript(code1, testCaseList.get(i));
            if (r.getCode() == 400 || !r.getData().toString().equals(testCaseList.get(i).getOutput())) {
                evaluation.setError("测试用例未通过");
                break;
            }
            // 通过的用例数加一
            evaluation.setPassedTestCaseNum(evaluation.getPassedTestCaseNum() + 1);
        }

        // 4、判断所以测试用例是否已经全部通过
        if (evaluation.getPassedTestCaseNum() == evaluation.getAllTestCaseNum()) {
            evaluation.setIsPassed(1);
            evaluation.setStatus("Accepted");
            user.setSolvedNum(user.getSolvedNum() + 1);
            problem.setSolvedNum(problem.getSolvedNum() + 1);

            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.success(evaluation, "通过");
        } else if (evaluation.getPassedTestCaseNum() > 0) {
            evaluation.setIsPassed(0);
            evaluation.setStatus("Partial Accepted");
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.error(evaluation, "测试用例未全部通过");
        } else {
            evaluation.setIsPassed(0);
            evaluation.setStatus("Wrong Answer");
            evaluationService.save(evaluation);
            userService.updateById(user);
            problemService.updateById(problem);
            return R.error(evaluation, "答案错误");
        }
    }
}
