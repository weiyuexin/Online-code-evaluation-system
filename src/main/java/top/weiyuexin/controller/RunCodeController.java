package top.weiyuexin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.service.CodeService;

/**
 * @PackageName: top.weiyuexin.controller
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: RunCodeController
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/16 21:05
 */
@Controller
@RequestMapping("/code")
public class RunCodeController {
    @Autowired
    private CodeService codeService;

    /**
     * 编译运行Java代码
     * @param code
     * @return
     */
    @PostMapping("/java")
    public R runJava(String code){

        return R.success();
    }

    /**
     * 编译运行C/C++代码
     * @param code
     * @return
     */
    @PostMapping("/cpp")
    public R runCpp(String code){
        return R.success();
    }

    /**
     * 运行python2代码
     * @param code
     * @return
     */
    @PostMapping("/python2")
    public R runPython2(String code){
        return R.success();
    }

    /**
     * 运行python3代码
     * @param code
     * @return
     */
    @PostMapping("/python3")
    public R runPython3(String code){
        return R.success();
    }

    /**
     * 编译运行Golang代码
     * @param code
     * @return
     */
    @PostMapping("/golang")
    public R runGolang(String code){
        return R.success();
    }
}
