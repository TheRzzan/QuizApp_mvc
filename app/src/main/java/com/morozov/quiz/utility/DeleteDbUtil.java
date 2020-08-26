package com.morozov.quiz.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Calendar;

import io.realm.Realm;

public class DeleteDbUtil {

    private static final String TIME_TABLE = "com.quiz.time.TABLE";
    private static final String TIME_VALUE = "com.quiz.time.VALUE";

    private static final Long TIME_EMPTY = -123L;

    public static void deleteOrSet(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(TIME_TABLE, Context.MODE_PRIVATE);
        Long savedTime = preferences.getLong(TIME_VALUE, TIME_EMPTY);
        if (savedTime.equals(TIME_EMPTY)) {
            setNextTime(preferences);
        } else {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime >= savedTime) {
                setNextTime(preferences);
                Realm.getDefaultInstance().executeTransaction( realm -> realm.deleteAll());
            }
        }
    }

    private static void setNextTime(SharedPreferences preferences) {
        Calendar nextDeleteTime = Calendar.getInstance();
        nextDeleteTime.add(Calendar.MONTH, 1);
        preferences.edit().putLong(TIME_VALUE, nextDeleteTime.getTimeInMillis()).apply();
    }
}
