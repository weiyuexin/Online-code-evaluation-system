package top.weiyuexin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.weiyuexin.pojo.Problem;

/**
 * @PackageName: top.weiyuexin.mapper
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: ProblemMapper
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/7 21:22
 */
@Mapper
public interface ProblemMapper extends BaseMapper<Problem> {
}
