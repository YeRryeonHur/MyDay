package com.example.myday1;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewWriting extends AppCompatActivity {
    private Context context;
    int data_date;
    String KEY_date;
    ArrayList<String> keys;
    Button btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writing_page);
        btn3 = (Button)findViewById(R.id.btn3);
        TextView tv = (TextView)findViewById(R.id.today_date);
        Date current = Calendar.getInstance().getTime();

        SimpleDateFormat YEAR = new SimpleDateFormat("yy", Locale.getDefault());
        SimpleDateFormat MONTH = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat DAY = new SimpleDateFormat("dd", Locale.getDefault());

        String year = YEAR.format(current);
        String month = MONTH.format(current);
        String day = DAY.format(current);

        tv.setText(year + "/" + month + "/" + day);

        int year_int = Integer.parseInt(year);
        int month_int = Integer.parseInt(month);
        int day_int = Integer.parseInt(day);

        data_date = year_int * 10000 + month_int * 100 + day_int;
        KEY_date = Integer.toString(data_date);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), list_3page.class);
                startActivity(intent);
            }
        });
    }

    public void save_Diary(View v){
        context = this;

        //이모티콘 저장
        RadioGroup rg_emoji = (RadioGroup)findViewById(R.id.emotes);
        RadioButton rb_emoji;
        int emoji_code;

        if(rg_emoji.getCheckedRadioButtonId() == R.id.laughing){
            rb_emoji = (RadioButton)findViewById(R.id.laughing);
            emoji_code = 1;
        }
        else if(rg_emoji.getCheckedRadioButtonId() == R.id.smiling){
            rb_emoji = (RadioButton)findViewById(R.id.smiling);
            emoji_code = 2;
        }
        else if(rg_emoji.getCheckedRadioButtonId() == R.id.angry){
            rb_emoji = (RadioButton)findViewById(R.id.angry);
            emoji_code = 3;
        }
        else if(rg_emoji.getCheckedRadioButtonId() == R.id.crying){
            rb_emoji = (RadioButton)findViewById(R.id.crying);
            emoji_code = 4;
        }
        else{
            Toast.makeText(this, "오늘의 기분을 골라주세요!", Toast.LENGTH_SHORT).show();
            return;
        }
        String EKEY = KEY_date + "E";
        PreferenceManager.setInt(this, EKEY, emoji_code);

        //내용 저장
        String DKEY = KEY_date + "D";
        EditText et = (EditText)findViewById(R.id.document);
        String document = et.getText().toString();
        PreferenceManager.setString(this, DKEY, document);

        //키값 저장
        save_key(KEY_date);

        //돌아가기
        Intent intent = new Intent(getApplicationContext(), list_3page.class);
        intent.putExtra("da_te", data_date);
        startActivity(intent);
        finish();
    }

    private void save_key(String k){
        keys = PreferenceManager.getArray(this, "key_list");
        for(int i = 0; i < keys.size(); i++){
            if(keys.get(i).equals(k)) return;
        }
        keys.add(k);
        PreferenceManager.setArray(this, "key_list", keys);
    }
}

