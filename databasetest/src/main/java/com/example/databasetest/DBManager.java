package com.example.databasetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/4/16.
 */

public class DBManager {
    private static Openhelper openhelper;


    void initDB(Context context) {
        openhelper = new Openhelper(context);
    }

    private static DBManager instance;

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public synchronized void saveUser(User user) {
        SQLiteDatabase db = openhelper.getWritableDatabase();

        ContentValues values;
        values = new ContentValues();
        values.put(Dao.CLUMN_USER_ID, user.getUserid());
        values.put(Dao.CLUMN_USER_NAME, user.getUsername());
        values.put(Dao.CLUMN_USER_AGE, user.getUserage());
        values.put(Dao.CLUMN_USER_SEX, user.getUsersex());
        if (db.isOpen()) {
            db.insert(Dao.TABLE_NAME, null, values);
        }
    }

    public User getUser(String username) {
        SQLiteDatabase db = openhelper.getReadableDatabase();
        String sql = "selecet * from " + Dao.TABLE_NAME + " where " + Dao.CLUMN_USER_NAME + "  =" + username;
        Cursor cursor = db.rawQuery(sql, null);
        User user = null;
        if (cursor.moveToNext()) {
            user = new User();
            user.setUserid(cursor.getInt(cursor.getColumnIndex(Dao.CLUMN_USER_ID)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(Dao.CLUMN_USER_NAME)));
            user.setUserage(cursor.getInt(cursor.getColumnIndex(Dao.CLUMN_USER_AGE)));
            user.setUsersex(cursor.getString(cursor.getColumnIndex(Dao.CLUMN_USER_SEX)));
            return user;
        }
        return null;
    }

    public void closeDb() {
        if (openhelper != null) {
            openhelper.closeDb();

        }
    }

}
