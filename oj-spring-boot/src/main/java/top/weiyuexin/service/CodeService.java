package top.weiyuexin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.weiyuexin.pojo.Code;
import top.weiyuexin.pojo.TestCase;
import top.weiyuexin.pojo.vo.R;

/**
 * @PackageName: top.weiyuexin.service
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: CodeService
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/16 21:01
 */

public interface CodeService extends IService<Code> {
    public R compileJava(Code code);

    public R runJava(Code code, TestCase testCase);

    public R compileCpp(Code code);

    public R runCpp(Code code, TestCase testCase);

    public R compileC(Code code);

    public R runC(Code code, TestCase testCase);

    public R compileGolang(Code code);

    public R runGolang(Code code, TestCase testCase);

    public R runPython(Code code, TestCase testCase);

    public R runJavaScript(Code code, TestCase testCase);
}
