package top.weiyuexin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.weiyuexin.pojo.Problem;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.service.ProblemService;

/**
 * @PackageName: top.weiyuexin.controller
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: ProblemController
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/7 21:23
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {
    @Autowired
    private ProblemService problemService;
    /**
     * 根据id查询题目
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Integer id) {
        return R.success(problemService.getById(id));
    }

    /**
     * 分页查询
     * @param start
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public R getPage(Integer start, Integer pageSize){
        return R.success();
    }

    /**
     * 添加题目
     * @param problem
     * @return
     */
    @PostMapping("")
    public R addProblem(Problem problem) {
        return R.success(problemService.save(problem));
    }

    /**
     * 修改题目
     * @param problem
     * @return
     */
    @PutMapping("")
    public R updateProblem(Problem problem) {
        return R.success(problemService.updateById(problem));
    }

    /**
     * 删除题目
     * @param problem
     * @return
     */
    @DeleteMapping("")
    public R deleteProblem(Problem problem){
        return R.success(problemService.removeById(problem));
    }

}
