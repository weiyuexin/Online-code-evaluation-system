package top.weiyuexin.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.weiyuexin.pojo.File;
import top.weiyuexin.pojo.vo.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/**
 * @PackageName: top.weiyuexin.controller
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: FileController
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/10 21:31
 */
@Controller
public class FileController {
    /**
     * 上传文件接口
     *
     * @param files
     * @return
     * @throws IOException
     */
    @PostMapping("/file/upload")
    @ResponseBody
    public R upload(@RequestPart("files") MultipartFile[] files) throws IOException {
        ArrayList<File> fileList = new ArrayList<>();
        if (files.length > 0) {
            for (MultipartFile f : files) {
                if (!f.isEmpty()) {
                    //获取当前时间
                    Calendar calendar = Calendar.getInstance();
                    //保存文件时用的目录路径
                    String pathName = "F:\\upload_file\\images\\" + calendar.get(Calendar.YEAR) + "\\" + (calendar.get(Calendar.MONTH) + 1) + "\\";
                    //String pathName = "/usr/local/nginx/html/video/"+calendar.get(Calendar.YEAR) +"/" +(calendar.get(Calendar.MONTH)+1)+"/";
                    //返回给用户的目录路径
                    String path = "http://101.43.253.15/video/" + calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/";
                    //创建文件目录
                    java.io.File filePath = new java.io.File(pathName);
                    if (!filePath.exists()) { //如果文件目录不存在
                        filePath.mkdirs(); //创建目录
                    }


                    //获取原始文件名
                    String originalFilename = f.getOriginalFilename();
                    //重命名文件，使用common-io获取原始文件后缀
                    String newFileName = calendar.getTimeInMillis() + new Random(5).nextInt() + "." + FilenameUtils.getExtension(originalFilename);
                    //写入文件
                    f.transferTo(new java.io.File(filePath, newFileName));
                    //保存信息
                    File file = new File();
                    file.setNewFilename(newFileName);
                    file.setOriginalFilename(originalFilename);
                    file.setDate(calendar.getTime().toString());
                    file.setUrl(path + newFileName);
                    fileList.add(file);
                }
            }
        }
        return R.success(fileList);
    }


}
