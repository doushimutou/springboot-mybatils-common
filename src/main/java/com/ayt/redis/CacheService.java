package com.ayt.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Description
 * Author ayt  on
 */
@Service
public class CacheService {
    private static final Logger logger= LoggerFactory.getLogger(CacheService.class);

    @Autowired
    RedisTemplate redisTemplate;


    @PostConstruct
    private void init(){
        //创建对象序列化类
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    }

    public void putCache(String key,Object value){
        try{
            //获取字符串操作接口
            redisTemplate.opsForValue().set(key,value);
        }catch (Exception e){
            logger.error( "putcache exception key:{}" + key +"value:{}"+value, e);
        }
    }

    /**
     *
     * @param key
     * @param value
     * @param expire 过期时间
     */
    public void putCacheWithExpire(String key,Object value ,int expire){

        redisTemplate.opsForValue().set(key,value,expire, TimeUnit.SECONDS);
    }

    /**
     * 根据KEY查询value
     * @param key
     * @return
     */
    public Object getCache(String key){

        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 根据key删除
     * @param key
     */
    public void deleteCache(String key ){
        redisTemplate.delete(key);
    }
}
