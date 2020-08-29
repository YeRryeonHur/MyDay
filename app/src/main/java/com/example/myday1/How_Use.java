package com.example.myday1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class How_Use extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_use);
    }


    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), colorchange.class);
        startActivity(intent);
        overridePendingTransition(0,0);
        finish();

    }
}
