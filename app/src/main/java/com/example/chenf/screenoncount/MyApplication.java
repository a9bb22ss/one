package com.example.chenf.screenoncount;

import android.app.Application;
import android.content.Context;

/**
 * Created by chenf on 2015/9/28.
 */
public class MyApplication extends Application {
    private static Context context;

    public void onCreate(){
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
