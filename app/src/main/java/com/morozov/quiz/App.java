package com.morozov.quiz;

import android.app.Application;

import com.morozov.quiz.utility.DeleteDbUtil;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
        DeleteDbUtil.deleteOrSet(getApplicationContext());
    }
}
