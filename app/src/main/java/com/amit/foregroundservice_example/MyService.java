package com.amit.foregroundservice_example;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

/**
 * Created by dell on 5/17/2018.
 */

public class MyService extends IntentService {

    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        //Build a notification
        Intent notification_intent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, notification_intent, 0);

        Notification notificationCompat = new NotificationCompat.Builder(this, "DEFAULT_CHANNEL")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Foreground Notification")
                .setContentText("More details of foreground notification")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        // Issue the notification.
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(99, notificationCompat);

        startForeground(99, notificationCompat);

        for(int i=0; i<30; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
