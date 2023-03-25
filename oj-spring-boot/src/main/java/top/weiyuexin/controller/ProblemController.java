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
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Integer id) {
        return R.success(problemService.getById(id));
    }

    /**
     * 添加题目
     *
     * @param problem
     * @return
     */
    @PostMapping("")
    public R add(Problem problem) {
        return R.success(problemService.save(problem));
    }

    /**
     * 修改题目
     * @param problem
     * @return
     */
    @PutMapping("")
    public R update(Problem problem) {
        return R.success(problemService.updateById(problem));
    }

    @DeleteMapping("")
    public R delete(Problem problem){
        return R.success(problemService.removeById(problem));
    }

}
