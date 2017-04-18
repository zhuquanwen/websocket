package com.test.model;

/**
 * @author: zhuquanwen
 * @date: 2017/4/18
 * @desc: 测试接收消息
 */
public class TestRMessage {
    private String msg;
    private String userId;
    public String getMsg() {
        return msg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
