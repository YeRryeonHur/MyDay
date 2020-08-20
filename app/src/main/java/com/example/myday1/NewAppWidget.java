package com.example.myday1;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        int list = MainActivity.list;
        int color = MainActivity.color;
        String arr[];
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextColor(R.id.button1, color);
        views.setTextColor(R.id.button2, color);
        views.setTextColor(R.id.button3, color);
        views.setTextColor(R.id.button4, color);

        arr = MainActivity.arr;

        views.setTextViewText(R.id.saying, arr[list]);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.button1, pending);

        Intent intent2 = new Intent(context, ToDoList1.class);
        PendingIntent pending2 = PendingIntent.getActivity(context, 0, intent2, 0);
        views.setOnClickPendingIntent(R.id.button2, pending2);

        Intent intent3 = new Intent(context, list_3page.class);
        PendingIntent pending3 = PendingIntent.getActivity(context, 0, intent3, 0);
        views.setOnClickPendingIntent(R.id.button3, pending3);

        Intent intent4 = new Intent(context, colorchange.class);
        PendingIntent pending4 = PendingIntent.getActivity(context, 0, intent4, 0);
        views.setOnClickPendingIntent(R.id.button4, pending4);





        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

