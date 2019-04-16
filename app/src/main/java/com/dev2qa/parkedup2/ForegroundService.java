package com.dev2qa.parkedup2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import static com.dev2qa.parkedup2.ParkedActivity.CHANNEL_ID;

public class ForegroundService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent notificationIntent = new Intent(this, ParkedActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        createNotificationChannel();
        if(MenuActivity.getNotify()) {
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("ParkedUp!")
                    .setContentText("Your parking spot has been saved!")
                    .setSmallIcon(R.mipmap.ic_launcher_foreground)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_foreground))
                    .setContentIntent(pendingIntent)
                    .setChannelId("2")
                    .build();
            startForeground(1, notification);
        }
        //do heavy work on a background thread
        //stopSelf();

        //Experiment with these three options if not working correctly
        //return START_NOT_STICKY
        //return START_REDELIVER_INTENT
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = getString(R.string.common_google_play_services_notification_channel_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel("1", name, importance);
            NotificationChannel channel = new NotificationChannel("2", CHANNEL_ID, importance);
            channel.setDescription("1");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
