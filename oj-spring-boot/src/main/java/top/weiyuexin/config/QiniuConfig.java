package top.weiyuexin.config;

import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Project: oj-spring-boot - QiniuConfig</p>
 * <p>描述：七牛云配置<p>
 *
 * @author WYX [3022422894@qq.com]
 * @since 2023-12-01
 */
@Configuration
public class QiniuConfig {
    @Value("${spring.qiniu.accessKey}")
    private String accessKey;
    @Value("${spring.qiniu.secretKey}")
    private String secretKey;

    @Bean
    public Auth getAuth() {
        return Auth.create(accessKey, secretKey);
    }

    @Bean
    public UploadManager getUploadManager() {
        return new UploadManager(new com.qiniu.storage.Configuration());
    }
}
