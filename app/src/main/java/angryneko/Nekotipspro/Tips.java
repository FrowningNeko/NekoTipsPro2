package angryneko.Nekotipspro;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;

import java.util.Random;

public class Tips extends Service {
    int tips22;
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES_TS = "12345";


    public void onCreate() {
        super.onCreate();
        mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        tips22 = mSettings.getInt(APP_PREFERENCES_TS, 32);
        if(tips22<=32){
            tips22++;

        }
        else {
            tips22=0;


        }
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_TS, tips22);
        editor.commit();
        stopSelf();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
