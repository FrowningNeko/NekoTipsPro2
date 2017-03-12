package xml;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.TextView;

import angryneko.Nekotipspro.R;
import angryneko.Nekotipspro.TipsActivity;

/**
 * Implementation of App Widget functionality.
 */


public class TipWidget extends AppWidgetProvider {


    public static final String APP_PREFERENCES_TS = "12345";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(context);

        int tips22 = mSettings.getInt(APP_PREFERENCES_TS, 0);

        Intent intent = new Intent(context, TipsActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        String tips[] = context.getResources().getStringArray(R.array.Tips);


        CharSequence widgetText = tips[tips22];
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.tip_widget);

        views.setTextViewText(R.id.appwidget_text, widgetText+"...");
        views.setOnClickPendingIntent(R.id.linWidget, contentIntent);

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

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(context);

        int tips22 = mSettings.getInt(APP_PREFERENCES_TS, 0);


        String tips[] = context.getResources().getStringArray(R.array.Tips);

        CharSequence widgetText = tips[tips22];
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.tip_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
    }
}

