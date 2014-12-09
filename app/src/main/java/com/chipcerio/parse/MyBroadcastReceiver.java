package com.chipcerio.parse;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

public class MyBroadcastReceiver extends ParsePushBroadcastReceiver {
    private static final String TAG = "CHIPCERIO";

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        super.onPushReceive(context, intent);
        Log.i(TAG, "onPushReceive");

        String data = intent.getExtras().getString(KEY_PUSH_DATA);
        Log.i(TAG, "data:" + data); // json

    }

}
