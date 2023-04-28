package top.weiyuexin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.weiyuexin.mapper.ContestUserMapper;
import top.weiyuexin.pojo.ContestUser;
import top.weiyuexin.service.ContestUserService;

import java.util.List;

@Service
public class ContestUserServiceImpl extends ServiceImpl<ContestUserMapper, ContestUser> implements ContestUserService {
    @Autowired
    private ContestUserMapper contestUserMapper;

    /**
     * 根据竞赛id获取参赛者
     *
     * @param contestId
     * @return
     */
    @Override
    public List<ContestUser> getByContestId(Integer contestId) {
        LambdaQueryWrapper<ContestUser> lqw = new LambdaQueryWrapper<>();
        lqw.orderByAsc(ContestUser::getId);
        lqw.eq(ContestUser::getContestId, contestId);
        List<ContestUser> contestUsers = contestUserMapper.selectList(lqw);
        return contestUsers;
    }

    @Override
    public List<ContestUser> getByContestIdAndUserId(Integer userId, Integer contestId) {
        LambdaQueryWrapper<ContestUser> lqw = new LambdaQueryWrapper<>();
        lqw.orderByAsc(ContestUser::getId);
        lqw.eq(ContestUser::getContestId, contestId);
        lqw.eq(ContestUser::getUserId, userId);
        List<ContestUser> contestUsers = contestUserMapper.selectList(lqw);
        return contestUsers;
    }
}
