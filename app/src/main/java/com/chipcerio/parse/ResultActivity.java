package com.chipcerio.parse;

import static com.chipcerio.parse.Common.TAG;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.List;

public class ResultActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Result");
        }

        String data = getIntent().getStringExtra(MyBroadcastReceiver.EXTRA_DATA);
        Log.i(TAG, data);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Event");
        query.whereGreaterThan("event_date", new Date());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> events, ParseException e) {
                if (e != null) return;
                Log.i(TAG, "total events:" + events.size());
            }
        });

    }
}
