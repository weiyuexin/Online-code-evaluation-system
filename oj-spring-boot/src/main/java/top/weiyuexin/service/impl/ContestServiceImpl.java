package top.weiyuexin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.weiyuexin.mapper.ContestMapper;
import top.weiyuexin.pojo.Contest;
import top.weiyuexin.service.ContestService;

import java.util.List;

/**
 * @PackageName: top.weiyuexin.service.impl
 * @ProjectName: oj-spring-boot
 * @ClassName: ContestServiceImpl
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/19 15:00
 */
@Service
public class ContestServiceImpl extends ServiceImpl<ContestMapper, Contest> implements ContestService {
    @Autowired
    private ContestMapper contestMapper;

    @Override
    public IPage<Contest> getPage(Integer currentPage, Integer pageSize, Contest contest) {
        LambdaQueryWrapper<Contest> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Contest::getId);
        //如果不为空，则查询
        lqw.like(Strings.isNotEmpty(contest.getName()), Contest::getName, contest.getName());
        lqw.like(Strings.isNotEmpty(contest.getStatus()), Contest::getStatus, contest.getStatus());
        IPage<Contest> page = new Page<>(currentPage, pageSize);
        contestMapper.selectPage(page, lqw);
        return page;
    }

    /**
     * 获取所有未开始的竞赛
     *
     * @return
     */
    @Override
    public List<Contest> getAllNewContest() {
        LambdaQueryWrapper<Contest> lqw = new LambdaQueryWrapper<>();
        lqw.like(Contest::getStatus, "未开始");
        List<Contest> contests = contestMapper.selectList(lqw);
        return contests;
    }
}
