package com.example.myday1;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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
}
