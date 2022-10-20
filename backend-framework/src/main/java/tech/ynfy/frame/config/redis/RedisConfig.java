package tech.ynfy.frame.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tech.ynfy.frame.constant.RedisConstant;

import java.time.Duration;

import static java.util.Collections.singletonMap;

/**
 * redis配置
 * <p>
 * LettuceConnectionConfiguration 自动加载
 */
@Configuration
@EnableCaching
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * redis-template 初始化
     *
     * @return
     */
    @Bean
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public RedisTemplate redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        template.setKeySerializer(new RedisKeySerializer());
        template.setValueSerializer(getRedisJsonSerializer());
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 缓存配置管理器
     * <p>
     * 注意: redis 连接失败会导致
     * The bean 'cacheManager', defined in class path resource [org/springframework/boot/autoconfigure/cache/RedisCacheConfiguration.class], could not be registered. A bean with that name has already been defined in class path resource [tech/ynfy/frame/config/redis/RedisConfig.class] and overriding is disabled.
     * 
     * 失败原因: 各种网络原因导致的链接失败
     * 
     * @return
     */
    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
        // 配置序列化（缓存默认有效期 6小时）
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(6));
        RedisCacheConfiguration redisCacheConfiguration = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new RedisKeySerializer()))
                                                                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(getRedisJsonSerializer()));
        // 创建默认缓存配置对象
        /* 自定义配置test:demo 的超时时间为 5分钟*/
        RedisCacheManager cacheManager = RedisCacheManager.builder(RedisCacheWriter.lockingRedisCacheWriter(lettuceConnectionFactory))
                                                          .cacheDefaults(redisCacheConfiguration)
                                                          .withInitialCacheConfigurations(singletonMap(RedisConstant.TEST_DEMO_CACHE,
                                                                                                       RedisCacheConfiguration.defaultCacheConfig()
                                                                                                                              .entryTtl(Duration.ofMinutes(5))
                                                                                                                              .disableCachingNullValues()))
                                                          .transactionAware()
                                                          .build();
        return cacheManager;
    }

    private RedisValueSerializer getRedisJsonSerializer() {
        RedisValueSerializer serializer = new RedisValueSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                                     ObjectMapper.DefaultTyping.NON_FINAL,
                                     JsonTypeInfo.As.PROPERTY);
        serializer.setObjectMapper(mapper);
        return serializer;
    }
}
