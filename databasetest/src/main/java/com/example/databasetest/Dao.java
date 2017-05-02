package com.example.databasetest;

import android.content.Context;

/**
 * Created by Administrator on 2017/4/16.
 */

public class Dao {
    public static final String TABLE_NAME = "t_table_name";
    public static final String CLUMN_USER_NAME = "m_user_name";
    public static final String CLUMN_USER_ID = "m_user_id";
    public static final String CLUMN_USER_SEX = "m_user_sex";
    public static final String CLUMN_USER_AGE = "m_user_age";


    public Dao(Context context) {
        DBManager.getInstance().initDB(context);
    }

    private static Dao instance;

    public static Dao getInstance(Context contex) {
        if (instance == null) {
            instance = new Dao(contex);
        }
        return instance;
    }

    public void saveUser(User user) {
        DBManager.getInstance().saveUser(user);
    }

    public User getUser(String username) {
        if (username != null) {
            User user = DBManager.getInstance().getUser(username);
            return user;
        }
        return null;
    }
}
