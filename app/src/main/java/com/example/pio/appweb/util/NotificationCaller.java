package com.example.pio.appweb.util;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;

import com.example.pio.appweb.R;

/**
 * Created by pio on 03.09.15.
 */
public  class NotificationCaller {

    private Notification notification;
    private int id = 0;
    private Activity activity;

    public NotificationCaller(Activity activity) {
        this.activity = activity;
    }

    public void setTitleAndContent(String title, String content) {

        notification = new Notification.Builder(activity)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentText(content)
                .setSmallIcon(R.drawable.calendar_icon)
                .setAutoCancel(true).getNotification();
    }

    public void showNotification() {
        NotificationManager manager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(id, notification);
        id++;
    }
}