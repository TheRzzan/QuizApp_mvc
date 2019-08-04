package com.morozov.quiz.utility;

import android.app.Activity;
import android.content.Intent;

import com.morozov.quiz.controller.app.quiz.QuizActivity;
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
        Intent intent = new Intent(activity, SubsectionActivity.class);
        intent.putExtra(AppConstants.JSON_KEY_SECTION_ID, sectionId);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public static void invokeTopicActivity(Activity activity, boolean shouldFinish, String subsectionId, String sectionId) {
        Intent intent = new Intent(activity, TopicActivity.class);

        intent.putExtra(AppConstants.JSON_KEY_SUBSECTION_ID, subsectionId);
        intent.putExtra(AppConstants.JSON_KEY_SECTION_ID, sectionId);

        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public static void invokeQuizActivity(Activity activity, boolean shouldFinish, String topicId, String subsectionId, String sectionId) {
        Intent intent = new Intent(activity, QuizActivity.class);

        intent.putExtra(AppConstants.JSON_KEY_TOPIC_ID, topicId);
        intent.putExtra(AppConstants.JSON_KEY_SUBSECTION_ID, subsectionId);
        intent.putExtra(AppConstants.JSON_KEY_SECTION_ID, sectionId);

        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }
}
