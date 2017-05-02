package com.example.listview03;

/**
 * Created by yao on 2017/2/16.
 */

public class AppBean {
    private int picId;
    private String name;
    private String version;
    private int fileSize;

    public AppBean(int picId, String name, String version, int fileSize) {
        this.picId = picId;
        this.name = name;
        this.version = version;
        this.fileSize = fileSize;
    }

    public AppBean() {
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

    @Override
    public String toString() {
        return "AppBean{" +
                "picId=" + picId +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
