package com.example.chenf.screenoncount;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenf.screenoncount.Receiver.ScreenOnOrOffReceiver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    private List<Duration> durations = new ArrayList<>();
//    private Database database = Database.getDataBase(MainActivity.this);

    /**
     *界面组件初始化
     */
    private ListView listView;
    private TextView date;
    private TextView times;
    private TextView all_duration;
    private TextView this_duration;
    private refreshReceiver refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Intent start_service = new Intent(this, Service.class);
        startService(start_service);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        refresh = new refreshReceiver();
        registerReceiver(refresh, intentFilter);
        durations = initList();//初始化时长数据
        DurationAdapter adapter = new DurationAdapter(MainActivity.this,R.layout.list_item,durations);
        listView = (ListView) findViewById(R.id.list);
        times = (TextView) findViewById(R.id.times);
        date = (TextView) findViewById(R.id.date);
        all_duration = (TextView) findViewById(R.id.all_duration);
        this_duration = (TextView) findViewById(R.id.this_duration);
        listView.setAdapter(adapter);
        int tmp = Statistics.database.getTimes();
        Date date_cotent = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String time_str = sdf.format(date_cotent);
        date.setText(time_str);
        times.setText(String.valueOf(tmp));
        all_duration.setText(Statistics.database.count_time());
    }

    private List<Duration> initList(){
        Statistics.database.delete();
        List<Duration> list = new ArrayList<>();
        //new Thread(new Runnable() {
           // @Override
            //public void run() {
        String today = "duration" + TimeTransform.getDate(Calendar.getInstance().getTime().getTime());
        Cursor cursor = null;
                try {
                    cursor = Statistics.database.query(today,null,null,null,null,null,null);
                }catch (Exception e){
                    Toast.makeText(this,"这一天没有数据",Toast.LENGTH_LONG);
                }
                if (cursor.moveToFirst()){
                    int i =1;
                    do {
                        Duration duration = new Duration();
                        duration.setSequenceNum(i);
                        long count = cursor.getLong(cursor.getColumnIndex("duration"));
                        long between=count/1000;//除以1000是为了转换成秒

                        long day=between/(24*3600);

                        long hour=between%(24*3600)/3600;

                        long minute=between%3600/60;

                        long second=between%60;
                        String time;
                        if (day>0){
                            time = day+"天"+hour+"小时"+minute+"分钟"+second+"秒";
                        }else if (hour>0){
                            time =hour+"小时"+minute+"分钟"+second+"秒";
                        }else if (minute>0){
                            time =minute+"分钟"+second+"秒";
                        }else {
                            time =second+"秒";
                        }
                        duration.setDuration(time);
                        list.add(duration);
                        i += 1;
                    }while (cursor.moveToNext());
                }
           // }
        //}).start();

        return list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent start_service = new Intent(this, Service.class);
        startService(start_service);
        unregisterReceiver(refresh);
    }
    class refreshReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            durations = initList();//初始化时长数据
            DurationAdapter adapter = new DurationAdapter(MainActivity.this,R.layout.list_item,durations);
            int tmp = Statistics.database.getTimes();
            Date date_cotent = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            String time_str = sdf.format(date_cotent);
            listView.setAdapter(adapter);
            date.setText(time_str);
            times.setText(String.valueOf(tmp));
            all_duration.setText(Statistics.database.count_time());
        }
    }
/*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
