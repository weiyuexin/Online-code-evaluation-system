package top.weiyuexin.service.impl;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.service.COSService;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

/**
 * @PackageName: top.weiyuexin.service.impl
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: COSServiceImpl
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/11 23:09
 */
@Service
public class COSServiceImpl implements COSService {
    @Value("${spring.tencent.secretId}")
    private String secretId;
    @Value("${spring.tencent.secretKey}")
    private String secretKey;
    @Value("${spring.tencent.bucket}")
    private String bucket;
    @Value("${spring.tencent.bucketName}")
    private String bucketName;
    @Value("${spring.tencent.path}")
    private String path;
    @Value("${spring.tencent.qianzui}")
    private String qianzui;
    @Value("${spring.tencent.qianzui-file}")
    private String qianzui_file;

    /**
     * 上传图片到腾讯云cos
     * @param file
     * @return
     */
    @Override
    public R upload(MultipartFile file) {

        String oldFileName = file.getOriginalFilename();
        String eName = oldFileName.substring(oldFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID()+eName;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH)+1;
        int day=cal.get(Calendar.DATE);
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(bucket));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = this.bucketName;

        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        File localFile = null;
        try {
            localFile = File.createTempFile("temp",null);
            file.transferTo(localFile);
            // 指定要上传到 COS 上的路径
            String key = "/"+this.qianzui+"/"+year+"/"+month+"/"+day+"/"+year+month+day+newFileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);


            return R.success(this.path + putObjectRequest.getKey(),"图片上传成功！");
        } catch (IOException e) {
            return R.error("图片上传失败!");
        }finally {
            // 关闭客户端(关闭后台线程)
            cosclient.shutdown();
        }
    }
}
