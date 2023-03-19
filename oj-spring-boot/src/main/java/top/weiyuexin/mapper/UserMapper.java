package top.weiyuexin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.weiyuexin.pojo.User;

/**
 * @PackageName: top.weiyuexin.mapper
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: UserMapper
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/7 19:07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
