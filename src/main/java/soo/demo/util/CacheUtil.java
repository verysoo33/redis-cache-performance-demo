package soo.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CacheUtil {

    private static final Logger log = LoggerFactory.getLogger(CacheUtil.class);

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public CacheUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 캐시 데이터 삭제
     * @param key1 캐시 키 1
     * @param key2 캐시 키 2
     */
    public void deleteCache(String key1, String key2) {
        String cacheKey = getCacheKey(key1, key2);

        try {
            redisTemplate.delete(cacheKey);
            log.debug("캐시 데이터 삭제됨 - {}", cacheKey);
        } catch (Exception e) {
            log.error("캐시 데이터를 삭제하는 데 실패했습니다 - {}, {}", cacheKey, e.getMessage());
        }
    }

    /**
     * 키 like 검색을 통한 일괄 삭제
     * @param prefix 삭제할 키 prefix
     */
    public void deleteCacheByPrefix(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            log.error("삭제할 캐시 키가 입력되지 않았습니다.");
            return;
        }

        Set<String> keys = redisTemplate.keys(prefix + "*");
        if (!keys.isEmpty()) {
            log.debug("캐시 데이터 삭제됨 - {}", keys);
            try {
                redisTemplate.delete(keys);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 캐시 데이터 키 : 으로 조합
     * @param key1 캐시 키1
     * @param key2 캐시 키2
     * @return 조합된 캐시 키
     */
    public String getCacheKey(String key1, String key2) {
        return ("".equals(key2) ? key1 : (key1 + ":" + key2));
    }
}
