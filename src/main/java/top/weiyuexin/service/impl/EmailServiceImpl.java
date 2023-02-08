package top.weiyuexin.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailUtil;
import org.springframework.stereotype.Service;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.service.EmailService;

/**
 * @PackageName: top.weiyuexin.service.impl
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: EmailServiceImpl
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/8 18:53
 */
@Service
public class EmailServiceImpl implements EmailService {
    /**
     * 发送邮件
     * @param email //收件人
     * @return
     */
    @Override
    public R send(String email) {
        R r = new R();
        //随机产生验证码
        Integer emailCode = RandomUtil.randomInt(10000,99999);
        //信息标题
        String title = "验证你的邮箱";
        //信息内容
        String emailCodeContent = "您的验证码是 "+ emailCode + ", 请在五分钟内完成验证。";
        //调用HuTool中的发送验证码的方法，发送验证码
        try {
            MailUtil.send(email,title,emailCodeContent,false);
            //发送成功
            r.setFlag(true);
            //将验证码保存到data中
            r.setData(emailCode);
            r.setMsg("验证码发送成功，请前往邮箱查看!");
        }catch (Exception e){
            //发送失败
            r.setFlag(false);
            r.setMsg("发送失败，请检查邮箱是否填写正确后重试!");
        }
        //返回消息模型
        return r;
    }
}
