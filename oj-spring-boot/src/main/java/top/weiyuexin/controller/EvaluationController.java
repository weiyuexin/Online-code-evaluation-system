package top.weiyuexin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.weiyuexin.pojo.Evaluation;
import top.weiyuexin.pojo.Problem;
import top.weiyuexin.pojo.User;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.pojo.vo.W;
import top.weiyuexin.service.EvaluationService;
import top.weiyuexin.service.ProblemService;
import top.weiyuexin.service.UserService;

import java.util.List;

/**
 * @PackageName: top.weiyuexin.controller
 * @ProjectName: oj-spring-boot
 * @ClassName: EvaluationController
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/19 11:48
 */
@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProblemService problemService;

    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Integer id) {
        return R.success(evaluationService.getById(id));
    }

    @GetMapping("/list")
    public W getPage(@RequestParam("page") Integer page,
                     @RequestParam("limit") Integer limit,
                     Evaluation evaluation) {
        IPage<Evaluation> Ipage = evaluationService.getPage(page, limit, evaluation);
        //如果当前页码值大于当前页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (page > Ipage.getPages()) {
            Ipage = evaluationService.getPage(page, limit, evaluation);
        }
        List<Evaluation> evaluations = Ipage.getRecords();
        //查询用户名和题目名称
        for (int i = 0; i < evaluations.size(); i++) {
            User user = userService.getById(evaluations.get(i).getUserId());
            if (user != null) {
                evaluations.get(i).setUserName(user.getUsername());
            }
            Problem problem = problemService.getById(evaluations.get(i).getProblemId());
            if (problem != null) {
                evaluations.get(i).setProblemName(problem.getTitle());
            }
        }

        Ipage.setRecords(evaluations);
        return new W(0, (int) Ipage.getTotal(), Ipage.getRecords());
    }

    /**
     * 添加测评记录
     *
     * @param evaluation
     * @return
     */
    @PostMapping("")
    public R addEvaluation(Evaluation evaluation) {
        return R.success();
    }

    /**
     * 修改测评记录
     *
     * @param evaluation
     * @return
     */
    @PutMapping("")
    public R updateEvaluation(Evaluation evaluation) {
        return R.success(evaluationService.updateById(evaluation));
    }

    /**
     * 删除测评记录
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R deleteEvaluation(@PathVariable("id") Integer id) {
        return R.success(evaluationService.removeById(id), "删除成功");
    }
}
