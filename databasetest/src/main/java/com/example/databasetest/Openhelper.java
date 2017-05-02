package com.example.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/4/16.
 */

public class Openhelper extends SQLiteOpenHelper {
    private static Openhelper instance;

    public static Openhelper getInstance(Context context) {

        if (instance == null) {
            instance = new Openhelper(context);
        }
        return instance;
    }

    private static final int DATABASE_VERSION = 1;
    private static final String CRATE_TABLE_SQL = "create table" +
            Dao.TABLE_NAME + "( " + Dao.CLUMN_USER_ID + " INTEGER PRIMARY KEY , "


            + Dao.CLUMN_USER_NAME + " TEXT , "
            + Dao.CLUMN_USER_AGE + " INTEGER  , "
            + Dao.CLUMN_USER_SEX + " text  " + " ) ; ";
    // create table m_table_name ( m_user_id integer primary key , m_user_name text ,m_user_age integer);

    public Openhelper(Context context) {
        super(context, "test.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void closeDb() {
        if (instance != null) {
            instance.getWritableDatabase().close();
            instance = null;
        }
    }
}
