package top.weiyuexin.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @PackageName: top.weiyuexin.pojo
 * @ProjectName: oj-spring-boot
 * @ClassName: Evaluation
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/7 18:42
 */
@Data
public class Evaluation {
    private Integer id;
    private Integer userId;
    @TableField(exist = false)
    private User user;
    private Integer problemId;
    @TableField(exist = false)
    private Problem problem;
    private String createTime;
    private Integer languageId;
    //通过的测试案例数
    private Integer passedTestCaseNum;
    //测试案例总数
    private Integer allTestCaseNum;
    private String error;
    private Integer isPassed; //是否通过，1---通过，0---没有通过
}
