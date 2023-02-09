package top.weiyuexin.pojo;

import lombok.Data;

import java.util.Date;
import java.util.stream.Stream;

/**
 * @PackageName: top.weiyuexin.pojo
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: solution
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/9 9:55
 */
@Data
public class solution {
    private Integer id;
    private Integer userId;
    private Integer problemId;
    private String runTime;
    private String memory;
    private Integer languageId;
    private Date submitTime;
    private String result;
    private Integer contest_id;
}
