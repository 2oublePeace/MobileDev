package com.emiryan.mobiledev.views;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.emiryan.mobiledev.R;
import com.emiryan.mobiledev.utils.DBparser;

/**
 * Implementation of App Widget functionality.
 */
public class DatabaseAppWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.database_app_widget_provider);
        views.setTextViewText(R.id.appwidget_text, Integer.toString(DBparser.rowCount(context)));
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) { }

    @Override
    public void onDisabled(Context context) { }
}