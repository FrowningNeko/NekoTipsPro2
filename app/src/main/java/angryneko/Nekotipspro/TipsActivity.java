package angryneko.Nekotipspro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class TipsActivity extends AppCompatActivity {

    TextView tvTips;
    String ListPreference;
    private SharedPreferences mSett;
    final int MENU_SETTING_ID = 3;
    final int MENU_ABOUT_ID = 2;
    final int MENU_QUIT_ID = 1;
    public static final String APP_PREFERENCES_TS = "12345";
    int tips22;
    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(angryneko.Nekotipspro.R.layout.tips);
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metricsB = new DisplayMetrics();
        display.getMetrics(metricsB);

        if(metricsB.widthPixels < 1024 && metricsB.heightPixels < 600){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        mSett = PreferenceManager.getDefaultSharedPreferences(this);
        mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        tvTips = (TextView) findViewById(angryneko.Nekotipspro.R.id.tvTips);
        String tips[] = getResources().getStringArray(angryneko.Nekotipspro.R.array.Tips);


        tips22 = mSettings.getInt(APP_PREFERENCES_TS, 1);
        tvTips.setText(
                tips[tips22]);

        ListPreference = mSett.getString("listPref", "0");
        int m = Integer.parseInt(ListPreference);
        switch (m) {
            case 1:
                tvTips.setTextSize(16);
                break;
            case 2:
                tvTips.setTextSize(18);
                break;
            case 3:
                tvTips.setTextSize(20);
                break;
            case 4:
                tvTips.setTextSize(22);
                break;
            case 0:
                tvTips.setTextSize(18);
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(0, MENU_SETTING_ID, 0, "Настройки");
        menu.add(0, MENU_ABOUT_ID, 0, "О программе");
        menu.add(0, MENU_QUIT_ID, 0, "Выйти");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case MENU_ABOUT_ID:
                Intent intent = new Intent(TipsActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            case MENU_QUIT_ID:
                finish();
                break;
            case MENU_SETTING_ID:
                Intent intent1 = new Intent(getBaseContext(), SettingActivity.class);
                startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onResume() {

        ListPreference = mSett.getString("listPref", "0"); //Устанавливаем шрифт в зависимости от настроек
        int m = Integer.parseInt(ListPreference);
        switch (m) {
            case 1:
                tvTips.setTextSize(16);
                break;
            case 2:
                tvTips.setTextSize(18);
                break;
            case 3:
                tvTips.setTextSize(20);
                break;
            case 4:
                tvTips.setTextSize(22);
                break;
            case 0:
                tvTips.setTextSize(18);
                break;
        }
        super.onResume();
    }
}
