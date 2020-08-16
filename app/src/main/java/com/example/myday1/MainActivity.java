package com.example.myday1;

import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static String DATE="";
    /**
     * 연/월 텍스트뷰
     */
    private TextView tvDate;
    /**
     * 그리드뷰 어댑터
     */
    private GridAdapter gridAdapter;

    /**
     * 일 저장 할 리스트
     */
    private ArrayList<String> dayList;

    /**
     * 그리드뷰
     */
    private GridView gridView;

    /**
     * 캘린더 변수
     */
    private Calendar mCal;

    //추가
    Date date;
    SimpleDateFormat curYearFormat;
    SimpleDateFormat curMonthFormat;
    int showM,showY, color=0;
    int test=0;
    int dayNum;
    public int list;
    int day;
    Integer today;
    Integer nowMonth;
    Button btn3, btn1, btn2, btn4;
    TextView tv1;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDate = (TextView)findViewById(R.id.tv_date);
        gridView = (GridView)findViewById(R.id.gridview);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        tv1 = (TextView)findViewById(R.id.saying);
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

        ImageView iv_last =  (ImageView)findViewById(R.id.iv_lastmonth);
        ImageView iv_next =  (ImageView)findViewById(R.id.iv_nextmonth);

        iv_last.setColorFilter(null);
        iv_last.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        iv_next.setColorFilter(null);
        iv_next.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        // 오늘 날짜를 세팅 해준다.
        long now = System.currentTimeMillis();

        date = new Date(now);
        /**
         * 현재 년도, 월, 일 가져오기
         */
        SimpleDateFormat simpleDate=new SimpleDateFormat("yyyyMMdd");
        DATE=simpleDate.format(date);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), list_3page.class);

                startActivity(intent);
            }
        });
        //연,월,일을 따로 저장
        curYearFormat = new SimpleDateFormat("yyyy");
        curMonthFormat = new SimpleDateFormat("MM");
        //final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        //현재 날짜 텍스트뷰에 뿌려줌
        tvDate.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));

        //gridview 요일 표시
        dayList = new ArrayList<String>();
        dayList.add("일");
        dayList.add("월");
        dayList.add("화");
        dayList.add("수");
        dayList.add("목");
        dayList.add("금");
        dayList.add("토");

        mCal = Calendar.getInstance();

        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);
        dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        //1일 - 요일 매칭 시키기 위해 공백 add
        for (int i = 1; i < dayNum; i++) {
            dayList.add("");
            test++;
        }
        //Toast.makeText(getApplicationContext(), "dayNum = " + dayNum , Toast.LENGTH_SHORT).show();
        setCalendarDate(mCal.get(Calendar.MONTH) + 1);

        gridAdapter = new GridAdapter(getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);



        showM = Integer.parseInt(curMonthFormat.format(date));
        showY = Integer.parseInt(curYearFormat.format(date));

        today = mCal.get(Calendar.DAY_OF_MONTH);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                day = position - 5 - dayNum;

                Intent intent = new Intent(getApplicationContext(), TimeTable.class);
                /**
                 * 선택한 날짜 넣어주기
                 */

               String[] strArr=tvDate.getText().toString().split("/");

                String YEAR=strArr[0];
                String MONTH=strArr[1];
                String Day=String.valueOf(day);
                if(day/10==0){
                    Day="0"+day;
                }
                intent.putExtra("chooseDate",YEAR+MONTH+Day);
                startActivity(intent);

                //Toast.makeText(getApplicationContext(), "test = " + day, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "position: "  + position, Toast.LENGTH_SHORT).show();


            }

        });
        list = (showM*30+dayNum)%100+1;
        context = this;
        Resources resources = getResources();
        String []arr = resources.getStringArray(R.array.goodsaying);
        tv1.setText(arr[list]);
    }


    /**
     * 해당 월에 표시할 일 수 구함
     *
     * @param month
     */
    private void setCalendarDate(int month) {
        mCal.set(Calendar.MONTH, month - 1);

        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            dayList.add("" + (i + 1));
        }

    }

    /**
     * 그리드뷰 어댑터
     *
     */
    private class GridAdapter extends BaseAdapter {

        private final List<String> list;

        private final LayoutInflater inflater;

        /**
         * 생성자
         *
         * @param context
         * @param list
         */
        public GridAdapter(Context context, List<String> list) {
            this.list = list;
            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            ViewHolder holder = null;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
                holder = new ViewHolder();

                holder.tvItemGridView = (TextView)convertView.findViewById(R.id.tv_item_gridview);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.tvItemGridView.setText("" + getItem(position));

            //해당 날짜 텍스트 컬러,배경 변경
            mCal = Calendar.getInstance();
            //오늘 day 가져옴
            today = mCal.get(Calendar.DAY_OF_MONTH);
            nowMonth = mCal.get(Calendar.MONTH);



            String sToday = String.valueOf(today);
            if (sToday.equals(getItem(position))) { //오늘 day 텍스트 컬러 변경
                holder.tvItemGridView.setTextColor(getResources().getColor(R.color.day));
            }
            if (position%7==0){
                holder.tvItemGridView.setTextColor(getResources().getColor(R.color.sunday));
            }
            if (position%7==6){
                holder.tvItemGridView.setTextColor(getResources().getColor(R.color.saturday));
            }

            return convertView;
        }



    }

    private class ViewHolder {
        TextView tvItemGridView;
    }

    int clickLeft,clickRight;
    int lastM,lastY,nextM,nextY;

    public void lastMonth(View v){
        clickLeft++;
        //gridview 요일 표시
        dayList = new ArrayList<String>();
        dayList.add("일");
        dayList.add("월");
        dayList.add("화");
        dayList.add("수");
        dayList.add("목");
        dayList.add("금");
        dayList.add("토");


        showM--;

        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
        mCal.set(showY, showM-1, 1);
        dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        //Toast.makeText(getApplicationContext(), "dayNum = " + dayNum , Toast.LENGTH_SHORT).show();
        //1일 - 요일 매칭 시키기 위해 공백 add
        for (int i = 1; i < dayNum; i++) {
            dayList.add("");
        }
        setCalendarDate(mCal.get(Calendar.MONTH) + 1);

        gridAdapter = new GridAdapter(getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);
        lastM = Integer.parseInt(curMonthFormat.format(date))  - clickLeft;
        lastY = Integer.parseInt(curYearFormat.format(date));


        if(showM<1){
            showY--;
            showM += 12;
            //Toast.makeText(this, showM+"  "+showY, Toast.LENGTH_LONG).show();
        }
        if(showM<10) {
            tvDate.setText(showY + "/" + "0" + showM);
        }
        else if(showM<=12){
            tvDate.setText(showY + "/"  + showM);
        }


    }

    public void nextMonth(View v){
        clickRight++;
        //gridview 요일 표시
        dayList = new ArrayList<String>();
        dayList.add("일");
        dayList.add("월");
        dayList.add("화");
        dayList.add("수");
        dayList.add("목");
        dayList.add("금");
        dayList.add("토");


        showM++;
        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
        mCal.set(showY, showM-1, 1);
        dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        //Toast.makeText(getApplicationContext(), "dayNum = " + dayNum , Toast.LENGTH_SHORT).show();
        //1일 - 요일 매칭 시키기 위해 공백 add
        for (int i = 1; i < dayNum; i++) {
            dayList.add("");
        }
        setCalendarDate(mCal.get(Calendar.MONTH) + 1);

        gridAdapter = new GridAdapter(getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);
        nextM = Integer.parseInt(curMonthFormat.format(date))  + clickRight;
        nextY = Integer.parseInt(curYearFormat.format(date));

        if(showM>12){
            showY++;
            showM-=12;

        }
        if(showM<10) {
            tvDate.setText(showY + "/" + "0" + showM);
        }
        else if(nextM<=12){
            tvDate.setText(showY + "/"  + showM);
        }

    }

    /*public void home(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }*/
    public void schedule(View v){
        Intent it=new Intent(getApplicationContext(),ToDoList1.class);
        startActivity(it);
        finish();
    }
    public void setting(View v){
        Intent intent = new Intent(getApplicationContext(), colorchange.class);
        startActivity(intent);
        finish();
    }




}
