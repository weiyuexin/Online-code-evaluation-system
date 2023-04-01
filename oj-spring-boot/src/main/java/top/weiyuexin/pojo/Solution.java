package top.weiyuexin.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @PackageName: top.weiyuexin.pojo
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: solution
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/9 9:55
 */
@Data
public class Solution {
    private Integer id;
    private Integer userId;
    @TableField(exist = false)
    private User user;
    private Integer problemId;
    @TableField(exist = false)
    private Problem problem;
    private String runTime;
    private String memory;
    private Integer languageId;
    private String submitTime;
    private String result;
    private Integer contest_id;
}
