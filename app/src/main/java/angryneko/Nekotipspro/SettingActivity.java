package angryneko.Nekotipspro;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;


public class SettingActivity extends PreferenceActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        Preference customPref = (Preference) findPreference("customPref");
        customPref
                .setOnPreferenceClickListener(new OnPreferenceClickListener() {

                    public boolean onPreferenceClick(Preference preference) {
                        Intent intent = new Intent(getBaseContext(), AboutActivity.class);
                        startActivity(intent);
                        return true;
                    }

                });
        Preference customTips= (Preference) findPreference("customTips");
        customTips
                .setOnPreferenceClickListener(new OnPreferenceClickListener() {

                    public boolean onPreferenceClick(Preference preference) {
                        Intent intent12 = new Intent(getBaseContext(), TipsActivity.class);
                        startActivity(intent12);
                        return true;
                    }

                });

        Preference customTime= (Preference) findPreference("customTime");
        customTime
                .setOnPreferenceClickListener(new OnPreferenceClickListener() {

                    public boolean onPreferenceClick(Preference preference) {
                        Intent intent12 = new Intent(getBaseContext(), TimeSetting.class);
                        startActivity(intent12);
                        return true;
                    }

                });
    }

}
