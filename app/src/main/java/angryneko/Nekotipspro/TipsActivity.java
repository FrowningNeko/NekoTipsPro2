package angryneko.Nekotipspro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class TipsActivity extends AppCompatActivity {

    TextView tvTips;
    final int MENU_SETTING_ID = 3;
    final int MENU_ABOUT_ID = 2;
    int tips22;
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES_TS = "12345";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips);


        mSettings = PreferenceManager.getDefaultSharedPreferences(this);

        tips22 = mSettings.getInt(APP_PREFERENCES_TS, 0);

        tvTips = (TextView) findViewById(R.id.tvTips);
        String tips[] = getResources().getStringArray(R.array.Tips);
        tvTips.setText(
                tips[tips22]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(0, MENU_SETTING_ID, 0, "Настройки");
        menu.add(0, MENU_ABOUT_ID, 0, "О программе");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case MENU_ABOUT_ID:
                Intent intent = new Intent(TipsActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            case MENU_SETTING_ID:
                Intent intent1 = new Intent(getBaseContext(), SettingActivity.class);
                startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }


    }
