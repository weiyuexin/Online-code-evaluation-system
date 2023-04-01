package top.weiyuexin.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @PackageName: top.weiyuexin.utils
 * @ProjectName: oj-spring-boot
 * @ClassName: Time
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/1 17:23
 */
public class Time {

    // 获取当前时间并格式化返回
    public static String CurrentTime() {
        //日期格式类：定义你需要的日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //当前时间毫秒数转换为日期
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        String currentDate = simpleDateFormat.format(date);
        return currentDate;
    }
}
