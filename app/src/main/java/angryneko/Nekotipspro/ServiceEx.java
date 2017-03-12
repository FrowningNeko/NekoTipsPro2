package angryneko.Nekotipspro;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Calendar;


public class ServiceEx extends Service {

    int REQUEST_CODE = 11223344;
    static AlarmManager alarmManager;
    static int hourOfDay;
    static int minute;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_hourOfDay = "hourOfDay";
    public static final String APP_PREFERENCES_minute = "minute";
    public static int yy;
    public static int mm;
    public static int dd;
    public static Calendar calendar;
    private SharedPreferences mSettings;
    PendingIntent myPendingIntent;
    Intent intent;
    int inspec = 0;

    @Override
    public void onCreate() {
        super.onCreate();


    }


    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        inspec = intent.getIntExtra("inspec", 0);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings.contains(APP_PREFERENCES_hourOfDay)) {
            hourOfDay = mSettings.getInt(APP_PREFERENCES_hourOfDay, 0);
            minute = mSettings.getInt(APP_PREFERENCES_minute, 0);
        }


        myService();
        return super.onStartCommand(intent, flags, startId);
    }

    public void myService() {
        intent = new Intent(this, RepeatingAlarmService.class);
        myPendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        hourOfDay = mSettings.getInt(APP_PREFERENCES_hourOfDay, 0);
        minute = mSettings.getInt(APP_PREFERENCES_minute, 0);
        Calendar myAlarmDate = Calendar.getInstance();
        calendar = Calendar.getInstance();
        yy = calendar.get(Calendar.YEAR);
        mm = calendar.get(Calendar.MONTH);
        dd = calendar.get(Calendar.DAY_OF_MONTH);
        myAlarmDate.setTimeInMillis(System.currentTimeMillis());
        myAlarmDate.set(Calendar.MONTH, mm);
        myAlarmDate.set(Calendar.YEAR, yy);

        if (inspec == 1) {
            dd = dd + 1;
        }
        myAlarmDate.set(Calendar.DAY_OF_MONTH, dd);
        myAlarmDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
        myAlarmDate.set(Calendar.MINUTE, minute);
        myAlarmDate.set(Calendar.SECOND, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, myAlarmDate.getTimeInMillis(), myPendingIntent);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_hourOfDay, hourOfDay);
        editor.putInt(APP_PREFERENCES_minute, minute);
        editor.apply();
        Toast.makeText(this, "Приложение Neko Tips запущенно", Toast.LENGTH_LONG).show();
        stopSelf();
    }
}