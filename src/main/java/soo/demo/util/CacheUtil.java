package soo.demo.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.function.Supplier;

@Component
public class CacheUtil {

    private static final Logger log = LoggerFactory.getLogger(CacheUtil.class);
    private static final SimpleDateFormat targetDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public CacheUtil(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;

        this.objectMapper = objectMapper;
        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
    }

    /**
     * 캐시에서 데이터를 조회하고 없으면 메소드를 호출하여 캐시에 저장
     * @param key1 캐시 키 1
     * @param key2 캐시 키 2
     * @param dataSupplier 데이터가 없을 경우 호출할 메소드 (Supplier 형태)
     * @param ttl 캐시 만료 시간 (초)
     * @param returnType 반환할 객체의 타입
     * @param ignoreCache 캐시데이터 유무 무시하고 DB 에서 새로 데이터 조회해서 캐시데이터 생성할지 여부
     * @return 캐시된 데이터 또는 새로 호출된 데이터
     */
    public <T> T getCache(String key1, String key2, Supplier<Object> dataSupplier, long ttl, Type returnType,
                          boolean ignoreCache) {
        String cacheKey = getCacheKey(key1, key2);
        String cachedData = (String) redisTemplate.opsForValue().get(cacheKey);

        // 캐시에 데이터가 있는 경우 역직렬화 해서 리턴
        if (cachedData != null && !ignoreCache) {
            try {
                JavaType javaType = objectMapper.getTypeFactory().constructType(returnType);
                Object objectData = objectMapper.readValue(cachedData, javaType);

                // Thymeleaf 탬플릿에서 사용하는 Date 타입 데이터로 형변환 처리
                if (objectData instanceof Map) {
                    return (T) transformMap((Map<String, Object>) objectData);
                }
                else if (objectData instanceof List) {
                    return (T) transformList((List<Object>) objectData);
                }
                else {
                    return (T) transformValue(objectData);
                }
            } catch (Exception e) {
                throw new RuntimeException("캐시 데이터를 역직렬화하는 데 실패했습니다 - " + cacheKey + e.getMessage());
            }
        }

        // 캐시에 데이터가 없으면 메소드 호출
        Object data = dataSupplier.get();

        // 캐시에 데이터 저장을 스킵할 플래그 확인, API 서버와 연결이 끊어지는 경우 등
        if (data instanceof Map && ((Map<?, ?>) data).containsKey("unsetCache")) {
            return (T) data;
        } else {
            // 캐시에 저장
            setCache(key1, key2, ttl, data);
        }

        return (T) data;
    }

    /**
     * 캐시에서 데이터를 조회하고 없으면 메소드를 호출하여 캐시에 저장
     * @param key1 캐시 키 1
     * @param key2 캐시 키 2
     * @return 캐시된 데이터 또는 새로 호출된 데이터
     */
    public String getCache(String key1, String key2) {
        String cacheKey = getCacheKey(key1, key2);
        return (String) redisTemplate.opsForValue().get(cacheKey);
    }

