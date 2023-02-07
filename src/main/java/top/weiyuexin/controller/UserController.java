package top.weiyuexin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.weiyuexin.pojo.User;
import top.weiyuexin.service.UserService;

/**
 * @PackageName: top.weiyuexin.controller
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: UserController
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/7 21:21
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/find")
    @ResponseBody
    public String findById(Integer id){
        User user = userService.findById(1);
        return "hello";
    }
}
