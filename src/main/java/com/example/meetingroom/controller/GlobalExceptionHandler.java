package com.example.meetingroom.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> handlerIllegalArgumentException(IllegalArgumentException e){
        Map<String, String> result = new HashMap<>();
        result.put("error", e.getMessage());
        return result;
    }

    @ExceptionHandler(Exception.class)
    public Map<String, String> handlerException(Exception e){
        e.printStackTrace(); // 콘솔에 진짜 에러 확인용
        Map<String, String> result = new HashMap<>();
        result.put("error", "서버 오류가 발생했습니다.");
        return result;
    }
}
