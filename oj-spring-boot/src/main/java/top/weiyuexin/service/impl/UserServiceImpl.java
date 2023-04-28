package top.weiyuexin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.weiyuexin.mapper.UserMapper;
import top.weiyuexin.pojo.User;
import top.weiyuexin.service.UserService;

import java.util.List;

/**
 * @PackageName: top.weiyuexin.service.impl
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: UserServiceImpl
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/7 19:08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 判断用户名和邮箱是否已经存在
     *
     * @param email
     * @param username
     * @return
     */
    @Override
    public Integer getByEmailOrName(String email, String username) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getEmail, email).or().eq(User::getUsername, username);
        return Math.toIntExact(userMapper.selectCount(lqw));
    }

    @Override
    public User getByNameAndPassword(String username, String password) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, username).eq(User::getPassword, password);
        return userMapper.selectOne(lqw);
    }

    @Override
    public User getByNameAndPasswordAndIsAdmin(String username, String password) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, username).eq(User::getPassword, password);
        lqw.eq(User::getIsAdmin, 1);
        return userMapper.selectOne(lqw);
    }

    @Override
    public IPage<User> getPage(Integer currentPage, Integer pageSize, User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(User::getId);
        //如果username不为空，则查询
        lqw.like(Strings.isNotEmpty(user.getUsername()), User::getUsername, user.getUsername());
        lqw.like(Strings.isNotEmpty(user.getEmail()), User::getEmail, user.getEmail());
        lqw.like(Strings.isNotEmpty(user.getSex()), User::getSex, user.getSex());
        IPage<User> page = new Page<>(currentPage, pageSize);
        userMapper.selectPage(page, lqw);
        return page;
    }

    /**
     * 按照解决题数进行排序
     *
     * @param currentPage
     * @param pageSize
     * @param user
     * @return
     */
    @Override
    public IPage<User> rank(Integer currentPage, Integer pageSize, User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(User::getSolvedNum);
        //如果username不为空，则查询
        lqw.like(Strings.isNotEmpty(user.getUsername()), User::getUsername, user.getUsername());
        lqw.like(Strings.isNotEmpty(user.getEmail()), User::getEmail, user.getEmail());
        lqw.like(Strings.isNotEmpty(user.getSex()), User::getSex, user.getSex());
        lqw.eq(User::getIsAdmin, 0);
        IPage<User> page = new Page<>(currentPage, pageSize);
        userMapper.selectPage(page, lqw);
        return page;
    }

    @Override
    public List<User> indexRank(Integer num) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(User::getSolvedNum);
        lqw.last("limit " + num);
        return userMapper.selectList(lqw);
    }
}
