package top.weiyuexin.service;

import kotlin.Result;
import org.springframework.web.multipart.MultipartFile;
import top.weiyuexin.pojo.vo.R;

/**
 * <p>Project: oj-spring-boot - QiniuService</p>
 * <p>描述：<p>
 *
 * @author WYX [3022422894@qq.com]
 * @since 2023-12-01
 */
public interface QiniuService {
    public R upload(MultipartFile file) ;
}
