package angryneko.Nekotipspro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class OnBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences mSett = PreferenceManager.getDefaultSharedPreferences(context);
        if (mSett.getBoolean("checkboxPref", false)) {
            {
                if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
                    Intent serviceLauncher = new Intent(context, ServiceEx.class);
                    context.startService(serviceLauncher);
                }
            }
        }

    }
}

