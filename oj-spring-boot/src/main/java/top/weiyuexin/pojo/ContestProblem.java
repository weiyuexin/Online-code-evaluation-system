package top.weiyuexin.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class ContestProblem {
    private Integer id;
    private Integer contestId;
    @TableField(exist = false)
    private String contestName;
    private Integer problemId;
    @TableField(exist = false)
    private String problemName;
    private Integer submitNum;
    private Integer solvedNum;
}
