package com.example.chenf.screenoncount.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.chenf.screenoncount.Statistics;


public class ScreenOnOrOffReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        String Action = intent.getAction();
        if (Intent.ACTION_SCREEN_OFF.equals(Action)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Statistics.end();
                }
            }).start();
            Log.d("ScreenOnOrOffReceiver", "熄屏成功");
        }else if (Intent.ACTION_SCREEN_ON.equals(Action)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Statistics.start();
                }
            }).start();
            Log.d("ScreenOnOrOffReceiver","亮屏成功");
        }
    }
}
