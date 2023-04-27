package top.weiyuexin.pojo;

import lombok.Data;

/**
 * @PackageName: top.weiyuexin.pojo
 * @ProjectName: oj-spring-boot
 * @ClassName: Contest
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/19 12:09
 */
@Data
public class Contest {
    private Integer id;
    private String name;
    private String startTime;
    private String endTime;
    private Integer num;
    private String status;
}
