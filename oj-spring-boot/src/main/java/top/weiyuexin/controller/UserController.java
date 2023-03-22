package top.weiyuexin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.weiyuexin.pojo.Article;
import top.weiyuexin.pojo.User;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * @PackageName: top.weiyuexin.controller
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: UserController
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/7 21:21
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public R findById(@PathVariable("id") Integer id){
        return R.success(userService.getById(id));
    }

    /**
     * 分页查询
     * @param start
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public R getPage(Integer start, Integer pageSize){
        return R.success();
    }


    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public R register(User user){
        return R.success();
    }

    /**
     * 登录
     * @param user
     * @param session
     * @return
     */
    @GetMapping("/login")
    public R login(User user,HttpSession session){
        return R.success();
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PutMapping("/update")
    public R updateUser(User user){
        return R.success(userService.updateById(user));
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    @DeleteMapping("/delete")
    public R deleteArticle(User user){
        return R.success(userService.removeById(user));
    }
}
