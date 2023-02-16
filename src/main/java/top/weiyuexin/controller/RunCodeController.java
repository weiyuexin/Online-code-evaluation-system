package top.weiyuexin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/java")
    public R runJava(String code){

        return R.success();
    }
}
