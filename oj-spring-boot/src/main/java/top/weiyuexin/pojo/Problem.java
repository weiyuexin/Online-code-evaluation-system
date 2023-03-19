package top.weiyuexin.pojo;

import lombok.Data;

import java.util.Date;

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
    private Integer id;
    private String title;
    private String describe;
    private String input;
    private String output;
    private String sampleInput;
    private String sampleOutput;
    /*提示*/
    private String hint;
    /*题目来源*/
    private String source;
    /*题目上传时间*/
    private Date createTime;
    /*时间限制*/
    private String timeLimit;
    /*内存限制*/
    private String memoryLimit;
    /*题目难度*/
    private String difficulty;
    /*上传用户id*/
    private Integer uploader;
    /*总提交次数*/
    private Integer submitNum;
    /*总解决问题的次数*/
    private Integer solvedNum;
    /*所属竞赛的id*/
    private Integer contsetId;
}
