package top.weiyuexin.controller;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.weiyuexin.pojo.User;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.service.ArticleService;
import top.weiyuexin.service.ContestService;
import top.weiyuexin.service.ProblemService;
import top.weiyuexin.service.UserService;
import top.weiyuexin.utils.Time;

import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    private ProblemService problemService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    /**
     * 管理员登录
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/admin/login.do/{username}/{password}")
    @ResponseBody
    public R adminLogin(@PathVariable("username") String username,
                        @PathVariable("password") String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        // 2、校验用户名和密码是否正确
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        User queriedUser = userService.getByNameAndPasswordAndIsAdmin(user.getUsername(), user.getPassword());
        if (queriedUser == null) {
            return R.error("用户名或密码错误");
        }
        return R.success("登录成功");
    }

    /**
     * 统计
     *
     * @return
     */
    @GetMapping("/admin/count")
    @ResponseBody
    public R getCount() {
        Integer userCount = Math.toIntExact(userService.count());
        Integer ArticleCount = Math.toIntExact(articleService.count());
        Integer ProblemCount = Math.toIntExact(problemService.count());
        Integer ContestCount = Math.toIntExact(contestService.count());

        Map<String, Integer> map = new HashMap<>();
        map.put("UserCount", userCount);
        map.put("ArticleCount", ArticleCount);
        map.put("ProblemCount", ProblemCount);
        map.put("ContestCount", ContestCount);

        return R.success(map);
    }

    /**
     * 系统信息
     *
     * @return
     */
    @GetMapping("/system/info")
    public R getSystemInfo() {
        return R.success();
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @PostMapping("/user/add")
    @ResponseBody
    public R addUser(User user) {
        if (userService.getByEmailOrName(user.getEmail(), user.getUsername()) > 0) {
            return R.error("用户名或邮箱已存在!");
        }
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        user.setRegisterTime(Time.CurrentTime());
        user.setPhoto("https://img.weiyuexin.top/img/picgo/2023/04/27/20230427183111.png");
        if (userService.save(user)) {
            return R.success("用户添加成功");
        } else {
            return R.error("用户添加失败,请重试");
        }
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @PutMapping("/user/edit")
    @ResponseBody
    public R editUser(User user) {
        if (userService.updateById(user)) {
            return R.success("修改成功");
        } else {
            return R.error("修改失败,请重试");
        }
    }
}
