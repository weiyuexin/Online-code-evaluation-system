package top.weiyuexin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.weiyuexin.pojo.Contest;

/**
 * @PackageName: top.weiyuexin.mapper
 * @ProjectName: oj-spring-boot
 * @ClassName: ContestMapper
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/19 14:58
 */
@Mapper
public interface ContestMapper extends BaseMapper<Contest> {
}
