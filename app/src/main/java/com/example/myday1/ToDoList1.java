package com.example.myday1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ToDoList1 extends AppCompatActivity {

    private String curDate=MainActivity.DATE;

    Button btn, start_btn;
    Button b1,b2,b3,b4,b5,b6;
    TextView memotext, tv1;
    Button btn3, btn1, btn2, btn4;
    Intent it;
    int color=-8331542;
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
        color = pref.getInt("key2", -8331542);


        if(Build.VERSION.SDK_INT >= 21){
            getWindow().setStatusBarColor(color);
        }


        Drawable iv_btn=btn1.getBackground();
        ColorFilter filter=new PorterDuffColorFilter(color,PorterDuff.Mode.SRC_IN);
        iv_btn.setColorFilter(filter);
        //btn1.setAlpha(0.66f);

        iv_btn=btn2.getBackground();
        iv_btn.setColorFilter(filter);
        //btn2.setAlpha(0.75f);

        iv_btn=btn3.getBackground();
        iv_btn.setColorFilter(filter);
        //btn3.setAlpha(0.84f);

        iv_btn=btn4.getBackground();
        iv_btn.setColorFilter(filter);
        //btn4.setAlpha(0.93f);


        iv_btn=b1.getBackground();
        iv_btn.setColorFilter(filter);
        b1.setAlpha(0.75f);

        iv_btn=b2.getBackground();
        iv_btn.setColorFilter(filter);
        b2.setAlpha(0.75f);

        iv_btn=b3.getBackground();
        iv_btn.setColorFilter(filter);
        b3.setAlpha(0.75f);

        iv_btn=b4.getBackground();
        iv_btn.setColorFilter(filter);
        b4.setAlpha(0.75f);

        iv_btn=b5.getBackground();
        iv_btn.setColorFilter(filter);
        b5.setAlpha(0.75f);

        iv_btn=b6.getBackground();
        iv_btn.setColorFilter(filter);
        b6.setAlpha(0.75f);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), list_3page.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), colorchange.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        int list2 = ((MainActivity)MainActivity.context).list;
        Resources resources = getResources();
        String []arr = resources.getStringArray(R.array.goodsaying);
        tv1 = (TextView)findViewById(R.id.saying);
        tv1.setText(arr[list2]);
        iv_btn=tv1.getBackground();
        filter=new PorterDuffColorFilter(color,PorterDuff.Mode.SRC_IN);
        iv_btn.setColorFilter(filter);
        tv1.setTextColor(Color.BLACK);
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
                 it = new Intent(this, other.class);
                startActivityForResult(it, CODE);
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
                overridePendingTransition(0,0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String edtAddr = data.getStringExtra("TEXT");
                    String[] str=edtAddr.split("\n");
                    for(int i=0;i<str.length;i++)
                    {
                        if(str[i].equals("")) continue;
                        memotext.append(str[i]+"\n");
                    }
                } else {

                }
                break;
        }
    }

    public void loadData()  {
        SharedPreferences preferences = getSharedPreferences("sharedpreferences2", MODE_PRIVATE);
        String json = preferences.getString(curDate+"2", null);
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

    //뒤로가기 버튼 눌렀을때 홈으로 이동하기 메소드
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);
        finish();

    }

}
