package com.appizona.yehiahd.fastsaveexample;

import android.app.Application;

import com.appizona.yehiahd.fastsave.FastSave;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FastSave.init(getApplicationContext());
    }
}
