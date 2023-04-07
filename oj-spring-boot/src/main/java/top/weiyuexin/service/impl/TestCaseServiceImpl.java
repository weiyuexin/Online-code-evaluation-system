package top.weiyuexin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.weiyuexin.mapper.TestCaseMapper;
import top.weiyuexin.pojo.TestCase;
import top.weiyuexin.service.TestCaseService;

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
}
