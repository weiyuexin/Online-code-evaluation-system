package top.weiyuexin.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.service.QiniuService;
import top.weiyuexin.utils.QiniuUtils;
import top.weiyuexin.utils.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>Project: oj-spring-boot - QiniuServiceImpl</p>
 * <p>描述：<p>
 *
 * @author WYX [3022422894@qq.com]
 * @since 2023-12-01
 */
@Service
@Slf4j
public class QiniuServiceImpl implements QiniuService {
    @Autowired
    private QiniuUtils qiniuUtils;

    @Override
    public R upload(MultipartFile file) {
        if (file.isEmpty()) {
            log.error("上传文件到七牛云出错：文件为空");
            return R.error("文件不能为空");
        }
        String fileName = StringUtils.getRandomImgName(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            FileInputStream uploadFile = (FileInputStream) file.getInputStream();
            String path = qiniuUtils.upload(uploadFile, fileName);
            log.info("上传图片到七牛云成功，url:{}", path);
            return R.success(path,"上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("上传文件到七牛云失败！");
            return R.error("上传到七牛云出错");
        }
    }
}
