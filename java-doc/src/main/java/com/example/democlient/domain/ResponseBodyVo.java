package com.example.democlient.domain;

import lombok.Data;

@Data
public class ResponseBodyVo<T> {

    /**
     * 200 表示成功
     */
    Integer code;

    /**
     * 消息
     */
    String msg;

    /**
     * 数据对象
     */
    T data;

    public ResponseBodyVo() {
        this.code = 200;
        this.msg = "success";
    }

    public ResponseBodyVo(T data) {
        this.code = 200;
        this.msg = "success";
        this.data = data;
    }

}
