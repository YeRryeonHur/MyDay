package com.example.myday1;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeTable extends AppCompatActivity {

    //완료한 일정 이름 담을 배열
    private ArrayList<String>schedule1=new ArrayList<>();
    //시간 담을 배열
    private ArrayList<String>start_time1=new ArrayList<>();
    private ArrayList<String>finish_time1=new ArrayList<>();
    //미완료한 일정 이름 담을 배열
    private ArrayList<String>schedule2=new ArrayList<>();
    //시간 담을 배열
    private ArrayList<String>start_time2=new ArrayList<>();
    private ArrayList<String>finish_time2=new ArrayList<>();
    boolean flag;
    String[] TIME; //시작, 끝 시각

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_table);

        for(int i=0;i<ToDoList2.sendArr.size();i++){
            ListViewItem listViewItem=ToDoList2.sendArr.get(i);
            String Name=listViewItem.getName();
            String Time=listViewItem.getTime();
            TIME=Time.split(","); //문자열 분리

            Log.i("테스트", TIME[0] + ", " + TIME[1]);

            for(int j=0;j<ToDoList1.list.size();j++){
                flag=false;
                String str=ToDoList1.list.get(j);
                if(str.equals(Name)) {
                    flag = true;
                    schedule2.add(Name);
                    start_time2.add(TIME[0]);
                    finish_time2.add(TIME[1]);
                    break;
                }
            }

            if(flag==false) {
                schedule1.add(Name);
                start_time1.add(TIME[0]);
                finish_time1.add(TIME[1]);
            }
        }
    }

    void coloring(int idx){
        TextView set_color;

        int start_h, start_m, finish_h, finish_m;

        for(int i = 0; i < start_time1.size(); i++){

            String[] start = start_time1.get(i).split(":");

            start_h = Integer.parseInt(start[0]);
            start_m = Integer.parseInt(start[1]);
            start_m /= 10;

            String[] finish = finish_time1.get(i).split(":");

            finish_h = Integer.parseInt(finish[0]);
            finish_m = Integer.parseInt(finish[1]);
            finish_m /= 10;

            int START = start_h * 10 + start_m, FINISH = finish_h * 10 + finish_m;

            for(int j = START; j <= FINISH; j++){
                set_color = getTextView(j);
                set_color.setBackgroundColor(Color.BLUE);

                if(j == START){
                    set_color.setText(schedule1.get(i));
                }
            }
        }

        for(int i = 0; i < start_time2.size(); i++){
            String[] start = start_time2.get(i).split(":");

            start_h = Integer.parseInt(start[0]);
            start_m = Integer.parseInt(start[1]);
            start_m /= 10;

            String[] finish = finish_time2.get(i).split(":");

            finish_h = Integer.parseInt(finish[0]);
            finish_m = Integer.parseInt(finish[1]);
            finish_m /= 10;

            int START = start_h * 10 + start_m, FINISH = finish_h * 10 + finish_m;

            for(int j = START; j <= FINISH; j++){
                set_color = getTextView(j);
                set_color.setBackgroundColor(Color.GRAY);

                if(j == START){
                    set_color.setText(schedule2.get(i));
                }
            }
        }
    }

    /**
     데이터 지우는 함수(혹시나 해서 만듦)
     **/
    private void deleteAll(){
        SharedPreferences sp=getSharedPreferences("sharedpreferences",0);
        SharedPreferences.Editor editor=sp.edit();
        editor.clear();
        editor.commit();

        sp=getSharedPreferences("sharedpreferences2",0);
        editor=sp.edit();
        editor.clear();
        editor.commit();

        ToDoList1.list.clear();
        ToDoList2.sendArr.clear();
    }

    TextView getTextView(int idx){
        int h, m;
        h = idx / 10;
        m = idx % 10;

        TextView tv = null;

        if(h == 0){
            if(m == 0) tv = findViewById(R.id.t000);
            if(m == 1) tv = findViewById(R.id.t001);
            if(m == 2) tv = findViewById(R.id.t002);
            if(m == 3) tv = findViewById(R.id.t003);
            if(m == 4) tv = findViewById(R.id.t004);
            if(m == 5) tv = findViewById(R.id.t005);
        }
        if(h == 1){
            if(m == 0) tv = findViewById(R.id.t010);
            if(m == 1) tv = findViewById(R.id.t011);
            if(m == 2) tv = findViewById(R.id.t012);
            if(m == 3) tv = findViewById(R.id.t013);
            if(m == 4) tv = findViewById(R.id.t014);
            if(m == 5) tv = findViewById(R.id.t015);
        }
        if(h == 2){
            if(m == 0) tv = findViewById(R.id.t020);
            if(m == 1) tv = findViewById(R.id.t021);
            if(m == 2) tv = findViewById(R.id.t022);
            if(m == 3) tv = findViewById(R.id.t023);
            if(m == 4) tv = findViewById(R.id.t024);
            if(m == 5) tv = findViewById(R.id.t025);
        }
        if(h == 3){
            if(m == 0) tv = findViewById(R.id.t030);
            if(m == 1) tv = findViewById(R.id.t031);
            if(m == 2) tv = findViewById(R.id.t032);
            if(m == 3) tv = findViewById(R.id.t033);
            if(m == 4) tv = findViewById(R.id.t034);
            if(m == 5) tv = findViewById(R.id.t035);
        }
        if(h == 4){
            if(m == 0) tv = findViewById(R.id.t040);
            if(m == 1) tv = findViewById(R.id.t041);
            if(m == 2) tv = findViewById(R.id.t042);
            if(m == 3) tv = findViewById(R.id.t043);
            if(m == 4) tv = findViewById(R.id.t044);
            if(m == 5) tv = findViewById(R.id.t045);
        }
        if(h == 5){
            if(m == 0) tv = findViewById(R.id.t050);
            if(m == 1) tv = findViewById(R.id.t051);
            if(m == 2) tv = findViewById(R.id.t052);
            if(m == 3) tv = findViewById(R.id.t053);
            if(m == 4) tv = findViewById(R.id.t054);
            if(m == 5) tv = findViewById(R.id.t055);
        }
        if(h == 6){
            if(m == 0) tv = findViewById(R.id.t060);
            if(m == 1) tv = findViewById(R.id.t061);
            if(m == 2) tv = findViewById(R.id.t062);
            if(m == 3) tv = findViewById(R.id.t063);
            if(m == 4) tv = findViewById(R.id.t064);
            if(m == 5) tv = findViewById(R.id.t065);
        }
        if(h == 7){
            if(m == 0) tv = findViewById(R.id.t070);
            if(m == 1) tv = findViewById(R.id.t071);
            if(m == 2) tv = findViewById(R.id.t072);
            if(m == 3) tv = findViewById(R.id.t073);
            if(m == 4) tv = findViewById(R.id.t074);
            if(m == 5) tv = findViewById(R.id.t075);
        }
        if(h == 8){
            if(m == 0) tv = findViewById(R.id.t080);
            if(m == 1) tv = findViewById(R.id.t081);
            if(m == 2) tv = findViewById(R.id.t082);
            if(m == 3) tv = findViewById(R.id.t083);
            if(m == 4) tv = findViewById(R.id.t084);
            if(m == 5) tv = findViewById(R.id.t085);
        }
        if(h == 9){
            if(m == 0) tv = findViewById(R.id.t090);
            if(m == 1) tv = findViewById(R.id.t091);
            if(m == 2) tv = findViewById(R.id.t092);
            if(m == 3) tv = findViewById(R.id.t093);
            if(m == 4) tv = findViewById(R.id.t094);
            if(m == 5) tv = findViewById(R.id.t095);
        }
        if(h == 10){
            if(m == 0) tv = findViewById(R.id.t100);
            if(m == 1) tv = findViewById(R.id.t101);
            if(m == 2) tv = findViewById(R.id.t102);
            if(m == 3) tv = findViewById(R.id.t103);
            if(m == 4) tv = findViewById(R.id.t104);
            if(m == 5) tv = findViewById(R.id.t105);
        }
        if(h == 11){
            if(m == 0) tv = findViewById(R.id.t110);
            if(m == 1) tv = findViewById(R.id.t111);
            if(m == 2) tv = findViewById(R.id.t112);
            if(m == 3) tv = findViewById(R.id.t113);
            if(m == 4) tv = findViewById(R.id.t114);
            if(m == 5) tv = findViewById(R.id.t115);
        }
        if(h == 12){
            if(m == 0) tv = findViewById(R.id.t120);
            if(m == 1) tv = findViewById(R.id.t121);
            if(m == 2) tv = findViewById(R.id.t122);
            if(m == 3) tv = findViewById(R.id.t123);
            if(m == 4) tv = findViewById(R.id.t124);
            if(m == 5) tv = findViewById(R.id.t125);
        }
        if(h == 13){
            if(m == 0) tv = findViewById(R.id.t130);
            if(m == 1) tv = findViewById(R.id.t131);
            if(m == 2) tv = findViewById(R.id.t132);
            if(m == 3) tv = findViewById(R.id.t133);
            if(m == 4) tv = findViewById(R.id.t134);
            if(m == 5) tv = findViewById(R.id.t135);
        }
        if(h == 14){
            if(m == 0) tv = findViewById(R.id.t140);
            if(m == 1) tv = findViewById(R.id.t141);
            if(m == 2) tv = findViewById(R.id.t142);
            if(m == 3) tv = findViewById(R.id.t143);
            if(m == 4) tv = findViewById(R.id.t144);
            if(m == 5) tv = findViewById(R.id.t145);
        }
        if(h == 15){
            if(m == 0) tv = findViewById(R.id.t150);
            if(m == 1) tv = findViewById(R.id.t151);
            if(m == 2) tv = findViewById(R.id.t152);
            if(m == 3) tv = findViewById(R.id.t153);
            if(m == 4) tv = findViewById(R.id.t154);
            if(m == 5) tv = findViewById(R.id.t155);
        }
        if(h == 16){
            if(m == 0) tv = findViewById(R.id.t160);
            if(m == 1) tv = findViewById(R.id.t161);
            if(m == 2) tv = findViewById(R.id.t162);
            if(m == 3) tv = findViewById(R.id.t163);
            if(m == 4) tv = findViewById(R.id.t164);
            if(m == 5) tv = findViewById(R.id.t165);
        }
        if(h == 17){
            if(m == 0) tv = findViewById(R.id.t170);
            if(m == 1) tv = findViewById(R.id.t171);
            if(m == 2) tv = findViewById(R.id.t172);
            if(m == 3) tv = findViewById(R.id.t173);
            if(m == 4) tv = findViewById(R.id.t174);
            if(m == 5) tv = findViewById(R.id.t175);
        }
        if(h == 18){
            if(m == 0) tv = findViewById(R.id.t180);
            if(m == 1) tv = findViewById(R.id.t181);
            if(m == 2) tv = findViewById(R.id.t182);
            if(m == 3) tv = findViewById(R.id.t183);
            if(m == 4) tv = findViewById(R.id.t184);
            if(m == 5) tv = findViewById(R.id.t185);
        }
        if(h == 19){
            if(m == 0) tv = findViewById(R.id.t190);
            if(m == 1) tv = findViewById(R.id.t191);
            if(m == 2) tv = findViewById(R.id.t192);
            if(m == 3) tv = findViewById(R.id.t193);
            if(m == 4) tv = findViewById(R.id.t194);
            if(m == 5) tv = findViewById(R.id.t195);
        }
        if(h == 20){
            if(m == 0) tv = findViewById(R.id.t200);
            if(m == 1) tv = findViewById(R.id.t201);
            if(m == 2) tv = findViewById(R.id.t202);
            if(m == 3) tv = findViewById(R.id.t203);
            if(m == 4) tv = findViewById(R.id.t204);
            if(m == 5) tv = findViewById(R.id.t205);
        }
        if(h == 21){
            if(m == 0) tv = findViewById(R.id.t210);
            if(m == 1) tv = findViewById(R.id.t211);
            if(m == 2) tv = findViewById(R.id.t212);
            if(m == 3) tv = findViewById(R.id.t213);
            if(m == 4) tv = findViewById(R.id.t214);
            if(m == 5) tv = findViewById(R.id.t215);
        }
        if(h == 22){
            if(m == 0) tv = findViewById(R.id.t220);
            if(m == 1) tv = findViewById(R.id.t221);
            if(m == 2) tv = findViewById(R.id.t222);
            if(m == 3) tv = findViewById(R.id.t223);
            if(m == 4) tv = findViewById(R.id.t224);
            if(m == 5) tv = findViewById(R.id.t225);
        }
        if(h == 23){
            if(m == 0) tv = findViewById(R.id.t230);
            if(m == 1) tv = findViewById(R.id.t231);
            if(m == 2) tv = findViewById(R.id.t232);
            if(m == 3) tv = findViewById(R.id.t233);
            if(m == 4) tv = findViewById(R.id.t234);
            if(m == 5) tv = findViewById(R.id.t235);
        }

        return tv;
    }
}