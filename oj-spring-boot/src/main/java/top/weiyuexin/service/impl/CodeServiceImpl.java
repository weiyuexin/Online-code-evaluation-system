package top.weiyuexin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.weiyuexin.mapper.CodeMapper;
import top.weiyuexin.pojo.Code;
import top.weiyuexin.pojo.TestCase;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.service.CodeService;

/**
 * @PackageName: top.weiyuexin.service.impl
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: CodeServiceImpl
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/16 21:03
 */
@Service
public class CodeServiceImpl extends ServiceImpl<CodeMapper, Code> implements CodeService {
    @Override
    public R compileJava(Code code) {
        return null;
    }

    @Override
    public R runJava(Code code, TestCase testCase) {
        return null;
    }

    @Override
    public R compileCpp(Code code) {
        return null;
    }

    @Override
    public R runCpp(Code code, TestCase testCase) {
        return null;
    }

    @Override
    public R compileC(Code code) {
        return null;
    }

    @Override
    public R runC(Code code, TestCase testCase) {
        return null;
    }

    @Override
    public R compileGolang(Code code) {
        return null;
    }

    @Override
    public R runGolang(Code code, TestCase testCase) {
        return null;
    }

    @Override
    public R runPython3(Code code, TestCase testCase) {
        return null;
    }

    @Override
    public R runJavaScript(Code code, TestCase testCase) {
        return null;
    }
}
