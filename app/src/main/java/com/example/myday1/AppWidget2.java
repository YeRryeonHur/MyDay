package com.example.myday1;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */

public class AppWidget2 extends AppWidgetProvider {

    public static String arr[];
    public static int list;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        for (int appWidgetId : appWidgetIds) {

            
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget2);

            SharedPreferences sharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
            int color = sharedPreferences.getInt("key2", -8331542);

            SharedPreferences shared = context.getSharedPreferences("saying", Context.MODE_PRIVATE);
            String sentence = shared.getString("sen", "");

            SharedPreferences today = context.getSharedPreferences("today", context.MODE_PRIVATE);
            String str = today.getString("today_date", "");

            views.setTextColor(R.id.todo, color);


            views.setTextViewText(R.id.saying, sentence);
            SharedPreferences  preferences = context.getSharedPreferences("sharedpreferences2", Context.MODE_PRIVATE);
            ArrayList<String> temp2 = new ArrayList<>();


            String json = preferences.getString(str+"2", null);
            if (json != null) {
                try {
                    JSONArray a = new JSONArray(json);
                    for (int i = 0; i < a.length(); i++) {
                        String url = a.optString(i);
                        temp2.add(url);
                        views.setTextViewText(R.id.todo, temp2+"");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            Intent newintent = new Intent(context, MainActivity.class);
            PendingIntent pending5 = PendingIntent.getActivity(context, 0, newintent, 0);
            views.setOnClickPendingIntent(R.id.saying, pending5);


            appWidgetManager.updateAppWidget(appWidgetId, views);

        }

    }
    @Override public void onReceive(Context context, Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName myWidget = new ComponentName(context.getPackageName(), NewAppWidget.class.getName());
        int[] widgetIds = appWidgetManager.getAppWidgetIds(myWidget);
        String action = intent.getAction();
        //업데이트 액션이 들어오면
        if(action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)){
            this.onUpdate(context, AppWidgetManager.getInstance(context), widgetIds); // onUpdate 호출
        }
    }



}

