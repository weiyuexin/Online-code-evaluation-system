package top.weiyuexin.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.weiyuexin.pojo.Code;
import top.weiyuexin.pojo.FilePath;
import top.weiyuexin.pojo.TestCase;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.service.CodeService;
import top.weiyuexin.utils.FileUtils;
import top.weiyuexin.utils.Time;

import java.util.ArrayList;

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
    public R runC(String code, Integer problemId, Integer userId, String language) {
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
        code1.setLanguage(language);
        if (!codeService.save(code1)) {
            return R.error("代码保存到数据库时发送错误");
        }
        // 2、编译代码
        R r = codeService.compileC(code1);
        if (r.getCode() == 400) {
            //编译发送错误
            return r;
        }
        // 3、运行代码，测试测试用例
        // 获取测试用例
        ArrayList<TestCase> testCases = new ArrayList<>();
        TestCase testCase = new TestCase();
        testCase.setInput("2 4");
        r = codeService.runC(code1, testCase);
        if (r.getCode() == 400) {
            return r;
        }
        System.out.println(r.getData());

        // 4、返回运行结果
        return R.success("通过");
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
