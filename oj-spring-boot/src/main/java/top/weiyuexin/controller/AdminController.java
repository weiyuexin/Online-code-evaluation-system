package top.weiyuexin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import top.weiyuexin.service.UserService;

/**
 * @PackageName: top.weiyuexin.controller
 * @ProjectName: oj-spring-boot
 * @ClassName: AdminController
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/10 21:35
 */
@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
}
