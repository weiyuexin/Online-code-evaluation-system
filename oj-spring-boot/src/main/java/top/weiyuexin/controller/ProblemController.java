package top.weiyuexin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.weiyuexin.pojo.Problem;
import top.weiyuexin.pojo.vo.R;

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
    /**
     * 根据id查询题目
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Integer id) {
        return R.success();
    }

    /**
     * 添加题目
     *
     * @param problem
     * @return
     */
    @PostMapping("/add")
    public R add(Problem problem) {
        return R.success();
    }

    /**
     * 修改题目
     * @param problem
     * @return
     */
    @PutMapping("/update")
    public R update(Problem problem) {
        return R.success();
    }

    @DeleteMapping("/delete")
    public R delete(Problem problem){
        return R.success();
    }

}
