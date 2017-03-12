package angryneko.Nekotipspro;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class LurkingNekoDialog extends BroadcastReceiver {
    public LurkingNekoDialog() {
    }

    private SharedPreferences mSett;
    private static final int NOTIFY_ID = 1011;
    public static final String APP_PREFERENCES_LURK = "checkboxLurk";
    public static final String APP_PREFERENCES_LURK2 = "tTime";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = context.getResources();
        Notification.Builder builder = new Notification.Builder(context);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(angryneko.Nekotipspro.R.drawable.ico1)
                        // большая картинка
                .setLargeIcon(BitmapFactory.decodeResource(res, angryneko.Nekotipspro.R.drawable.ico1))
                        //.setTicker(res.getString(R.string.warning)) // текст в строке состояния
                .setTicker("Напоминаю тебе, что пора сделать перерыв")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                        //.setContentTitle(res.getString(R.string.notifytitle)) // Заголовок уведомления
                .setContentTitle("Пора сделать перерыв!")
                        //.setContentText(res.getString(R.string.notifytext))
                .setContentText("Эй! Оторвись от телефона и отдохни :)"); // Текст уведомления

        Notification notification = builder.getNotification(); // до API 16
        //Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, notification);
        Intent serviceLauncher1 = new Intent(context, LurkingService.class);
        context.stopService(serviceLauncher1);


        mSett = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mSett.edit();
        editor.putBoolean(APP_PREFERENCES_LURK, false);
        editor.remove(APP_PREFERENCES_LURK2);
        editor.apply();
        Toast.makeText(context, "Эй! Оторвись от телефона и отдохни :)", Toast.LENGTH_LONG).show();
    }
}
