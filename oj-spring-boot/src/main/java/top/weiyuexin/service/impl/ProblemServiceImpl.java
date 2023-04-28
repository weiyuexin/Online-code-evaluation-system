package top.weiyuexin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.weiyuexin.mapper.ProblemMapper;
import top.weiyuexin.pojo.Problem;
import top.weiyuexin.service.ProblemService;

import java.util.List;

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

    @Override
    public IPage<Problem> getPage(Integer currentPage, Integer pageSize, Problem problem) {
        LambdaQueryWrapper<Problem> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Problem::getId);
        //如果不为空，则查询
        lqw.like(Strings.isNotEmpty(problem.getTitle()), Problem::getTitle, problem.getTitle());
        lqw.like(Strings.isNotEmpty(problem.getDescription()), Problem::getDescription, problem.getDescription());
        lqw.like(Strings.isNotEmpty(problem.getDifficulty()), Problem::getDifficulty, problem.getDifficulty());
        IPage<Problem> page = new Page<>(currentPage, pageSize);
        problemMapper.selectPage(page, lqw);
        return page;
    }


    /**
     * 获取首页题目排行
     *
     * @return
     */
    @Override
    public List<Problem> hotProblems(Integer num) {
        LambdaQueryWrapper<Problem> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Problem::getSubmitNum);
        lqw.last("limit " + num);
        List<Problem> problems = problemMapper.selectList(lqw);
        return problems;
    }
}
