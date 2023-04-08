package top.weiyuexin.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @PackageName: top.weiyuexin.pojo
 * @ProjectName: oj-spring-boot
 * @ClassName: Code
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/7 17:16
 */
@Data
public class Code {
    private Integer id;
    private Integer userId;
    private Integer problemId;
    private String codePath;
    private String createTime;
    private String language;

    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private Problem problem;
}
