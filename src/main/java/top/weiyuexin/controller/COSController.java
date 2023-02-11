package top.weiyuexin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.service.COSService;

import javax.servlet.http.HttpSession;

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
     * @param file
     * @param session
     * @return
     */
    @PostMapping(value = "/cos/upload")
    @ResponseBody
    public Object Upload(@RequestParam(value = "file") MultipartFile file){
        //判断文件是否为空
        if(file == null){
            return R.error("图片不能为空");
        }else {
            //返回的类型是
            return cosService.upload(file);
        }
    }
}
