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
    /*个人简介*/
    //private String introduction;
    /*头像*/
    private String photo;
   // private Integer submitNum;
    //private Integer solvedNum;
    //private Date registerTime;
    /*最后登录时间*/
    //private Date accessTime;
    //private String ip;
   // private String school;
    //private Integer languageId;
}
