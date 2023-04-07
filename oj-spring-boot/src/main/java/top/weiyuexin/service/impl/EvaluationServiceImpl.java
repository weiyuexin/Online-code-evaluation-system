package top.weiyuexin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
