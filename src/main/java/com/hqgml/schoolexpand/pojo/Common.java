package com.hqgml.schoolexpand.pojo;

import lombok.Data;

/**
 * @author Devil
 * @date 2020/1/9 1:33
 * 统一返回格式
 */
@Data
public class Common<T> {


    private Integer code;

    private long timestamp;

    private T data;

    public Common(T data) {
        this.code = 200;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }
    public Common(Integer code, T data) {
        this.code = code;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }
}
