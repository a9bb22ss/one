package com.example.chenf.screenoncount;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by chenf on 2015/9/28.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String CREATE_DURATION1 = "create table IF NOT EXISTS duration";
    private static final String CREATE_DURATION2 = "("
            +"id integer primary key autoincrement,"
            +"start integer,end integer,duration integer)";
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Long millisecond = Calendar.getInstance().getTime().getTime();
        String CREATE_DURATION = CREATE_DURATION1 + TimeTransform.getDate(millisecond) + CREATE_DURATION2;
        db.execSQL(CREATE_DURATION);
        Log.d("MyDatabaseHelper","数据库创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Long millisecond = Calendar.getInstance().getTime().getTime();
        String CREATE_DURATION = CREATE_DURATION1 + TimeTransform.getDate(millisecond) + CREATE_DURATION2;
        db.execSQL(CREATE_DURATION);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Long millisecond = Calendar.getInstance().getTime().getTime();
        String CREATE_DURATION = CREATE_DURATION1 + TimeTransform.getDate(millisecond) + CREATE_DURATION2;
        db.execSQL(CREATE_DURATION);
    }
}
