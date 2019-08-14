package com.morozov.quiz.utility;

import android.app.Activity;
import android.content.Intent;

import com.morozov.quiz.controller.app.score.ScoreActivity;
import com.morozov.quiz.controller.models.ScoreModel;

public class ActivityUtility {
    public static void invokeNewActivity(Activity activity, Class<?> tClass, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public static void invokeScoreActivity(Activity activity, boolean shouldFinish, ScoreModel score) {
        Intent intent = new Intent(activity, ScoreActivity.class);

        intent.putExtra(AppConstants.BUNDLE_KEY_CORRECT_ANS, score.getCorrectAnswers());
        intent.putExtra(AppConstants.BUNDLE_KEY_WRONG_ANS, score.getWrongAnswers());
        intent.putExtra(AppConstants.BUNDLE_KEY_SKIPPED_ANS, score.getSkippedAnswers());

        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }
}
