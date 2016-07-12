package com.roide.thenotebook.app;

import android.app.Application;
import android.content.ComponentName;
import android.content.pm.PackageManager;

import com.roide.thenotebook.alarm.BootReceiver;
import com.roide.thenotebook.backend.StorageManager;

/**
 * Created by roide on 9/5/15.
 */
public class TheNotebook extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        StorageManager.initialize(getApplicationContext());
        enableReceiver();
        BootReceiver receiver = new BootReceiver();
        receiver.setAlarmNotification(getApplicationContext(), 19);
    }

    private void enableReceiver()
    {
        ComponentName receiver = new ComponentName(getApplicationContext(), BootReceiver.class);
        PackageManager pm = getApplicationContext().getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}
