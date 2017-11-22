package com.example.user.androidstudy;

import android.app.Application;
import android.content.Context;

import com.example.user.androidstudy.week9.MyDBMigration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by USER on 2017-07-23.
 */

public class AndroidApplication extends Application {

    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();


        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("osori.realm")
                .migration(new MyDBMigration())
                .schemaVersion(MyDBMigration.SCHEMA_VERSION)
                .build();
        Realm.setDefaultConfiguration(config);
    }


    public static Context getContext(){
        return applicationContext;
    }
}
