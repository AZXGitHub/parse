package com.chipcerio.parse;

import static com.chipcerio.parse.Common.TAG;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SaveCallback;

public class MainActivity extends ActionBarActivity {

    interface Category {
        String TECHNOLOGY  = "5JimgqyJB1";
        String SPORTS      = "a3k1grX5pU";
        String EDUCATIONAL = "cENWL5By5v";
        String OTHERS      = "3btYSTqO7T";
    }

    interface Class {
        String CATEGORY = "/classes/Category/";
        String EVENT = "/classes/Event/";
    }

    @Override
    protected void onResume() {
        super.onResume();
        subscribe();
    }

    private void subscribe() {
        ParsePush.subscribeInBackground("hangouts", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i(TAG, "Subscribed");
                } else {
                    Log.i(TAG, "Failed");
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            subscribe();
        }
        setContentView(R.layout.activity_main);

        new AsyncTask<Void, Void, Response>() {
            @Override
            protected Response doInBackground(Void... params) {
                Request httpRequest = new Request(Class.CATEGORY + Category.TECHNOLOGY);
                return httpRequest.get();
            }

            @Override
            protected void onPostExecute(Response response) {
                super.onPostExecute(response);
                if (response.isSuccess()) {
                    Log.i(TAG, "isSuccess:" + response.isSuccess());
                    Log.d(TAG, "messageBody:" + response.getMessageBody());
                } else {
                    Log.e(TAG, "isSuccess:" + response.isSuccess());
                }
            }
        }.execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
