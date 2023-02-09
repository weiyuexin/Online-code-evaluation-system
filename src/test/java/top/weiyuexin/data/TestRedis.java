package top.weiyuexin.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import top.weiyuexin.Application;

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
    private StringRedisTemplate redisTemplate;

    @Test
    public void testSet(){
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("key2","value1");
    }

    @Test
    public void testGet(){
        ValueOperations ops = redisTemplate.opsForValue(); //表明数据是key-value型的数据
        Object name = ops.get("name");
        System.out.println(name);
    }

}
