package com.example.myday1;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class splash extends AppCompatActivity{//스플래쉬: 시작할 때 로딩 화면
    public static int color=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_splash);
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        color = pref.getInt("key2", 0);
        if(Build.VERSION.SDK_INT >= 21){
            getWindow().setStatusBarColor(color);
        }
        TextView tv_start;
        tv_start = (TextView)findViewById(R.id.start);
        tv_start.setTextColor(color);

        Handler handler = new Handler();//핸들러 만들기
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplication(), MainActivity.class));//인텐트 이용해서 스플래쉬에서 메인으로 이동하기
                splash.this.finish();//스플래쉬 끝내기
            }
        }, 2000);//지속시간 2000밀리초로 지정하기


    }





    @Override
    public void onBackPressed() {
        //스플래쉬 화면에서 뒤로가기 가기 금지
    }






}
