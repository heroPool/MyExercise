package com.example.databasetest;

/**
 * Created by Administrator on 2017/4/16.
 */

public class User {
    private int userid;
    private String username;
    private int userage;
    private String usersex;

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", userage=" + userage +
                ", usersex='" + usersex + '\'' +
                '}';
    }

    public User() {
        super();
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserage() {
        return userage;
    }

    public void setUserage(int userage) {
        this.userage = userage;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public User(int userid, String username, int userage, String usersex) {

        this.userid = userid;
        this.username = username;
        this.userage = userage;
        this.usersex = usersex;
    }
}
