package com.example.myday1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ReadDiary extends AppCompatActivity {
    int key_int;
    String KEY;
    Button btn4;
    Context context = this;
    ArrayList<String> keys;
    Button btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reading_page);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        //KEY값 받아오기
        Intent it = getIntent();
        key_int = it.getIntExtra("day_check", 0);
        KEY = Integer.toString(key_int);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), list_3page.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), colorchange.class);
                startActivity(intent);
            }
        });
        //날짜 설정하기
        int year_int = key_int / 10000;
        int month_int = (key_int / 100) % 100;
        int day_int = key_int % 100;
        TextView dtv = (TextView)findViewById(R.id.today_date);
        dtv.setText(year_int + "/" + month_int + "/" + day_int);

        //내용 불러오기
        String DKEY = KEY + "D";
        String DIARY = PreferenceManager.getString(this, DKEY);
        TextView tv = (TextView)findViewById(R.id.Diary_TV);
        tv.setText(DIARY);

        //이모티콘 불러오기
        String EKEY = KEY + "E";
        int emoji_code = PreferenceManager.getInt(this, EKEY);
        ImageView iv = (ImageView)findViewById(R.id.Emoji);
        if(emoji_code == 1){
            iv.setImageResource(R.drawable.laugh);
        }
        else if(emoji_code == 2){
            iv.setImageResource(R.drawable.smile);
        }
        else if(emoji_code == 3){
            iv.setImageResource(R.drawable.emoji);
        }
        else if(emoji_code == 4){
            iv.setImageResource(R.drawable.angry);
        }


        final Button bt1 = (Button)findViewById(R.id.modify);
        bt1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder ad = new AlertDialog.Builder(ReadDiary.this);
                ad.setTitle("Modify");
                ad.setMessage("수정할까요?");

                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText et = (EditText)findViewById(R.id.Diary_ET);
                        TextView tv = (TextView)findViewById(R.id.Diary_TV);
                        Button bt2 = (Button)findViewById(R.id.save);
                        Button bt3 = (Button)findViewById(R.id.delete);

                        bt1.setVisibility(View.GONE);
                        bt2.setVisibility(View.VISIBLE);
                        bt3.setVisibility(View.GONE);

                        String document = tv.getText().toString();
                        et.setText(document);

                        tv.setVisibility(View.GONE);
                        et.setVisibility(View.VISIBLE);
                    }
                });
                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();
            }
        });

        final String ecode = EKEY, dcode = DKEY;
        final Button bt3 = (Button)findViewById(R.id.delete);
        bt3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder ad = new AlertDialog.Builder(ReadDiary.this);
                ad.setTitle("Delete");
                ad.setMessage("삭제할까요?");

                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PreferenceManager.removeKey(context, dcode);
                        PreferenceManager.removeKey(context, ecode);

                        //키값 삭제
                        keys = PreferenceManager.getArray(context, "key_list");
                        int index = findIdx(KEY);
                        if(index > -1) keys.remove(index);
                        PreferenceManager.setArray(context, "key_list", keys);

                        //3번 화면으로 넘어가기
                        Intent it = new Intent(context, list_3page.class);
                        startActivity(it);
                        finish();
                    }
                });
                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();
            }
        });
    }

    public void to_TextView(View v){
        EditText et = (EditText)findViewById(R.id.Diary_ET);
        TextView tv = (TextView)findViewById(R.id.Diary_TV);
        Button bt1 = (Button)findViewById(R.id.modify);
        Button bt2 = (Button)findViewById(R.id.save);
        Button bt3 = (Button)findViewById(R.id.delete);

        bt2.setVisibility(View.GONE);
        bt1.setVisibility(View.VISIBLE);
        bt3.setVisibility(View.VISIBLE);

        String document = et.getText().toString();
        tv.setText(document);

        String DKEY = KEY + "D";
        PreferenceManager.setString(this, DKEY, document);

        et.setVisibility(View.GONE);
        tv.setVisibility(View.VISIBLE);
    }

    int findIdx(String n){
        for(int i = 0; i < keys.size(); i++){
            if(keys.get(i).equals(n)) return i;
        }
        return -1;
    }
}
