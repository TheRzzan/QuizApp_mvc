package com.morozov.quiz.utility;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class ClearResultsBroadcastReceiver extends BroadcastReceiver {

    private static final Integer REQUEST_CODE = 123;

    public static void startIfNotStarter(Context context) {
        Intent broadcastIntent = new Intent(context, ClearResultsBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE, broadcastIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        Object alarmService = context.getSystemService(Context.ALARM_SERVICE);
        if (alarmService instanceof AlarmManager) {
            Calendar time = Calendar.getInstance();
            time.add(Calendar.SECOND, 10);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)  {
                ((AlarmManager) alarmService).setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time.getTimeInMillis() , pendingIntent);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)  {
                AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(time.getTimeInMillis(), pendingIntent);
                ((AlarmManager) alarmService).setAlarmClock(alarmClockInfo, pendingIntent);
            } else {
                ((AlarmManager) alarmService).setExact(AlarmManager.RTC_WAKEUP, time.getTimeInMillis() , pendingIntent);
            }
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Received", Toast.LENGTH_SHORT).show();
    }
}
