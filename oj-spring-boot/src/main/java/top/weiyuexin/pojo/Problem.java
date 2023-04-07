package top.weiyuexin.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @PackageName: top.weiyuexin.pojo
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: problem
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/7 19:03
 */
@Data
public class Problem {
    @TableId("id")
    private Integer id;
    @TableField("title")
    private String title;
    @TableField("describe")
    private String describe;
    @TableField("input")
    private String input;
    @TableField("output")
    private String output;
    /*提示*/
    @TableField("hint")
    private String hint;
    /*题目来源*/
    @TableField("source")
    private String source;
    /*题目上传时间*/
    @TableField("create_time")
    private String createTime;
    /*时间限制*/
    @TableField("time_limit")
    private String timeLimit;
    /*内存限制*/
    @TableField("memory_limit")
    private String memoryLimit;
    /*题目难度*/
    @TableField("difficulty")
    private String difficulty;
    /*上传用户id*/
    @TableField("uploader")
    private Integer uploader;
    /*总提交次数*/
    @TableField("submit_num")
    private Integer submitNum;
    /*总解决问题的次数*/
    @TableField("solved_num")
    private Integer solvedNum;
    /*所属竞赛的id*/
    @TableField("contest_id")
    private Integer contestId;
}
