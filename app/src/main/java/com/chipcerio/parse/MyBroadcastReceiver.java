package com.chipcerio.parse;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

public class MyBroadcastReceiver extends ParsePushBroadcastReceiver {
    private static final String TAG = "CHIPCERIO";
    public static final String EXTRA_DATA = "com.chipcerio.parse.DATA";

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        super.onPushReceive(context, intent);
        Log.i(TAG, "onPushReceive");

        String data = intent.getExtras().getString(KEY_PUSH_DATA);
        Log.i(TAG, "data:" + data); // json

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("You have a message")
                .setContentText("Click to view")
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true);

        Intent resultIntent = new Intent(context, ResultActivity.class);
        resultIntent.putExtra(EXTRA_DATA, data);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                context,
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        builder.setContentIntent(resultPendingIntent);

        int notificationId = 1;
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(notificationId, builder.build());

    }

}
