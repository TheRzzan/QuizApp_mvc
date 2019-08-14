package com.morozov.quiz.controller.app.score;

import android.arch.lifecycle.MutableLiveData;

import com.morozov.quiz.controller.UiViewModel;
import com.morozov.quiz.controller.models.ScoreModel;

public class ScoreViewModel extends UiViewModel {
    private MutableLiveData<ScoreModel> score;

    private MutableLiveData<Boolean> showScore;

    private MutableLiveData<Boolean> startTestAgain;
    private MutableLiveData<Boolean> startMainMenu;

    public ScoreViewModel() {
        score = new MutableLiveData<>();

        showScore = new MutableLiveData<>();

        startTestAgain = new MutableLiveData<>();
        startMainMenu = new MutableLiveData<>();
    }

    MutableLiveData<ScoreModel> score() {
        return score;
    }

    MutableLiveData<Boolean> showScore() {
        return showScore;
    }

    MutableLiveData<Boolean> startTestAgain() {
        return startTestAgain;
    }
    MutableLiveData<Boolean> startMainMenu() {
        return startMainMenu;
    }
}
