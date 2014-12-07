package com.chipcerio.parse;

import android.app.Application;

import com.parse.Parse;

public class MyParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "tUXmGboIaUB2aKru1LMpyo7IQ8GzAW0NtPpCDy4P", "e19Ls1IxN0eqBJvnZy2PWB2LJk0Ngnie3NfnWXjR");
    }
}
