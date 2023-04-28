package top.weiyuexin.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.weiyuexin.pojo.Contest;
import top.weiyuexin.service.ContestService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ContestCron {
    @Autowired
    private ContestService contestService;

    @Scheduled(fixedRate = 1000)
    public void updateContestStatus() throws ParseException {
        List<Contest> contests = contestService.getAllNewContest();
        for (int i = 0; i < contests.size(); i++) {
            // 获取当前时间
            long currentTime = new Date().getTime();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long startTime = df.parse(contests.get(i).getStartTime()).getTime();
            long endTime = df.parse(contests.get(i).getEndTime()).getTime();
            if (currentTime >= startTime && currentTime <= endTime) {
                contests.get(i).setStatus("进行中");
            }
            if (currentTime > endTime) {
                contests.get(i).setStatus("已结束");
            }
            contestService.updateById(contests.get(i));
        }
    }
}
