package soo.demo.config.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable {
    String key1();  // 캐시 키의 첫 번째 값
    String key2() default "";  // 캐시 키의 두 번째 값 (옵션)
    long ttl() default 3600; // 캐시 TTL (기본 1시간)
}