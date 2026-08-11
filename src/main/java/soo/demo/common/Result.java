package soo.demo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Result<T> {
    private int count;
    private T data;

    // 리스트 전용 생성 편의 메서드
    public static <T> Result<List<T>> of(List<T> list) {
        return new Result<>(list.size(), list);
    }
}