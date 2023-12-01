package top.weiyuexin.utils;

import cn.hutool.core.lang.UUID;

/**
 * <p>Project: oj-spring-boot - StringUtils</p>
 * <p>描述：<p>
 *
 * @author WYX [3022422894@qq.com]
 * @since 2023-12-01
 */
public class StringUtils {
    /**
     * @Description: 生成唯一图片名称
     * @Param: fileName
     * @return: 云服务器fileName
     */
    public static String getRandomImgName(String fileName) {

        int index = fileName.lastIndexOf(".");

        if (fileName.isEmpty() || index == -1) {
            throw new IllegalArgumentException();
        }
        // 获取文件后缀
        String suffix = fileName.substring(index).toLowerCase();
        // 生成UUID
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 生成上传至云服务器的路径
        return uuid + suffix;
    }
}
