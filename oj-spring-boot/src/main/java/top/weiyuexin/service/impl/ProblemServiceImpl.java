package top.weiyuexin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.weiyuexin.mapper.ProblemMapper;
import top.weiyuexin.pojo.Problem;
import top.weiyuexin.service.ProblemService;

/**
 * @PackageName: top.weiyuexin.service.impl
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: ProblemServiceImpl
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/7 21:27
 */
@Service
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements ProblemService {
    @Autowired
    private ProblemMapper problemMapper;
}
