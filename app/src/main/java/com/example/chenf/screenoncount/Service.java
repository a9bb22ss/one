package com.example.chenf.screenoncount;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.example.chenf.screenoncount.Receiver.ScreenOnOrOffReceiver;

/**
 * Created by chenf on 2015/10/7.
 */
public class Service extends android.app.Service {

    private IntentFilter intentFilter;
    private ScreenOnOrOffReceiver receiver;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Service","Service Create");
        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        receiver = new ScreenOnOrOffReceiver();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent start_service = new Intent(this, Service.class);
        this.startService(start_service);
    }
}
