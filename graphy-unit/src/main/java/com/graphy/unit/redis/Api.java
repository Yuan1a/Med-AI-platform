package com.graphy.unit.redis;

import java.util.Date;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.graphy.unit.redis.attr.ReidsKeyInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * redis 操作管理
 */
@Component
@SuppressWarnings("all")
public class Api {

    private static RedisTemplate<String, Object> redisTemplate;

    @Resource
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        Api.redisTemplate = redisTemplate;
    }

    /**
     * 添加缓存
     *
     * @param home    应用程序标识
     * @param key     缓存键
     * @param value   缓存值
     * @param timeOut 过期时间
     * @throws Exception
     */
    public static void add(String home, String key, Object value, Date timeOut) throws Exception {
        try {
            if (value == null) throw new Exception("RedisError(addValue):不允许缓存null值");
            String rKey = home + ":" + key;
            String redisString = null;
            if (value instanceof String || value instanceof StringBuilder) {
                redisString = value.toString();
            } else {
                redisString = JSONObject.toJSONString(value);
            }
            redisTemplate.opsForValue().set(rKey, redisString);
            redisTemplate.expireAt(rKey, timeOut);
        } catch (Exception err) {
            throw new Exception("RedisError(addValue):" + err.getMessage());
        }
    }

    /**
     * 添加缓存
     *
     * @param home  应用程序标识
     * @param key   缓存键
     * @param value 缓存值
     * @throws Exception
     */
    public static void add(String home, String key, Object value) throws Exception {
        try {
            Date timeOut = com.graphy.unit.date.Api.dateFormat("2070-01-01 12:01:01", "yyyy-MM-dd HH:mm:ss");
            add(home, key, value, timeOut);
        } catch (Exception err) {
            throw new Exception("RedisError(addValue):" + err.getMessage());
        }
    }

    /**
     * 更新缓存的有效时间
     *
     * @param home    应用程序标识
     * @param key     缓存键
     * @param timeOut 过期时间
     * @throws Exception
     */
    public static void upTimeOut(String home, String key, Date timeOut) throws Exception {
        try {
            String rKey = home + ":" + key;
            if (!redisTemplate.hasKey(rKey)) return;
            redisTemplate.expireAt(rKey, timeOut);
        } catch (Exception err) {
            throw new Exception("RedisError(addValue):" + err.getMessage());
        }
    }

    /**
     * 获取缓存的有效时间
     *
     * @param home 应用程序标识
     * @param key  缓存键
     * @throws Exception
     */
    public static Date getTimeOut(String home, String key) throws Exception {
        try {
            String rKey = home + ":" + key;
            if (!redisTemplate.hasKey(rKey)) return null;
            long timeOut = redisTemplate.getExpire(rKey);
            if (timeOut > Integer.MAX_VALUE) {
                timeOut = Integer.MAX_VALUE;
            }
            Date dTimeOut = com.graphy.unit.date.Api.dateAddSecond(new Date(), Integer.valueOf(String.valueOf(timeOut)));
            return dTimeOut;
        } catch (Exception err) {
            throw new Exception("RedisError(addValue):" + err.getMessage());
        }
    }

    /**
     * 移除缓存
     *
     * @param home 应用标识
     * @param key  缓存键
     * @throws Exception
     */
    public static void remove(String home, String key) throws Exception {
        try {
            String rKey = home + ":" + key;
            if (redisTemplate.hasKey(rKey)) redisTemplate.delete(rKey);
        } catch (Exception err) {
            throw new Exception("RedisError(removeValue):" + err.getMessage());
        }
    }


    /**
     * 获取缓存值
     *
     * @param home 应用标识
     * @param key  缓存键
     * @return
     */
    public static String get(String home, String key) throws Exception {
        try {
            String rKey = home + ":" + key;
            if (!redisTemplate.hasKey(rKey)) return null;
            String redisValue = redisTemplate.opsForValue().get(rKey).toString();
            if (redisValue == null) return null;
            return redisValue;
        } catch (Exception err) {
            throw new Exception("RedisError(getValue):" + err.getMessage());
        }
    }

    /**
     * 获取缓存值
     *
     * @param home 应用标识
     * @param key  缓存键
     * @param cls  反射对象
     * @return
     */
    public static <T> T get(String home, String key, Class<T> cls) throws Exception {
        try {
            String rKey = home + ":" + key;
            if (!redisTemplate.hasKey(rKey)) return null;
            String redisValue = get(home, key);
            if (redisValue == null) return null;
            return JSONObject.parseObject(redisValue, cls);
        } catch (Exception err) {
            throw new Exception("RedisError(getValue):" + err.getMessage());
        }
    }

    /**
     * 获取缓存值
     *
     * @param home 应用标识
     * @param key  缓存键
     * @param cls  反射对象
     * @return
     */
    public static <T> List<T> getList(String home, String key, Class<T> cls) throws Exception {
        try {
            String rKey = home + ":" + key;
            if (!redisTemplate.hasKey(rKey)) return null;
            String redisValue = get(home, key);
            if (redisValue == null) return null;
            return JSONObject.parseArray(redisValue, cls);
        } catch (Exception err) {
            throw new Exception("RedisError(getValue):" + err.getMessage());
        }
    }

    /**
     * 关键字搜索缓存信息
     *
     * @param home
     * @param key
     * @param count
     * @return
     */
    public static List<ReidsKeyInfo> findKeyInfo(String home, String key, Integer count) {
        List<ReidsKeyInfo> returnValue = new ArrayList<>();
        if (key == null) key = "";
        if (!StrUtil.hasEmpty(key)) {
            key += "*";
        }
        Set<String> values = redisTemplate.keys(home + ":*" + key);
        Iterator<String> it = values.iterator();
        Integer i = 0;
        while (it.hasNext() && i <= count) {
            String rkey = it.next();
            ReidsKeyInfo reidsKeyInfo = new ReidsKeyInfo();
            reidsKeyInfo.setKey(rkey.substring((home + ":").length()));
            reidsKeyInfo.setValue(redisTemplate.opsForValue().get(rkey).toString());
            returnValue.add(reidsKeyInfo);
            i++;
        }
        return returnValue;
    }


}
