package top.weiyuexin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.weiyuexin.pojo.ContestProblem;

import java.util.List;

public interface ContestProblemService extends IService<ContestProblem> {
    List<ContestProblem> getByContestId(Integer contestId);
}
