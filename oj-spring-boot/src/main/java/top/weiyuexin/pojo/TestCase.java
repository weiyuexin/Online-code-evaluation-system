package top.weiyuexin.pojo;

import lombok.Data;

/**
 * @PackageName: top.weiyuexin.pojo
 * @ProjectName: oj-spring-boot
 * @ClassName: TestCase
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/7 17:12
 */
@Data
public class TestCase {
    private Integer id;
    private Integer problemId;
    private String input;
    private String output;
}
