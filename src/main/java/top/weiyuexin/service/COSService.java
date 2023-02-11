package top.weiyuexin.service;

import org.springframework.web.multipart.MultipartFile;
import top.weiyuexin.pojo.vo.R;

import javax.servlet.http.HttpSession;

/**
 * @PackageName: top.weiyuexin.service
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: COSService
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/11 18:57
 */
public interface COSService {
    public R upload(MultipartFile file);
}
