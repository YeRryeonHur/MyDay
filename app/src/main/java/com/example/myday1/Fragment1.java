package com.example.myday1;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class Fragment1 extends Fragment {
    public Fragment1(){}
    int color;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.frag1, container, false);
        return layout;
    }
}
