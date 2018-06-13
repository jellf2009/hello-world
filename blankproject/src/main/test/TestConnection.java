import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class TestConnection {
    public static void main(String[] args) {
        ApplicationContext ap = new ClassPathXmlApplicationContext("classpath:spring/spring-redis.xml");
        System.out.println(ap);
        StringRedisTemplate stringRedisTemplate = ap.getBean("stringRedisTemplate", StringRedisTemplate.class);
        RedisSerializer<?> serializer = stringRedisTemplate.getValueSerializer();
        RedisSerializer<?> keySerializer = stringRedisTemplate.getKeySerializer();
        System.out.println(serializer);
        System.out.println(keySerializer);

//        stringRedisTemplate.opsForValue().set("232", "kekeke");
//        String s = stringRedisTemplate.opsForValue().get("232");
//        System.out.println(s);
//        System.out.println(s);

    }
}
