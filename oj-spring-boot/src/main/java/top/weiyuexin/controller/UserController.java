package top.weiyuexin.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import top.weiyuexin.config.JwtConfig;
import top.weiyuexin.pojo.User;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.pojo.vo.W;
import top.weiyuexin.service.UserService;
import top.weiyuexin.utils.Time;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource
    private JwtConfig jwtConfig;


    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public R getById(@PathVariable("id") Integer id) {
        return R.success(userService.getById(id));
    }

    /**
     * 分页查询
     *
     * @param page
     * @param limit
     * @param user
     * @return
     */
    @GetMapping("/list")
    public W getPage(@RequestParam("page") Integer page,
                     @RequestParam("limit") Integer limit,
                     User user) {
        IPage<User> Ipage = userService.getPage(page, limit, user);
        //如果当前页码值大于当前页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (page > Ipage.getPages()) {
            Ipage = userService.getPage(page, limit, user);
        }
        List<User> users = Ipage.getRecords();

        Ipage.setRecords(users);
        return new W(0, (int) Ipage.getTotal(), Ipage.getRecords());
    }

    @GetMapping("/rank")
    public W rank(@RequestParam("page") Integer page,
                  @RequestParam("limit") Integer limit,
                  User user) {
        IPage<User> Ipage = userService.rank(page, limit, user);
        //如果当前页码值大于当前页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (page > Ipage.getPages()) {
            Ipage = userService.getPage(page, limit, user);
        }
        List<User> users = Ipage.getRecords();

        Ipage.setRecords(users);
        return new W(0, (int) Ipage.getTotal(), Ipage.getRecords());
    }


    /**
     * @param user 用户信息
     * @param code 邮箱验证码
     * @return
     */
    @PostMapping("/register")
    public R register(User user, String code) {
        // 1、校验验证码是否正确
        String emailCodeInRedis = redisTemplate.opsForValue().get("emailCode:" + user.getEmail());
        if (emailCodeInRedis == null) {
            return R.error("验证码已过期，请重新发送!");
        }
        if (!Objects.equals(emailCodeInRedis, code)) {
            return R.error("验证码错误，请重试!");
        }
        // 2、判断用户名和邮箱是否已经存在
        if (userService.getByEmailOrName(user.getEmail(), user.getUsername()) > 0) {
            return R.error("用户名或邮箱已存在!");
        }

        // 3、保存到数据库
        user.setIntroduction("暂无介绍");
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        user.setRegisterTime(Time.CurrentTime());
        user.setPhoto("https://img.weiyuexin.top/img/picgo/2023/04/27/20230427183111.png");
        if (userService.save(user)) {
            return R.success("注册成功");
        } else {
            return R.error("注册失败,请重试");
        }
    }

    /**
     * 登录
     *
     * @param user 用户信息
     * @param code 邮箱验证码
     * @return
     */
    @GetMapping("/login")
    public R login(User user, String code, HttpServletResponse response) {
        // 1、校验验证码是否正确
        String emailCodeInRedis = redisTemplate.opsForValue().get("emailCode:" + user.getEmail());
        if (emailCodeInRedis == null) {
            return R.error("验证码已过期，请重新发送!");
        }
        if (!Objects.equals(emailCodeInRedis, code)) {
            return R.error("验证码错误，请重试!");
        }
        // 2、校验用户名和密码是否正确
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        User queriedUser = userService.getByNameAndPassword(user.getUsername(), user.getPassword());
        if (queriedUser == null) {
            return R.error("用户名或密码错误");
        }
        // 3、将用户登录session信息保存到Redis
        String token = jwtConfig.createToken(queriedUser.getUsername());
        redisTemplate.opsForValue().set("session:" + queriedUser.getId(), token, 60 * 60 * 24 * 7, TimeUnit.SECONDS);
        // 保存cookie
        Cookie cookie = new Cookie("user", queriedUser.getId().toString());
        response.addCookie(cookie);
        // 4、更新用户登录时间
        queriedUser.setAccessTime(Time.CurrentTime());
        if (!userService.updateById(queriedUser)) {
            return R.error("更新用户登录时间错误");
        }
        return R.success("登录成功");
    }


    /**
     * 退出登录
     *
     * @param id
     * @return
     */
    @GetMapping("/logout/{id}")
    public R logout(@PathVariable("id") Integer id) {
        //删除redis中session
        redisTemplate.opsForValue().getAndDelete("session:" + id);
        return R.success("退出登录成功");
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @PutMapping("")
    public R updateUser(User user) {
        return R.success(userService.updateById(user));
    }

    /**
     * 删除用户
     *
     * @return
     */
    @DeleteMapping("/{id}")
    public R deleteUser(@PathVariable("id") Integer id) {
        return R.success(userService.removeById(id), "删除成功");
    }
}
