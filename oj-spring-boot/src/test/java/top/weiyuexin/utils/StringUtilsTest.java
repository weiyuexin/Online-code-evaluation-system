package top.weiyuexin.utils;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>Project: oj-spring-boot - StringUtilsTest</p>
 * <p>描述：<p>
 *
 * @author WYX [3022422894@qq.com]
 * @since 2023-12-01
 */
@SpringBootTest
public class StringUtilsTest {
    @Test
    public void test() {
        System.out.println(StringUtils.getRandomImgName("王者荣耀.jpg"));
    }
}
