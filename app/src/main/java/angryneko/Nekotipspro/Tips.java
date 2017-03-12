package angryneko.Nekotipspro;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;

import java.util.Random;

import xml.TipWidget;

public class Tips extends Service {
    //Класс предназначен для смены сохраненного совета после срабатываения Alarm
    int tips22;
    public static final String APP_PREFERENCES_TS = "12345";
    SharedPreferences mSettings;


    public void onCreate() {
        super.onCreate();
        mSettings = PreferenceManager.getDefaultSharedPreferences(this);

        tips22 = mSettings.getInt(APP_PREFERENCES_TS, 42);
        if(tips22<=41){
            tips22++;

        }
        else {
            tips22=0;


        }
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_TS, tips22);
        editor.apply();
        Intent intentWidget = new Intent(this, TipWidget.class);
        intentWidget.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] id = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), TipWidget.class));
        intentWidget.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, id);
        sendBroadcast(intentWidget);
        stopSelf();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
