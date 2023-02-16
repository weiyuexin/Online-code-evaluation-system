package top.weiyuexin.service;

import top.weiyuexin.pojo.User;
import top.weiyuexin.pojo.vo.R;

/**
 * @PackageName: top.weiyuexin.service
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: CodeService
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/16 21:01
 */

public interface CodeService {
    public R runJava(String code, User user);
    public R runCpp(String code, User user);
    public R runPython(String code,User user);
}
