package com.example.listview.bean;

/**
 * private int picId;
 private String name;
 private String version;
 private int fileSize;

 * Created by Administrator on 2017/2/23.
 */

public class AppBean {
    private int picId;
    private String name;
    private String version;
    private int fileSize;

    public AppBean(int picId, String version, String name, int fileSize) {
        this.picId = picId;
        this.version = version;
        this.name = name;
        this.fileSize = fileSize;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }
}
