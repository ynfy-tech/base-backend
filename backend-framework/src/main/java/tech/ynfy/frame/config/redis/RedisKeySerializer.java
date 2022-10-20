package tech.ynfy.frame.config.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.lang.Nullable;

import java.nio.charset.Charset;

/**
 * Redis使用FastJson序列化
 */
@Configuration
public class RedisKeySerializer implements RedisSerializer<String> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    // 为 redis 统一添加前缀
    private final String CACHE_PREFIX = "url_shortcut:";
    
    @Override
    public String deserialize(@Nullable byte[] bytes) {
        return (bytes == null ? null : new String(bytes, DEFAULT_CHARSET).replaceFirst(CACHE_PREFIX, ""));
    }

    @Override
    public byte[] serialize(@Nullable String string) {
        return (string == null ? null : (CACHE_PREFIX + string).getBytes(DEFAULT_CHARSET));
    }
}
