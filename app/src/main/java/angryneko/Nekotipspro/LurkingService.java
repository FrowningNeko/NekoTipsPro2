package angryneko.Nekotipspro;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.widget.Toast;


public class LurkingService extends Service {


    private SharedPreferences mSett;
    int REQUEST_CODE = 1122334444;
    static AlarmManager alarmManager;
    String ListLurk;
    long LurkTime;
    PendingIntent pendingIntent1;
    Intent intent1;
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_LURK = "Time";
    public static final String APP_PREFERENCES_LURK2 = "tTime";
    long meow_lurk;
    long meow_lurk_finish;
    long meow3;
    long when;
    final static int myID = 1234;


    @Override
    public void onCreate() {
        super.onCreate();

        mSett = PreferenceManager.getDefaultSharedPreferences(this);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        intent1 = new Intent(this, LurkingNekoDialog.class);
        pendingIntent1 = PendingIntent.getBroadcast(this, REQUEST_CODE, intent1, 0);

        ListLurk = mSett.getString("listLurk", "0");

        int m = Integer.parseInt(ListLurk);
        switch (m) {
            case 1:
                LurkTime = 600000;
                break;
            case 2:
                LurkTime = 1200000;
                break;
            case 3:
                LurkTime = 1800000;
                break;
            case 4:
                LurkTime = 2700000;
                break;
            case 5:
                LurkTime = 3600000;
                break;
            case 0:
                LurkTime = 600000;
                break;
        }


        registerReceiver(OnLurkReceiver, new IntentFilter(
                Intent.ACTION_SCREEN_ON));
        registerReceiver(OffLurkReceiver, new IntentFilter(
                Intent.ACTION_SCREEN_OFF));
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE); //Будильник
        alarmManager.set(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + LurkTime,
                pendingIntent1);
        meow_lurk = SystemClock.elapsedRealtime() + LurkTime;
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putLong(APP_PREFERENCES_LURK, meow_lurk);
        editor.apply();
        Toast.makeText(this, "Функция Lurking Neko запущена", Toast.LENGTH_LONG).show();
    }


    public void onDestroy() {

        if (alarmManager != null) {
            Toast.makeText(this, "Функция Lurking Neko остановлена", Toast.LENGTH_LONG).show();
            alarmManager.cancel(pendingIntent1);
        }
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putLong(APP_PREFERENCES_LURK2, meow_lurk);
        editor.apply();
        super.onDestroy();
        stopForeground(true);
    }

    private BroadcastReceiver OnLurkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            meow3 = SystemClock.elapsedRealtime() + meow_lurk_finish;
            intent1 = new Intent(context, LurkingNekoDialog.class);
            pendingIntent1 = PendingIntent.getBroadcast(context, REQUEST_CODE, intent1, 0);
            alarmManager.set(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    meow3,
                    pendingIntent1);
            meow_lurk = meow3;

            onStartCommand(intent, 0, 0);
        }
    };
    private BroadcastReceiver OffLurkReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (alarmManager != null) {
                alarmManager.cancel(pendingIntent1);
            }
            meow_lurk_finish = meow_lurk - SystemClock.elapsedRealtime();

        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        when = (meow_lurk - SystemClock.elapsedRealtime()) / 1000 / 60;
//The intent to launch when the user clicks the expanded notification
        Intent intent12 = new Intent(this, MainActivity.class);
        intent12.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendIntent = PendingIntent.getActivity(this, 0, intent12, 0);

//This constructor is deprecated. Use Notification.Builder instead
        Notification notice = new Notification(angryneko.Nekotipspro.R.drawable.ico1, "Lurking Neko запущен", System.currentTimeMillis());
        notice.setLatestEventInfo(this, "Мяу!", "Осталось: " + when + " минут", pendIntent);
        notice.flags |= Notification.FLAG_NO_CLEAR;
        startForeground(myID, notice);
        return START_STICKY;

    }

}
