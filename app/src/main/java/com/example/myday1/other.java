package com.example.myday1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class other extends AppCompatActivity {
    Intent it;
    EditText editmemo;
    Button button,button2;
    Button btn3, btn1, btn2, btn4;
    InputMethodManager imm;
    int color=100;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        it=getIntent();

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        color = pref.getInt("key2", 100);
        editmemo=findViewById(R.id.edit);
        imm.hideSoftInputFromWindow(editmemo.getWindowToken(),0);
        editmemo.setTextIsSelectable(true); //커서 보이게
        editmemo.setShowSoftInputOnFocus(false); //키보드 숨겨짐
        button=findViewById(R.id.finish);
        button2=findViewById(R.id.cancel);
        button.setBackgroundColor(color);
        button.setAlpha(0.66f);
        button2.setBackgroundColor(color);
        button2.setAlpha(0.75f);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);

        btn1.setBackgroundColor(color);
        btn1.setAlpha(0.66f);
        btn2.setBackgroundColor(color);
        btn2.setAlpha(0.75f);
        btn3.setBackgroundColor(color);
        btn3.setAlpha(0.84f);
        btn4.setBackgroundColor(color);
        btn4.setAlpha(0.93f);
    }

    public void btnOther(View v)
    {
        int id=v.getId();
        switch(id)
        {
            case R.id.finish:
                final String TEXT=editmemo.getText().toString();
                it.putExtra("TEXT",TEXT);
                setResult(RESULT_OK,it);
                finish();
                break;
            case R.id.cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}

