package top.weiyuexin.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.weiyuexin.pojo.Problem;

import java.util.List;

/**
 * @PackageName: top.weiyuexin.service
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: ProblemService
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/7 21:27
 */
public interface ProblemService extends IService<Problem> {

    IPage<Problem> getPage(Integer currentPage, Integer pageSize, Problem problem);
    
    List<Problem> hotProblems(Integer num);
}