    /**
     * 캐시 데이터 upsert
     * @param key1 캐시 키 1
     * @param key2 캐시 키 2
     * @param ttl 캐시 만료 시간 (초)
     * @param data 캐싱할 데이터
     */
    public void setCache(String key1, String key2, long ttl, Object data) {
        String cacheKey = getCacheKey(key1, key2);

        try {
            // 데이터를 JSON 으로 직렬화 후 Redis 에 저장
            String jsonData = objectMapper.writeValueAsString(data);
            redisTemplate.opsForValue().set(cacheKey, jsonData, Duration.ofSeconds(ttl));
        } catch (Exception e) {
            log.error("데이터를 캐시에 직렬화하는 데 실패했습니다 - {}, {}", cacheKey, e.getMessage());
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

    /**
     * 중첩된 Map 구조를 효율적으로 탐색하여 Date 타입을 변환하는 메서드
     * @param inputMap 변환할 Map
     * @return 변환된 Map (기존 Map 에 직접 반영)
     */
    private Map<String, Object> transformMap(Map<String, Object> inputMap) {
        // 탐색할 Map 을 담을 스택 생성 (재귀 대신 스택 기반 탐색)
        Stack<Map<String, Object>> mapStack = new Stack<>();
        mapStack.push(inputMap);

        // 스택이 빌 때까지 탐색
        while (!mapStack.isEmpty()) {
            // 현재 Map 꺼내기
            Map<String, Object> currentMap = mapStack.pop();

            // Map 의 모든 엔트리를 순회
            for (Map.Entry<String, Object> entry : currentMap.entrySet()) {
                Object value = entry.getValue();

                // value 가 또 다른 Map 이면 스택에 추가
                if (value instanceof Map) {
                    mapStack.push((Map<String, Object>) value);
                }
                // value 가 List 인 경우, 리스트의 각 항목을 검사하여 Map 이면 스택에 추가
                else if (value instanceof List) {
                    List<Object> list = (List<Object>) value;
                    currentMap.put(entry.getKey(), transformList(list));
                }
                // 단순 타입의 경우 변환 적용
                else {
                    currentMap.put(entry.getKey(), transformValue(value));
                }
            }
        }

        return inputMap;
    }

    /**
     * 중첩된 List 구조를 효율적으로 탐색하여 Date 타입을 변환하는 메서드
     * @param inputList 변환할 리스트
     * @return 변환된 리스트
     */
    private List<Object> transformList(List<Object> inputList) {
        ListIterator<Object> iterator = inputList.listIterator();
        while (iterator.hasNext()) {
            Object item = iterator.next();

            if (item instanceof Map) {
                // 리스트의 항목이 Map 인 경우 재귀적으로 Map 변환 처리
                iterator.set(transformMap((Map<String, Object>) item));
            } else if (item instanceof List) {
                // 리스트의 항목이 또 다른 리스트인 경우 반복 호출하여 처리
                iterator.set(transformList((List<Object>) item));
            } else {
                // 단순 타입인 경우 변환 적용
                iterator.set(transformValue(item));
            }
        }

        return inputList;
    }

    /**
     * 단순 value 에 대한 변환 로직 (Date 타입 변환)
     * @param value 변환할 값
     * @return 변환된 값 (Date 타입이면 변환 적용)
     */
    private Object transformValue(Object value) {
        if (value instanceof String) {
            // 문자열이 특정 날짜 형식을 가지는 경우 Date 로 변환
            try {
                return targetDateFormat.parse((String) value);
            } catch (ParseException e) {
                // 변환 실패 시 원래 문자열 그대로 반환
                return value;
            }
        }

        return value;
    }

    /**
     * 캐시 데이터 삭제
     * @param key1 캐시 키 1
     * @param key2 캐시 키 2
     */
    public boolean deleteCache(String key1, String key2) {
        boolean result = false;
        String cacheKey = getCacheKey(key1, key2);
        try {
            redisTemplate.delete(cacheKey);
            log.debug("캐시 데이터 삭제됨 - {}", cacheKey);
            result = true;
        } catch (Exception e) {
            log.error("캐시 데이터를 삭제하는 데 실패했습니다 - {}, {}", cacheKey, e.getMessage());
        }

        return result;
    }

    /**
     * 키 like 검색을 통한 일괄 삭제
     * @param prefix 삭제할 키 prefix
     */
    public boolean deleteCacheByPrefix(String prefix) {
        boolean result = false;
        if (prefix == null || prefix.isEmpty()) {
            log.error("삭제할 캐시 키가 입력되지 않았습니다.");
        } else {
            Set<String> keys = redisTemplate.keys(prefix + "*");
            if (keys != null && !keys.isEmpty()) {
                log.debug("캐시 데이터 삭제됨 - {}", keys);
                try {
                    redisTemplate.delete(keys);
                    result = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
