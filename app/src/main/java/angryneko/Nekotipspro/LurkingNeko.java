package angryneko.Nekotipspro;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class LurkingNeko extends AppCompatActivity {

    private SharedPreferences mSett;
    String ListPreference;
    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(angryneko.Nekotipspro.R.layout.lurking_neko);
        mSett = PreferenceManager.getDefaultSharedPreferences(this);
        tv1 = (TextView)findViewById(angryneko.Nekotipspro.R.id.textView6);
        tv2 = (TextView)findViewById(angryneko.Nekotipspro.R.id.textView8);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == angryneko.Nekotipspro.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onResume() {

        ListPreference = mSett.getString("listPref", "0");
        int m = Integer.parseInt(ListPreference);
        switch (m) {
            case 1:
                tv1.setTextSize(16);
                tv2.setTextSize(16);
                break;
            case 2:
                tv1.setTextSize(18);
                tv2.setTextSize(18);
                break;
            case 3:
                tv1.setTextSize(20);
                tv2.setTextSize(20);
                break;
            case 4:
                tv1.setTextSize(22);
                tv2.setTextSize(22);
                break;
            case 0:
                tv1.setTextSize(18);
                tv2.setTextSize(18);
                break;
        }
        super.onResume();}
}
