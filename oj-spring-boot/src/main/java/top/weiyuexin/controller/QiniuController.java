package top.weiyuexin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.service.QiniuService;

/**
 * <p>Project: oj-spring-boot - QiniuController</p>
 * <p>描述：<p>
 *
 * @author WYX [3022422894@qq.com]
 * @since 2023-12-01
 */
@RestController
@CrossOrigin
public class QiniuController {
    @Autowired
    private QiniuService qiniuService;

    @PostMapping(value = "/qiniu/upload")
    public R upload(@RequestParam("file") MultipartFile file) {
        return qiniuService.upload(file);
    }
}
