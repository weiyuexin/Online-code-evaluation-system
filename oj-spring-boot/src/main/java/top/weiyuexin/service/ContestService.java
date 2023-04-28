package top.weiyuexin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.weiyuexin.pojo.Contest;

import java.util.List;

/**
 * @PackageName: top.weiyuexin.service
 * @ProjectName: oj-spring-boot
 * @ClassName: ContestService
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/19 14:59
 */
public interface ContestService extends IService<Contest> {
    IPage<Contest> getPage(Integer currentPage, Integer pageSize, Contest contest);

    List<Contest> getAllNewContest();
}
