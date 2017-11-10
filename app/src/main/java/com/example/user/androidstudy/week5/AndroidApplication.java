package com.example.user.androidstudy.week5;

import android.app.Application;
import android.content.Context;

/**
 * Created by USER on 2017-07-23.
 */

public class AndroidApplication extends Application {

    private static Context applicationContext;

    @Override
    public void onCreate(){
        super.onCreate();
        applicationContext=getApplicationContext();
    }

    public static Context getContext(){
        return applicationContext;
    }
}