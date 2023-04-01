package top.weiyuexin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.service.EmailService;

@Controller
public class EmailController {
    /**
     * 注入邮箱验证码服务
     */
    @Autowired
    private EmailService emailService;

    /**
     * 发送邮箱验证码接口
     *
     * @param email
     * @return
     */
    @PostMapping("/email/send/{email}")
    @ResponseBody
    public R send(@PathVariable("email") String email) {
        return emailService.send(email);
    }
}
