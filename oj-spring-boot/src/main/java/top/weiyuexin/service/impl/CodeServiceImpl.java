package top.weiyuexin.service.impl;

import org.springframework.stereotype.Service;
import top.weiyuexin.pojo.User;
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
public class CodeServiceImpl implements CodeService {
    /**
     * 运行Java代码
     * @param code 代码
     * @param user 用户
     * @return
     */
    @Override
    public R runJava(String code, User user) {
        return null;
    }

    /**
     * 运行c/c++代码
     * @param code 代码
     * @param user 用户
     * @return
     */
    @Override
    public R runCpp(String code, User user) {
        return null;
    }

    /**
     * 运行Python代码
     * @param code 代码
     * @param user 用户
     * @return
     */
    @Override
    public R runPython(String code, User user) {
        return null;
    }
}
