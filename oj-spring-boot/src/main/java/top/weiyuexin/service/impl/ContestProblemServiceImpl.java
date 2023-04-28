package top.weiyuexin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.weiyuexin.mapper.ContestProblemMapper;
import top.weiyuexin.pojo.ContestProblem;
import top.weiyuexin.service.ContestProblemService;

import java.util.List;

@Service
public class ContestProblemServiceImpl extends ServiceImpl<ContestProblemMapper, ContestProblem> implements ContestProblemService {
    @Autowired
    private ContestProblemMapper contestProblemMapper;

    @Override
    public List<ContestProblem> getByContestId(Integer contestId) {
        LambdaQueryWrapper<ContestProblem> lqw = new LambdaQueryWrapper<>();
        lqw.orderByAsc(ContestProblem::getId);
        lqw.eq(ContestProblem::getContestId, contestId);
        List<ContestProblem> contestProblems = contestProblemMapper.selectList(lqw);
        return contestProblems;
    }
}
