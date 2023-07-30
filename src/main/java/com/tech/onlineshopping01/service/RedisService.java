package com.tech.onlineshopping01.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Collections;

@Service
@Slf4j
public class RedisService {
    @Resource
    public JedisPool jedisPool;

    public void setValue(String key, String value) {
        Jedis resource = jedisPool.getResource();
        resource.set(key, value);
        resource.close();
    }

    public String getValue(String key) {
        Jedis resource = jedisPool.getResource();
        String value = resource.get(key);
        resource.close();
        return value;
    }

    public long stockDeduct(String key) {
        try (Jedis resource = jedisPool.getResource()) {
            String script =
                    "if redis.call('exists', KEYS[1]) == 1 then\n" +
                            "    local stock = tonumber(redis.call('get', KEYS[1]))\n" +
                            "    if (stock<=0) then\n" +
                            "        return -1\n" +
                            "    end\n" +
                            "\n" +
                            "    redis.call('decr', KEYS[1]);\n" +
                            "    return stock - 1;\n" +
                            "end\n" +
                            "\n" +
                            "return -1;";
            long res = (long)resource.eval(script, Collections.singletonList(key), Collections.emptyList());
            if (res < 0) {
                log.info("no stock");
                return -1;
            } else {
                return res;
            }
        } catch (Exception e) {
            return -1;
        }
    }

    public boolean tryGetDistributedLock(String lockKey, String requestId, int expiredTime) {


        Jedis resource = jedisPool.getResource();
        String res = resource.set(lockKey, requestId, "NX", "PX", expiredTime);
        if (res!= null && res.equals("OK")) {
            return true;
        }
        return false;
    }
    public boolean releaseDistributedLock(String lockKey, String requestId) {
        Jedis resource = jedisPool.getResource();
        String script = "if redis.call('get', KEYS[1]) == ARGV[1]" +
                " then return redis.call('del', KEYS[1])" +
                " else return 0 end";
        long eval = (long)resource.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (eval == 0) {
            return false;
        }
        return eval == 1L;
    }
}
