package top.weiyuexin.service.impl;

import org.springframework.stereotype.Service;
import top.weiyuexin.pojo.User;
import top.weiyuexin.service.UserService;

/**
 * @PackageName: top.weiyuexin.service.impl
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: UserServiceImpl
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/7 19:08
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findById(Integer id) {
        System.out.println("findById");
        return new User();
    }
}
