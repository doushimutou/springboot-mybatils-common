package com.ayt.redis;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
