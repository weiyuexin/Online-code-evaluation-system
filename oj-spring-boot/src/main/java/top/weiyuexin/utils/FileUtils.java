package top.weiyuexin.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @PackageName: top.weiyuexin.utils
 * @ProjectName: oj-spring-boot
 * @ClassName: File
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/8 20:04
 */
public class FileUtils {
    // 将字符串写入文件
    public static boolean WriteToFile(String filePath, String content) {
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean flag = true;
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
}
