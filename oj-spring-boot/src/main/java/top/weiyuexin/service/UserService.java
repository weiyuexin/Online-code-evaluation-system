package top.weiyuexin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.weiyuexin.pojo.User;

import java.util.List;

/**
 * @PackageName: top.weiyuexin.service
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: UserService
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/7 19:07
 */

public interface UserService extends IService<User> {
    Integer getByEmailOrName(String email, String username);

    User getByNameAndPassword(String username, String password);

    User getByNameAndPasswordAndIsAdmin(String username, String password);

    IPage<User> getPage(Integer currentPage, Integer pageSize, User user);

    IPage<User> rank(Integer currentPage, Integer pageSize, User user);

    List<User> indexRank(Integer num);
}
