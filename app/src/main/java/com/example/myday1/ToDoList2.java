package com.example.myday1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ToDoList2 extends AppCompatActivity {

    private String curDate=MainActivity.DATE;

    public static ArrayList<ListViewItem> sendArr = new ArrayList<ListViewItem>();
    private ListView listView;
    private MyAdapter Adapter;
    private String doingnow, start_time, finish_time; //지금 하고 있는 것
    Button btn3, btn1, btn2, btn4;
    private TextView output;
    int color=100;
    private Button completebtn,stopbtn;
    public static boolean flag=true;
    TextView tv1;
    final static int Init = 0;
    final static int Run = 1;
    final static int Pause = 2;

    int cur_Status; //현재의 상태를 저장할변수를 초기화함.
    int myCount = 1;
    long myBaseTime;
    long myPauseTime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_list2);
        saveData2();
        loadData();
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        output = findViewById(R.id.time_out);
        completebtn = findViewById(R.id.completebtn);
        stopbtn = findViewById(R.id.stopbtn);
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        color = pref.getInt("key2", 0);
        btn1.setBackgroundColor(color);
        btn1.setAlpha(0.66f);
        btn2.setBackgroundColor(color);
        btn2.setAlpha(0.75f);
        btn3.setBackgroundColor(color);
        btn3.setAlpha(0.84f);
        btn4.setBackgroundColor(color);
        btn4.setAlpha(0.93f);
        completebtn.setBackgroundColor(color);
        completebtn.setAlpha(0.75f);
        stopbtn.setBackgroundColor(color);
        stopbtn.setAlpha(0.84f);
        int list = ((MainActivity)MainActivity.context).list;
        Resources resources = getResources();
        String []arr = resources.getStringArray(R.array.goodsaying);
        tv1 = (TextView)findViewById(R.id.saying);
        tv1.setText(arr[list]);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), list_3page.class);
                startActivity(intent);
                finish();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), colorchange.class);
                startActivity(intent);
                finish();
            }
        });




        Intent intent = getIntent();
        Adapter = new MyAdapter(this, R.layout.to_do_list2_listview, ToDoList1.list);

        listView = findViewById(R.id.list);
        listView.setAdapter(Adapter);
        listView.invalidate();

    }



    class MyAdapter extends BaseAdapter implements View.OnClickListener {
        private Context context;
        private LayoutInflater inflater;
        private ArrayList<String> string;
        private int layout;
        AlertDialog.Builder alert;

        public MyAdapter(Context context, int alayout, ArrayList<String> string) {
            this.context = context;
            this.string = string;
            layout = alayout;
            inflater = LayoutInflater.from(this.context);
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return string.size();
        }

        @Override
        public Object getItem(int position) {
            return string.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos = position;
            if (convertView == null) convertView = inflater.inflate(layout, parent, false);

            TextView txt = (TextView) convertView.findViewById(R.id.listtext);
            txt.setText(string.get(position));
            txt.setSelected(true);

            Button btn = (Button) convertView.findViewById(R.id.btn1);
            btn.setOnClickListener(this);

            final View finalConvertView = convertView;
            btn.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alert = new AlertDialog.Builder(context);
                    alert.setTitle("프로그램");
                    alert
                            .setMessage("시작할까?")
                            .setCancelable(false)
                            .setPositiveButton("시작", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //intent(pos);
                                    doingnow=(String)Adapter.getItem(pos);
                                    finalConvertView.setBackgroundColor(Color.CYAN);
                                    myBaseTime = SystemClock.elapsedRealtime();
                                    myTimer.sendEmptyMessage(0);
                                    long now=System.currentTimeMillis();
                                    Date mDate=new Date(now);
                                    SimpleDateFormat simpleDate=new SimpleDateFormat("hh:mm:ss");
                                    start_time=simpleDate.format(mDate);
                                    cur_Status=Run;
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();
                }

            });

            Button btn2;
            btn2 = convertView.findViewById(R.id.btn2);
            btn2.setOnClickListener(this);
            btn2.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int count;
                    final int[] checked = new int[1];
                    count = Adapter.getCount();

                    alert = new AlertDialog.Builder(context);
                    alert.setTitle("완료");
                    alert
                            .setMessage("다 했어?")
                            .setCancelable(false)
                            .setPositiveButton("완료", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (count > 0) {
                                        checked[0] = pos;
                                        if (checked[0] > -1 && checked[0] < count) {
                                            Log.i("인덱스: ", String.valueOf(checked[0]));
                                            finalConvertView.setBackgroundColor(Color.WHITE);
                                            ToDoList1.list.remove(checked[0]);
                                            Adapter.notifyDataSetChanged();
                                        }
                                    } else {
                                        listView.setBackgroundColor(Color.CYAN);
                                    }
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();

                                }
                            });
                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();
                }
            });

            return convertView;
        }

        @Override
        public void onClick(View v) {

        }
    }



    //데이터 호출
    public void loadData() {
        SharedPreferences preferences = getSharedPreferences("sharedpreferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString(curDate, null);


        Type type = new TypeToken<ArrayList<ListViewItem>>() {
        }.getType();
        if (gson.fromJson(json, type) != null) {
            sendArr = gson.fromJson(json, type);
        }

    }


    public void saveData2() {
        SharedPreferences preferences = getSharedPreferences("sharedpreferences2", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ToDoList1.list);
        editor.putString(curDate+"2", json);
        editor.apply();
    }
    @Override
    protected void onResume() {
        super.onResume();
        saveData2();
    }


    //스탑워치 구현
    public void myonclick(View v) {
        switch (v.getId()) {
            case R.id.stopbtn:
                switch (cur_Status) {
                    case Init:
                        flag = true;
                        myBaseTime = SystemClock.elapsedRealtime();
                       // System.out.println(myBaseTime);
                        myTimer.sendEmptyMessage(0);

                        stopbtn.setText("일시정지");
                        cur_Status = Run;
                        break;

                    case Run: //움직이고 있을 때 멈춘다
                        flag = false;
                        myTimer.removeMessages(0);
                        myPauseTime = SystemClock.elapsedRealtime();
                        stopbtn.setText("시작");
                        cur_Status = Pause;
                        break;

                    case Pause: //시작한다
                        flag = true;
                        long now = SystemClock.elapsedRealtime();
                        myTimer.sendEmptyMessage(0);
                        myBaseTime += (now - myPauseTime);
                        stopbtn.setText("일시정지");
                        cur_Status = Run;
                        break;
                }
                break;
            case R.id.completebtn:
                flag = false;
                myTimer.removeMessages(0);
                long now=System.currentTimeMillis();
                Date mDate=new Date(now);
                SimpleDateFormat simpleDate=new SimpleDateFormat("hh:mm:ss");
                finish_time=simpleDate.format(mDate);

                /**
                 *sendArr에 저장 (한 일, 시작 시각, 나중시각)
                 */
                ListViewItem item=new ListViewItem(doingnow,start_time+","+finish_time);
                sendArr.add(item);

                listView.setAdapter(Adapter);
                saveData1();

                output.setText("00 : 00 : 00");
                cur_Status = Init;
                stopbtn.setText("시작");
                myCount = 1;

                break;
        }
    }

    Handler myTimer = new Handler() {
        @SuppressLint("HandlerLeak")
        public void handleMessage(Message msg) {
            output.setText(getTimeOut());
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

    private void saveData1() {
        SharedPreferences preferences = getSharedPreferences("sharedpreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(sendArr);
        editor.putString(curDate, json);
        editor.apply();
    }

    //뒤로가기 버튼 눌렀을때 홈으로 이동하기 메소드
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();

    }

}
