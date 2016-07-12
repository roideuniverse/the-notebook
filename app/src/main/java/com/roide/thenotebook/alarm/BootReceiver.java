package com.roide.thenotebook.alarm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.roide.thenotebook.R;
import com.roide.thenotebook.activities.MainActivity;

import java.util.Calendar;

/**
 * Created by roide on 9/6/15.
 */
public class BootReceiver extends BroadcastReceiver
{
    private static final String SHOW_NOTIFICATION = "com.roide.thenotebook.alarm";
    private static final int NOTIFICATION_ID = 001;
    private static final String TAG = BootReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.d(TAG, "OnReceive::" + intent);
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
            Log.d(TAG, "Boot_COMPLETE");
            for(int i=10;i<22; i++)
            {
                setAlarmNotification(context, i);
            }
        }
        else if(intent.getAction().equals(SHOW_NOTIFICATION))
        {
            showNotification(context);
        }
    }

    public void setAlarmNotification(Context context, int time)
    {
        Log.d(TAG, "setAlarm::time=" + time);

        Intent newIntent = new Intent(context, BootReceiver.class);
        newIntent.setAction(SHOW_NOTIFICATION);

        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, newIntent, 0);

        // Set the alarm here.
        // Set the alarm to start at approximately 8:00 p.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, time);
        //calendar.set(Calendar.MINUTE, 40);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
    }

    public void showNotification(Context context)
    {
        Log.d(TAG, "showNotification");
        Intent newIntent = new Intent(context, MainActivity.class);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("The Notebook")
                        .setContentText("Make an entry")
                        .setAutoCancel(true);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, newIntent, 0);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(NOTIFICATION_ID, notificationBuilder.build());
    }
}
