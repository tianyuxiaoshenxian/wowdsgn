package edu.neusoft.demo.entity;

import java.util.Date;

public class RegisterNumber {


    private int id;

    private int rgNumber;

    private String currenttime;

    private Date createTime;

    private String weChatId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRgNumber() {
        return rgNumber;
    }

    public void setRgNumber(int rgNumber) {
        this.rgNumber = rgNumber;
    }

    public String getCurrenttime() {
        return currenttime;
    }

    public void setCurrenttime(String currenttime) {
        this.currenttime = currenttime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getWechatId() {
        return weChatId;
    }

    public void setWechatId(String wechatId) {
        this.weChatId = wechatId;
    }
}
