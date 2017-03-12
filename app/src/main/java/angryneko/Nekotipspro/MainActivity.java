package angryneko.Nekotipspro;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity{

    PendingIntent myPendingIntent;
    Intent intent;
    static int hourOfDay;
    static int minute;
    int flag;
    int firstStart = 0;
    TextView txTittle;
    int REQUEST_CODE = 11223344;
    public static AlarmManager alarmManager;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_hourOfDay = "hourOfDay";
    public static final String APP_PREFERENCES_minute = "minute";
    public static final String APP_PREFERENCES_FIRST = "first";
    ImageView imageView;
    AnimationDrawable anim;
    SharedPreferences mSettings;
    public DialogFragment newFragment;



    final int MENU_SETTING_ID = 3;
    final int MENU_ABOUT_ID = 2;
    final int MENU_TIPS_ID = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        txTittle = (TextView) findViewById(R.id.textView9);

        if (mSettings.contains(APP_PREFERENCES_hourOfDay)) {
            // Получаем число из настроек
            hourOfDay = mSettings.getInt(APP_PREFERENCES_hourOfDay , 0);
            minute = mSettings.getInt(APP_PREFERENCES_minute, 0);
        } else {
            hourOfDay = 12;
            minute = 0;
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putInt(APP_PREFERENCES_hourOfDay, hourOfDay);
            editor.putInt(APP_PREFERENCES_minute, minute);
            editor.apply();
        }
        getSystemService(Activity.ALARM_SERVICE);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        intent = new Intent(this, RepeatingAlarmService.class);

        imageView = (ImageView) findViewById(R.id.imageView);
        boolean isWorkingAlarm = (PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_NO_CREATE) != null);

        if (isWorkingAlarm) //Проверка, включено ли приложение
        {
            txTittle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico1, 0, 0, 0);
            imageView.setBackgroundResource(R.drawable.animbutton_description_return);
            flag = 2;
        }
        else
        {
            txTittle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico2, 0, 0, 0);
            imageView.setBackgroundResource(R.drawable.animbutton_description);
            flag = 1;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_TIPS_ID, 0, "Совет дня");
        menu.add(0, MENU_SETTING_ID, 0, "Настройки");
        menu.add(0, MENU_ABOUT_ID, 0, "О программе");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case MENU_ABOUT_ID:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            case MENU_SETTING_ID:
                Intent intent1 = new Intent(getBaseContext(), SettingActivity.class);
                startActivity(intent1);
                break;
            case MENU_TIPS_ID:
                Intent intent12 = new Intent(getBaseContext(), TipsActivity.class);
                startActivity(intent12);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    public void button(View view) {

        //Изменение кнопки в зависимости от состояния приложения
        switch (flag){
            case 1:
                if (mSettings.contains(APP_PREFERENCES_FIRST)) {
                    startApp();
                }
                else{//Настройка времени при первом запуске
                    newFragment = new MainActivity.SelectTimeFragment();
                    newFragment.show(getSupportFragmentManager(), "TimePicker");
                    firstStart = 1;
                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putInt(APP_PREFERENCES_FIRST, firstStart);
                    editor.apply();
                }
                break;
            case 2:
                if (alarmManager != null) {
                    myPendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                    alarmManager.cancel(myPendingIntent);
                    myPendingIntent.cancel();
                    Toast.makeText(this, "Приложение остановлено", Toast.LENGTH_LONG).show();
                    txTittle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico2, 0, 0, 0);
                    imageView.setBackgroundResource(R.drawable.animbutton_description_return);
                    flag = 1;
                    anim = (AnimationDrawable) imageView.getBackground();
                    anim.setOneShot(true);
                    anim.stop();
                    anim.start();
                }
                break;
        }
    }

    public void startApp(){
        Intent intentService = new Intent(MainActivity.this, ServiceEx.class);
        startService(intentService);
        txTittle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico1, 0, 0, 0);
        imageView.setBackgroundResource(R.drawable.animbutton_description);
        flag = 2;
        anim = (AnimationDrawable) imageView.getBackground();
        anim.setOneShot(true);
        anim.stop();
        anim.start();
    }

    public void populateSetTime(int hourOfDay, int minute) {

        this.hourOfDay = hourOfDay;
        this.minute = minute;
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_hourOfDay, hourOfDay);
        editor.putInt(APP_PREFERENCES_minute, minute);
        editor.apply();
        startApp();
    }

    @SuppressLint("ValidFragment")
    public class SelectTimeFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);;
            minute = calendar.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hourOfDay, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            populateSetTime(hourOfDay, minute);
        }
    }


}