package com.example.chenf.screenoncount.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.chenf.screenoncount.Service;
import com.example.chenf.screenoncount.Statistics;

/**
 * Created by chenf on 2015/10/7.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Intent start_service = new Intent(context, Service.class);
            context.startService(start_service);
            Statistics.start();
        }else {
            Statistics.end();
        }
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    Statistics.end();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
    }
}
