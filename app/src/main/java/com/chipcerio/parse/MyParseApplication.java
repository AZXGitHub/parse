package com.chipcerio.parse;

import android.app.Application;

import com.parse.Parse;

public class MyParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, Common.AppKey.APP_ID, Common.AppKey.CLIENT_KEY);
    }
}
