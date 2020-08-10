package com.example.myday1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*
ArrayList<String> keys

키값 불러오기:
keys = PreferenceManager.getArray(this, "key_list");

키값 저장하기:
PreferenceManager.setArray(this, "key_list", keys);

cf)key값들 저장한 데이터베이스 키값은 "key_list"루 했다
cf)key값들은 String형태로 저장함
cf)나머지는 평소 ArrayList사용하는것처럼 사용하면 됨
 */

public class list_3page extends Activity {
    private Context context;
    ListView listview;
    FrameLayout frame;
    Button btn4, btn2, btn1, btn3;
    String date1="", date2="";
    int day=0, emotii=0, emojic=0, day_write=0, year_a, month_a, day_a, color=0;
    listAdapter adapter;
    ArrayList<Data> list;
    ArrayList<String> keys;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_3);
        context=this;
        Intent intent = getIntent();
        list = new ArrayList<Data>();
        adapter = new listAdapter(list);
        btn4 = findViewById(R.id.btn4);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        listview = findViewById(R.id.listv);
        listview.setAdapter(adapter);
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
        keys = PreferenceManager.getArray(this, "key_list");
        for(int i = keys.size() - 1; i > -1; i--){
            day = Integer.parseInt(keys.get(i));
            emotii = PreferenceManager.getInt(context, day + "E");
            list.add(new Data(day, emotii));
        }
/*
        if(getIntent().hasExtra("da_te")){
            day = intent.getExtras().getInt("da_te");
            date2 = Integer.toString(day);
            date1 = date2+"E";
            emotii = PreferenceManager.getInt(context, date1);
            list.add(new Data(day, emotii));
        }


*/

        ImageView fab = (ImageView)findViewById(R.id.fab);
        fab.setColorFilter(null);
        fab.setColorFilter(color, PorterDuff.Mode.SRC_IN);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ToDoList1.class);
                startActivity(intent);
                finish();
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ReadDiary.class);
                intent.putExtra("day_check", day_write);
                startActivity(intent);
                finish();
            }
        });
        int list = ((MainActivity)MainActivity.context).list;
        Resources resources = getResources();
        String []arr = resources.getStringArray(R.array.goodsaying);
        tv1 = (TextView)findViewById(R.id.saying);
        tv1.setText(arr[list]);

        //일기버튼 추가 or 안보이게
        if(exist()){
            fab.setVisibility(View.GONE);
        }
        else fab.setVisibility(View.VISIBLE);
    }
    class listAdapter extends BaseAdapter{
        List<Data> lists;

        public listAdapter(List<Data> lists){
            this.lists = lists;
        }
        @Override
        public int getCount(){
            return lists.size();

        }

        @Override
        public Object getItem(int i) {
            return lists.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Context c = viewGroup.getContext();
            if(view==null){
                LayoutInflater inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.custom_list, viewGroup, false);

            }
            TextView date = view.findViewById(R.id.date_show);
            ImageView iv = view.findViewById(R.id.iv_img);

            Data data = lists.get(i);

            day_write=data.getDate();
            year_a = day_write/10000;
            month_a = (day_write/100)%100;
            day_a = day_write%100;
            date.setText("20"+String.valueOf(year_a)+"년"+String.valueOf(month_a)+"월"+String.valueOf(day_a)+"일");
            emojic = data.getEmot();
            if(emojic == 1){
                iv.setImageResource(R.drawable.laugh);
            }
            else if(emojic == 2){
                iv.setImageResource(R.drawable.smile);
            }
            else if(emojic == 3){
                iv.setImageResource(R.drawable.emoji);
            }
            else if(emojic == 4){
                iv.setImageResource(R.drawable.angry);
            }

            return view;
        }
    }

    private boolean exist(){
        Date current = Calendar.getInstance().getTime();

        SimpleDateFormat YEAR = new SimpleDateFormat("yy", Locale.getDefault());
        SimpleDateFormat MONTH = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat DAY = new SimpleDateFormat("dd", Locale.getDefault());

        String year = YEAR.format(current);
        String month = MONTH.format(current);
        String day = DAY.format(current);

        String today = year + month + day;

        keys = PreferenceManager.getArray(this, "key_list");

        for(int i = keys.size() - 1; i > -1; i--){
            if(keys.get(i).equals(today))
                return true;
        }
        return false;
    }

    public void to_Writing(View v){
        Log.i("sdf", "클릭됨");
        Intent intent = new Intent(getApplicationContext(), NewWriting.class);
        startActivity(intent);
        finish();
    }
}
