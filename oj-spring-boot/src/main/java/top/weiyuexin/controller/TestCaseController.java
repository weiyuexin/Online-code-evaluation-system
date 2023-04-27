package top.weiyuexin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.weiyuexin.pojo.TestCase;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.pojo.vo.W;
import top.weiyuexin.service.TestCaseService;

import java.util.List;

@RestController
@RequestMapping("/testcase")
public class TestCaseController {
    @Autowired
    private TestCaseService testCaseService;

    @GetMapping("/list")
    public W getPage(@RequestParam("page") Integer page,
                     @RequestParam("limit") Integer limit,
                     TestCase testCase) {
        IPage<TestCase> Ipage = testCaseService.getPage(page, limit, testCase);
        //如果当前页码值大于当前页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (page > Ipage.getPages()) {
            Ipage = testCaseService.getPage(page, limit, testCase);
        }
        List<TestCase> testCases = Ipage.getRecords();

        Ipage.setRecords(testCases);
        return new W(0, (int) Ipage.getTotal(), Ipage.getRecords());
    }

    /**
     * 添加测试用例
     *
     * @param testCase
     * @return
     */
    @PostMapping("")
    public R addtestCase(TestCase testCase) {
        return R.success(testCaseService.save(testCase), "添加成功");
    }

    /**
     * 修改用例
     *
     * @param testCase
     * @return
     */
    @PutMapping("")
    public R updateTestCase(TestCase testCase) {
        return R.success(testCaseService.updateById(testCase), "修改成功");
    }

    /**
     * 删除用例
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R deleteProblem(@PathVariable("id") Integer id) {
        return R.success(testCaseService.removeById(id), "删除成功");
    }
}
