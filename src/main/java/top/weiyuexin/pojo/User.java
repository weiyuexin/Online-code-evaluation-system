package top.weiyuexin.pojo;


import lombok.Data;

import java.util.Date;

/**
 * @PackageName: top.weiyuexin.pojo
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: User
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/7 19:03
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String sex;
    private Integer points;
    private String phone;
    private String signature;
    private Date time;
    private String address;
    private String photo;
}
