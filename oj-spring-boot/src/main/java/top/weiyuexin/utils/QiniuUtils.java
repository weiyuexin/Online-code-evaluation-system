package top.weiyuexin.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

/**
 * <p>Project: oj-spring-boot - QiniuUtils</p>
 * <p>描述：<p>
 *
 * @author WYX [3022422894@qq.com]
 * @since 2023-12-01
 */
@Component
public class QiniuUtils {
    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private Auth auth;

    @Value("${spring.qiniu.bucketName}")
    private String bucketName;
    @Value("${spring.qiniu.path}")
    private String url;

    public String upload(FileInputStream file, String fileName) throws QiniuException {
        String token = auth.uploadToken(bucketName);
        Response res = uploadManager.put(file, fileName, token, null, null);
        if (!res.isOK()) {
            throw new RuntimeException("上传七牛云出错:" + res);
        }
        return url+"/"+fileName;
    }
}
