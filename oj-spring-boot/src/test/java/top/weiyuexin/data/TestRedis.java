package top.weiyuexin.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import top.weiyuexin.Application;
import top.weiyuexin.pojo.Article;
import top.weiyuexin.pojo.User;

import java.io.IOException;

/**
 * @PackageName: top.weiyuexin.data
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: TestRedis
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/8 17:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestRedis {
    //自动装配RedisTemplate对象
    @Autowired
    private RedisTemplate redisTemplate;
    // JSON工具
    private static final ObjectMapper mapper = new ObjectMapper();


    @Test
    public void testSet() throws JsonProcessingException {
        ValueOperations ops = redisTemplate.opsForValue();
        Article article = new Article();
        article.setId(3);
        article.setTitle("测试");
        article.setContent("测试redis");
        // 手动序列化
        //String json = mapper.writeValueAsString(article);
        ops.set("article:3", article);
    }

    @Test
    public void testGet() throws IOException {
        ValueOperations ops = redisTemplate.opsForValue(); //表明数据是key-value型的数据
        Article article = (Article) ops.get("article");
        //反序列化
        //Article article = mapper.readValue((JsonParser) articleJson, Article.class);
        System.out.println(article);
    }

    @Test
    public void testxlh() {
        User user = new User();
        user.setUsername("weeee");
        redisTemplate.opsForValue().set("user", user);
    }
}
