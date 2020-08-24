package com.example.myday1;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;


public class MyTimerService extends Service {
    long myBaseTime;
    public MyTimerService(){

    }
    private String time;

    IMyTimerService.Stub binder=new IMyTimerService.Stub() {
        @Override
        public String getCount() throws RemoteException {
            return time;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };

    private static Timer timer = new Timer();
    private Context ctx;

    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    public void onCreate()
    {
        super.onCreate();
        ctx = this;
        startService();
    }

    private void startService()
    {

    }

    private class mainTask extends TimerTask
    {
        public void run()
        {
            myTimer.sendEmptyMessage(0);
        }
    }

    public void onDestroy()
    {
        super.onDestroy();
     //   Toast.makeText(this, "Service Stopped ...", Toast.LENGTH_SHORT).show();
    }

    private final Handler myTimer = new Handler()
    {
        @SuppressLint("HandlerLeak")
        public void handleMessage(Message msg) {
         //   output.setText(getTimeOut());
            myTimer.sendEmptyMessage(0);
        }
    };

    String getTimeOut() {
        long now = SystemClock.elapsedRealtime();
        long outTime = now - myBaseTime;

        long sec = outTime / 1000;
        long min = sec / 60;
        long hour = min / 60;
        sec = sec % 60;

        String real_outTime = String.format("%02d : %02d : %02d", hour, min, sec);
        return real_outTime;
    }


}
