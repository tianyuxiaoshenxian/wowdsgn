package edu.neusoft.demo.entity;

import java.util.Date;

public class RegisterNumber {


    private int id;

    private int rgNumber;

    private int maxRgNumber;

    public static int onlineRgNumber = 0;

    private String currentTime;

    private Date createTime;


    private String userName;

    public int getMaxRgNumber() {
        return maxRgNumber;
    }

    public void setMaxRgNumber(int maxRgNumber) {
        this.maxRgNumber = maxRgNumber;
    }

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

    public static int getOnlineRgNumber() {
        return onlineRgNumber;
    }

    public static void setOnlineRgNumber(int onlineRgNumber) {
        RegisterNumber.onlineRgNumber = onlineRgNumber;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
