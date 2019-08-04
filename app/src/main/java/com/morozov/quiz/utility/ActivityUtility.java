package com.morozov.quiz.utility;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.morozov.quiz.controller.app.subsection.SubsectionActivity;
import com.morozov.quiz.controller.app.topic.TopicActivity;

public class ActivityUtility {
    public static void invokeNewActivity(Activity activity, Class<?> tClass, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public static void invokeSubsectionActivity(Activity activity, boolean shouldFinish, String sectionId) {
        Toast.makeText(activity, sectionId, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(activity, SubsectionActivity.class);
        intent.putExtra(AppConstants.JSON_KEY_SECTION_ID, sectionId);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public static void invokeTopicActivity(Activity activity, boolean shouldFinish, String subsectionId, String sectionId) {
        Toast.makeText(activity, subsectionId, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(activity, TopicActivity.class);

        intent.putExtra(AppConstants.JSON_KEY_SUBSECTION_ID, subsectionId);
        intent.putExtra(AppConstants.JSON_KEY_SECTION_ID, sectionId);

        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }
}
