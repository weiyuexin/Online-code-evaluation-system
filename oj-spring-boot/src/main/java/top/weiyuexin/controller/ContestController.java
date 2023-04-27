package top.weiyuexin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.weiyuexin.pojo.Contest;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.pojo.vo.W;
import top.weiyuexin.service.ContestService;
import top.weiyuexin.service.ProblemService;
import top.weiyuexin.service.UserService;

import java.util.List;

/**
 * @PackageName: top.weiyuexin.controller
 * @ProjectName: oj-spring-boot
 * @ClassName: ContestController
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/19 15:04
 */
@RestController
@RequestMapping("/contest")
public class ContestController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProblemService problemService;
    @Autowired
    private ContestService contestService;

    /**
     * 根据id查看竞赛
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Integer id) {
        return R.success(contestService.getById(id));
    }

    /**
     * 分页查询
     *
     * @param page
     * @param limit
     * @param contest
     * @return
     */
    @GetMapping("/list")
    public W getPage(@RequestParam("page") Integer page,
                     @RequestParam("limit") Integer limit,
                     Contest contest) {
        IPage<Contest> Ipage = contestService.getPage(page, limit, contest);
        //如果当前页码值大于当前页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (page > Ipage.getPages()) {
            Ipage = contestService.getPage(page, limit, contest);
        }
        List<Contest> contests = Ipage.getRecords();

        Ipage.setRecords(contests);
        return new W(0, (int) Ipage.getTotal(), Ipage.getRecords());
    }

    /**
     * 创建竞赛
     *
     * @param contest
     * @return
     */
    @PostMapping("")
    public R addContest(Contest contest) {
        return R.success(contestService.save(contest), "创建成功");
    }

    /**
     * 修改竞赛
     *
     * @param contest
     * @return
     */
    @PutMapping("")
    public R updateContest(Contest contest) {
        return R.success(contestService.updateById(contest));
    }

    /**
     * 删除竞赛
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R deleteContest(@PathVariable("id") Integer id) {
        return R.success(contestService.removeById(id));
    }
}
