package top.weiyuexin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.weiyuexin.pojo.Contest;
import top.weiyuexin.pojo.ContestProblem;
import top.weiyuexin.pojo.ContestUser;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.pojo.vo.W;
import top.weiyuexin.service.*;

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
    @Autowired
    private ContestProblemService contestProblemService;
    @Autowired
    private ContestUserService contestUserService;

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
     * 获取参赛用户列表
     *
     * @param contestId
     * @return
     */
    @GetMapping("/user/list/{contestId}")
    public R getContestUsers(@PathVariable("contestId") Integer contestId) {
        List<ContestUser> users = contestUserService.getByContestId(contestId);
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setUserName(userService.getById(users.get(i).getUserId()).getUsername());
            users.get(i).setContestName(contestService.getById(users.get(i).getContestId()).getName());
        }
        return R.success(users);
    }

    /**
     * 获取竞赛题目列表
     *
     * @param contestId
     * @return
     */
    @GetMapping("/problem/list/{contestId}")
    public R getContestProblems(@PathVariable("contestId") Integer contestId) {
        List<ContestProblem> problems = contestProblemService.getByContestId(contestId);
        for (int i = 0; i < problems.size(); i++) {
            problems.get(i).setProblemName(problemService.getById(problems.get(i).getProblemId()).getTitle());
            problems.get(i).setContestName(contestService.getById(problems.get(i).getContestId()).getName());
        }
        return R.success(problems);
    }

    @GetMapping("/admin/problem/{contestId}")
    public W getAdminContestProblems(@PathVariable("contestId") Integer contestId) {
        List<ContestProblem> problems = contestProblemService.getByContestId(contestId);
        for (int i = 0; i < problems.size(); i++) {
            problems.get(i).setProblemName(problemService.getById(problems.get(i).getProblemId()).getTitle());
            problems.get(i).setContestName(contestService.getById(problems.get(i).getContestId()).getName());
        }
        return new W(0, problems.size(), problems);
    }

    /**
     * 创建竞赛
     *
     * @param contest
     * @return
     */
    @PostMapping("")
    public R addContest(Contest contest) {
        contest.setStatus("未开始");
        return R.success(contestService.save(contest), "创建成功");
    }

    /**
     * 添加竞赛题目
     *
     * @param contestProblem
     * @return
     */
    @PostMapping("/problem/add")
    public R addContestProblem(ContestProblem contestProblem) {
        System.out.println(contestProblem.toString());
        return R.success(contestProblemService.save(contestProblem), "选择成功");
    }

    /**
     * 报名竞赛
     *
     * @param contestUser
     * @return
     */
    @PostMapping("/user/apply")
    public R addContestUser(ContestUser contestUser) {
        // 判断是否已经报名
        if (contestUserService.getByContestIdAndUserId(contestUser.getUserId(), contestUser.getContestId()) != null) {
            return R.error("已报名，不可重复报名");
        }

        if (contestUserService.save(contestUser)) {
            return R.success("报名成功");
        } else {
            return R.error("报名失败");
        }
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
