package top.weiyuexin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.weiyuexin.pojo.Evaluation;

/**
 * @PackageName: top.weiyuexin.service
 * @ProjectName: oj-spring-boot
 * @ClassName: EvaluationService
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/8 1:28
 */
public interface EvaluationService extends IService<Evaluation> {
    IPage<Evaluation> getPage(Integer currentPage, Integer pageSize, Evaluation evaluation);
}
