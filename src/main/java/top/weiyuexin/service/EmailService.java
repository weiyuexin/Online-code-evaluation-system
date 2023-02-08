package top.weiyuexin.service;

import top.weiyuexin.pojo.vo.R;

/**
 * @PackageName: top.weiyuexin.service
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: EmailService
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/8 18:53
 */
public interface EmailService {
    /**
     * 发送邮件
     * @param email //收件人
     * @return
     */
    public R send(String email);
}
