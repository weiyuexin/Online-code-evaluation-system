package top.weiyuexin.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class ContestUser {
    private Integer id;
    private Integer contestId;
    @TableField(exist = false)
    private String contestName;
    private Integer userId;
    @TableField(exist = false)
    private String userName;
    private Integer submitNum;
    private Integer solvedNum;
}
