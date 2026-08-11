package soo.demo.config.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import soo.demo.util.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;

@Aspect
@Component
public class CacheAspect {

    private final CacheUtil cacheUtil;

    @Autowired
    public CacheAspect(CacheUtil cacheUtil) {
        this.cacheUtil = cacheUtil;
    }

    @Around("@annotation(cacheable)")
    public Object cacheAround(ProceedingJoinPoint joinPoint, Cacheable cacheable) throws Throwable {
        // 기본 캐시 키를 어노테이션에서 가져옴
        String key1 = String.valueOf(cacheable.key1()).replace("\"", "");
        String key2 = cacheable.key2() != null ?
                cacheable.key2().replace("\"", "") : "";
        long ttl = cacheable.ttl();

        // 파라미터
        Object[] args = joinPoint.getArgs();

        // 동적 키를 만들기 위해 메소드 파라미터를 기반으로 키2 생성
        if (key2.isEmpty() && args.length > 0) {
            key2 = Arrays.stream(joinPoint.getArgs())
                    .filter(arg -> arg instanceof String || arg instanceof Number)  // 문자열 또는 숫자형 필터링
                    .map(Object::toString)  // 문자열로 변환
                    .reduce(key2, (key, arg) -> key + ":" + arg);
            if (key2.length() > 1) {
                key2 = key2.substring(1);
            }
        }

        // 메소드의 리턴 타입을 동적으로 가져옴
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Type returnType = method.getGenericReturnType();

        // 캐시를 무시하고 데이터 조회할지 여부
        boolean ignoreCache = false;
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (args.length > 0 && parameterAnnotations.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Boolean && ((Boolean) args[i])) {
                    for (Annotation annotation : parameterAnnotations[i]) {
                        if (annotation instanceof IgnoreCache) {
                            ignoreCache = true;
                            break;
                        }
                    }
                }
                if (ignoreCache) break;
            }
        }

        // 캐시 조회 및 로직 처리
        return cacheUtil.getCache(key1, key2, () -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("API 호출 중 오류 발생", throwable);
            }
        }, ttl, returnType, ignoreCache);
    }
}