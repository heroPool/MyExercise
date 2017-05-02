package com.example.listview02.bean;

/**
 * Created by Administrator on 2017/2/25.
 */

public class AppBean {
    private int picId;
    private String name;
    private String version;
    private int fileSize;

    public AppBean(int picId, String name, String version) {
        this.picId = picId;
        this.name = name;
        this.version = version;
    }

    public AppBean() {
        super();
    }

    @Override
    public String toString() {
        return "AppBean{" +
                "picId=" + picId +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
