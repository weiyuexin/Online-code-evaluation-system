package top.weiyuexin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.weiyuexin.mapper.TestCaseMapper;
import top.weiyuexin.pojo.TestCase;
import top.weiyuexin.service.TestCaseService;

import java.util.List;

/**
 * @PackageName: top.weiyuexin.service.impl
 * @ProjectName: oj-spring-boot
 * @ClassName: TestCaseServiceImpl
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/8 1:05
 */
@Service
public class TestCaseServiceImpl extends ServiceImpl<TestCaseMapper, TestCase> implements TestCaseService {
    @Autowired
    private TestCaseMapper testCaseMapper;

    /**
     * 根据problemId查找测试用例
     *
     * @param problemId
     * @return
     */
    @Override
    public List<TestCase> getByProblemId(Integer problemId) {
        LambdaQueryWrapper<TestCase> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TestCase::getProblemId, problemId);
        return testCaseMapper.selectList(lqw);
    }

    @Override
    public IPage<TestCase> getPage(Integer currentPage, Integer pageSize, TestCase testCase) {
        LambdaQueryWrapper<TestCase> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(TestCase::getId);
        lqw.like(TestCase::getProblemId, testCase.getProblemId());
        IPage<TestCase> page = new Page<>(currentPage, pageSize);
        testCaseMapper.selectPage(page, lqw);
        return page;
    }
}
