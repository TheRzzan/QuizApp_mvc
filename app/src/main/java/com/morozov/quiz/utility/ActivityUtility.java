package com.morozov.quiz.utility;

import android.app.Activity;
import android.content.Intent;

import com.morozov.quiz.controller.app.quiz.QuizActivity;
import com.morozov.quiz.controller.app.score.ScoreActivity;
import com.morozov.quiz.controller.app.subsection.SubsectionActivity;
import com.morozov.quiz.controller.app.topic.TopicActivity;
import com.morozov.quiz.controller.models.ScoreModel;

public class ActivityUtility {
    public static void invokeNewActivity(Activity activity, Class<?> tClass, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public static void invokeSubsectionActivity(Activity activity, boolean shouldFinish, String sectionId, boolean isToTest) {
        Intent intent = new Intent(activity, SubsectionActivity.class);

        intent.putExtra(AppConstants.BUNDLE_KEY_IS_TO_TEST, isToTest);
        intent.putExtra(AppConstants.JSON_KEY_SECTION_ID, sectionId);

        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public static void invokeTopicActivity(Activity activity, boolean shouldFinish, String subsectionId, String sectionId, boolean isToTest) {
        Intent intent = new Intent(activity, TopicActivity.class);

        intent.putExtra(AppConstants.BUNDLE_KEY_IS_TO_TEST, isToTest);
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

    public static void invokeScoreActivity(Activity activity, boolean shouldFinish, String topicId, String subsectionId, String sectionId, ScoreModel score) {
        Intent intent = new Intent(activity, ScoreActivity.class);

        intent.putExtra(AppConstants.BUNDLE_KEY_CORRECT_ANS, score.getCorrectAnswers());
        intent.putExtra(AppConstants.BUNDLE_KEY_WRONG_ANS, score.getWrongAnswers());
        intent.putExtra(AppConstants.BUNDLE_KEY_SKIPPED_ANS, score.getSkippedAnswers());

        intent.putExtra(AppConstants.JSON_KEY_TOPIC_ID, topicId);
        intent.putExtra(AppConstants.JSON_KEY_SUBSECTION_ID, subsectionId);
        intent.putExtra(AppConstants.JSON_KEY_SECTION_ID, sectionId);

        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }
}
