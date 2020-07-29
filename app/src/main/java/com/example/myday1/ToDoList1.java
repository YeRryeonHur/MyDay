package com.example.myday1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ToDoList1 extends AppCompatActivity {

    Button btn;
    TextView memotext, btn3;
    Intent it;
    final static int CODE = 1;
    boolean std = false, bok = false, brk = false, fod = false, exe = false;
    public static ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_list1);
        btn3 = (Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), list_3page.class);
                startActivity(intent);
            }
        });
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

  /*  @Override
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
    }*/

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
