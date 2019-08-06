package com.morozov.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.morozov.quiz.controller.app.section.SectionActivity;
import com.morozov.quiz.utility.ActivityUtility;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtility.invokeNewActivity(MainActivity.this, SectionActivity.class, true);
    }
}
