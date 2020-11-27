package com.deepak.broadcastreceiverexample.recievers;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.deepak.broadcastreceiverexample.App;
import com.deepak.broadcastreceiverexample.R;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, App.ALARM_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText("Recieved Alarm")
                ;
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if(notificationManager!=null)
            notificationManager.notify(10,builder.build());
    }
}
