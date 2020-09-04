package com.morozov.quiz;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.morozov.quiz.controller.app.airplane.AirplaneActivity;
import com.morozov.quiz.controller.app.login.LoginActivity;
import com.morozov.quiz.utility.ActivityUtility;
import com.morozov.quiz.utility.AppConstants;
import com.morozov.quiz.utility.MySharedPreferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean isLogined = MySharedPreferences.getPreference(MainActivity.this, AppConstants.IS_LOGINED_ONCE);

        if (isLogined)
            ActivityUtility.invokeNewActivity(MainActivity.this, AirplaneActivity.class, true);
        else
            ActivityUtility.invokeNewActivity(MainActivity.this, LoginActivity.class, true);
    }
}
