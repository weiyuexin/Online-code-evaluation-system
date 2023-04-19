package top.weiyuexin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.weiyuexin.mapper.EvaluationMapper;
import top.weiyuexin.pojo.Evaluation;
import top.weiyuexin.service.EvaluationService;

/**
 * @PackageName: top.weiyuexin.service.impl
 * @ProjectName: oj-spring-boot
 * @ClassName: EvaluationServiceImpl
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/8 1:29
 */
@Service
public class EvaluationServiceImpl extends ServiceImpl<EvaluationMapper, Evaluation> implements EvaluationService {
    @Autowired
    private EvaluationMapper evaluationMapper;

    @Override
    public IPage<Evaluation> getPage(Integer currentPage, Integer pageSize, Evaluation evaluation) {
        LambdaQueryWrapper<Evaluation> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Evaluation::getId);
        //如果不为空，则查询
        lqw.like(evaluation.getUserId() != null, Evaluation::getUserId, evaluation.getUserId());
        lqw.like(evaluation.getProblemId() != null, Evaluation::getProblemId, evaluation.getProblemId());
        lqw.like(evaluation.getLanguage() != null, Evaluation::getLanguage, evaluation.getLanguage());
        lqw.like(evaluation.getIsPassed() != null, Evaluation::getIsPassed, evaluation.getIsPassed());
        IPage<Evaluation> page = new Page<>(currentPage, pageSize);
        evaluationMapper.selectPage(page, lqw);
        return page;
    }
}
