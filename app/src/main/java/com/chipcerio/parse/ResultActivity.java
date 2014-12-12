package com.chipcerio.parse;

import static com.chipcerio.parse.Common.TAG;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class ResultActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Result");
        }

        String data = getIntent().getStringExtra(MyBroadcastReceiver.EXTRA_DATA);
        Log.i(TAG, data);

    }
}
