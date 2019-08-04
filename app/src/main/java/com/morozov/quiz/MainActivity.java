package com.morozov.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.morozov.quiz.controller.app.section.SectionActivity;
import com.morozov.quiz.utility.ActivityUtility;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityUtility.invokeNewActivity(MainActivity.this, SectionActivity.class, true);
    }
}
