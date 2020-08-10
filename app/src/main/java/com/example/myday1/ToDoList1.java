package com.example.myday1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ToDoList1 extends AppCompatActivity {

    Button btn, start_btn;
    Button b1,b2,b3,b4,b5,b6;
    TextView memotext, tv1;
    Button btn3, btn1, btn2, btn4;
    Intent it;
    int color=100;
    final static int CODE = 1;
    boolean std = false, bok = false, brk = false, fod = false, exe = false;
    public static ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_list1);
        start_btn=(Button)findViewById(R.id.startbtn);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);

        b1=(Button)findViewById(R.id.studybtn);
        b2=(Button)findViewById(R.id.exercisebtn);
        b3=(Button)findViewById(R.id.breakbtn);
        b4=(Button)findViewById(R.id.otherbtn);
        b5=(Button)findViewById(R.id.foodbtn);
        b6=(Button)findViewById(R.id.bookbtn);


        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        color = pref.getInt("key2", 100);
        btn1.setBackgroundColor(color);
        btn1.setAlpha(0.66f);
        btn2.setBackgroundColor(color);
        btn2.setAlpha(0.75f);
        btn3.setBackgroundColor(color);
        btn3.setAlpha(0.84f);
        btn4.setBackgroundColor(color);
        btn4.setAlpha(0.93f);
        start_btn.setBackgroundColor(color);
        start_btn.setAlpha(0.8f);

        b1.setBackgroundColor(color);
        b1.setAlpha(0.66f);
        b2.setBackgroundColor(color);
        b2.setAlpha(0.75f);
        b3.setBackgroundColor(color); b3.setAlpha(0.75f);
        b4.setBackgroundColor(color); b4.setAlpha(0.84f);
        b5.setBackgroundColor(color); b5.setAlpha(0.84f);
        b6.setBackgroundColor(color); b6.setAlpha(0.93f);

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
        int list2 = ((MainActivity)MainActivity.context).list;
        Resources resources = getResources();
        String []arr = resources.getStringArray(R.array.goodsaying);
        tv1 = (TextView)findViewById(R.id.saying);
        tv1.setText(arr[list2]);
        memotext = findViewById(R.id.memo);
        memotext.setMovementMethod(new ScrollingMovementMethod());
        loadData();
        for(int i=0;i<list.size();i++) {
            if(list.get(i).equals("공부하기")) std=true;
            else if(list.get(i).equals("책 읽기")) bok=true;
            else if(list.get(i).equals("수업 듣기")) brk=true;
            else if(list.get(i).equals("밥 먹기")) fod=true;
            else if(list.get(i).equals("운동하기")) exe=true;
            memotext.append(list.get(i) + "\n");
        }


    }

    public void settext(String str) {
        memotext.append(str + "\n");
    }

    public void btnMain(View v) {
        int id = v.getId();
        btn = findViewById(id);
        switch (id) {
            case R.id.studybtn:
                if (std) Toast.makeText(this, "이미 추가하였습니다. ", Toast.LENGTH_SHORT).show();
                else {
                    std = true;
                    settext(btn.getText().toString());
                }
                break;

            case R.id.bookbtn:
                if (bok) Toast.makeText(this, "이미 추가하였습니다. ", Toast.LENGTH_SHORT).show();
                else {
                    bok = true;
                    settext(btn.getText().toString());
                }
                break;

            case R.id.breakbtn:
                if (brk) Toast.makeText(this, "이미 추가하였습니다. ", Toast.LENGTH_SHORT).show();
                else {
                    brk = true;
                    settext(btn.getText().toString());
                }
                break;

            case R.id.foodbtn:
                if (fod) Toast.makeText(this, "이미 추가하였습니다. ", Toast.LENGTH_SHORT).show();
                else {
                    fod = true;
                    settext(btn.getText().toString());
                }
                break;

            case R.id.exercisebtn:
                if (exe) Toast.makeText(this, "이미 추가하였습니다. ", Toast.LENGTH_SHORT).show();
                else {
                    exe = true;
                    settext(btn.getText().toString());
                }
                break;

            case R.id.otherbtn: //other class에서 값 전달받아야함.
                // it = new Intent(this, other.class);
                //startActivityForResult(it, CODE);
                break;

            case R.id.startbtn:
                String[] str = memotext.getText().toString().split("\n");
                if (!(str[0].equals(""))) {
                    list.clear();
                    for (int i = 0; i < str.length; i++) {
                        if(str[i].equals("")) continue;
                        list.add(str[i]);
                    }

                }

                it = new Intent(this, ToDoList2.class);
                it.putStringArrayListExtra("list", list);
                startActivity(it);
        }
    }


    public void loadData()  {
        SharedPreferences preferences = getSharedPreferences("sharedpreferences2", MODE_PRIVATE);
        String json = preferences.getString("listitem2", null);
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                list.clear();
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    list.add(url);
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
    }
}
