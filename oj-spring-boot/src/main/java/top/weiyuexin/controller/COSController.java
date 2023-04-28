package top.weiyuexin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.weiyuexin.pojo.vo.F;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.service.COSService;

import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName: top.weiyuexin.controller
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: COSController
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/11 14:10
 */
@Controller
public class COSController {
    @Autowired
    private COSService cosService;

    /**
     * 上传文件到腾讯云cos接口
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/cos/upload")
    @ResponseBody
    public F Upload(@RequestParam(value = "file") MultipartFile file) {
        //判断文件是否为空
        F f = new F();
        if (file == null) {
            return f;
        } else {

            Map<String, Object> map = new HashMap<>();
            map.put("alt", file.getOriginalFilename());
            R r = cosService.upload(file);
            if (r.getCode() == 200) {//上传成功
                f.setErrno(0);
                map.put("url", r.getData());
                map.put("href", r.getData());
                f.setData(map);
            } else {
                f.setErrno(1);
                f.setData("错误");
            }
        }
        return f;
    }
}
