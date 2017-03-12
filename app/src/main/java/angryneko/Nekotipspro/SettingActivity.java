package angryneko.Nekotipspro;


import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceClickListener;



public class SettingActivity extends PreferenceActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(angryneko.Nekotipspro.R.xml.preferences);
        // Get the custom preference

        Preference customPref = (Preference) findPreference("customPref");
        customPref
                .setOnPreferenceClickListener(new OnPreferenceClickListener() {

                    public boolean onPreferenceClick(Preference preference) {
                        Intent intent = new Intent(getBaseContext(), AboutActivity.class);
                        startActivity(intent);
                        return true;
                    }

                });
        Preference customLurk = (Preference) findPreference("customLurk");
        customLurk
                .setOnPreferenceClickListener(new OnPreferenceClickListener() {

                    public boolean onPreferenceClick(Preference preference) {
                        Intent intent1 = new Intent(getBaseContext(), LurkingNeko.class);
                        startActivity(intent1);
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
