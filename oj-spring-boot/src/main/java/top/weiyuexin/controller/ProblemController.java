package top.weiyuexin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.weiyuexin.pojo.Problem;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.pojo.vo.W;
import top.weiyuexin.service.ProblemService;

import java.util.List;

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
     * 分页查询
     *
     * @param page
     * @param limit
     * @param problem
     * @return
     */
    @GetMapping("/list")
    public W getPage(@RequestParam("page") Integer page,
                     @RequestParam("limit") Integer limit,
                     Problem problem) {
        IPage<Problem> Ipage = problemService.getPage(page, limit, problem);
        //如果当前页码值大于当前页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (page > Ipage.getPages()) {
            Ipage = problemService.getPage(page, limit, problem);
        }
        List<Problem> problems = Ipage.getRecords();

        Ipage.setRecords(problems);
        return new W(0, (int) Ipage.getTotal(), Ipage.getRecords());
    }

    /**
     * 添加题目
     *
     * @param problem
     * @return
     */
    @PostMapping("")
    public R addProblem(Problem problem) {
        return R.success();
    }

    /**
     * 修改题目
     *
     * @param problem
     * @return
     */
    @PutMapping("")
    public R updateProblem(Problem problem) {
        return R.success(problemService.updateById(problem));
    }

    /**
     * 删除题目
     *
     * @param problem
     * @return
     */
    @DeleteMapping("")
    public R deleteProblem(Problem problem) {
        return R.success(problemService.removeById(problem));
    }

}
