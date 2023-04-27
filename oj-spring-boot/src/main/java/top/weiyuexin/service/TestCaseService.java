package top.weiyuexin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.weiyuexin.pojo.TestCase;

import java.util.List;

/**
 * @PackageName: top.weiyuexin.service
 * @ProjectName: oj-spring-boot
 * @ClassName: TestCaseService
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/8 1:04
 */
public interface TestCaseService extends IService<TestCase> {
    List<TestCase> getByProblemId(Integer problemId);

    IPage<TestCase> getPage(Integer currentPage, Integer pageSize, TestCase testCase);
}
