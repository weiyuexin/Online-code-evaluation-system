package top.weiyuexin.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @PackageName: top.weiyuexin.pojo
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: Article
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/7 19:04
 */
@Data
public class Article {
    private Integer id;
    private Integer problemId;
    private Integer authorId;
    @TableField(exist = false)
    private String authorName;
    @TableField(exist = false)
    private User user;
    private String title;
    private String content;
    private String time;
    private Integer readNum;
    private Integer starNum;
    private Integer commentNum;
}
