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
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;


public class MyTimerService extends Service {
    long myBaseTime=ToDoList2.myBaseTime;
    public MyTimerService(){

    }
   private String count="00 : 00 : 00";


   IMyTimerService.Stub binder=new IMyTimerService.Stub() {
       @Override
       public String getCount() {
           return count;
       }

       @Override
       public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

       }
   };

   @Override
    public void onCreate()
    {
        super.onCreate();

        Thread Timer=new Thread(new Timer());
        Timer.start();
    }

    private boolean isStop;

   @Override
    public void onDestroy()
    {
        super.onDestroy();
        if(ToDoList2.cur_Status==4){
            count="00 : 00 : 00";
        }
        isStop=true;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent){
        isStop=true;
        if(ToDoList2.cur_Status==4){
            count=" 00: 00 : 00 ";
        }
        return super.onUnbind(intent);
    }


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

    private class Timer implements Runnable{

        private Handler handler=new Handler();
        @Override
        public void run() {
            while(true){
                if(isStop) break;

                count=getTimeOut();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),count+" ",Toast.LENGTH_SHORT);
                    }
                });
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
