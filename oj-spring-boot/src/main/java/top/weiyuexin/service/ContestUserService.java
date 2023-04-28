package top.weiyuexin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.weiyuexin.pojo.ContestUser;

import java.util.List;

public interface ContestUserService extends IService<ContestUser> {
    List<ContestUser> getByContestId(Integer contestId);

    List<ContestUser> getByContestIdAndUserId(Integer userId, Integer contestId);
}
