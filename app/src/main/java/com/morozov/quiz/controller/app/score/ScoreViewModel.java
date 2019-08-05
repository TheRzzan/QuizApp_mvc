package com.morozov.quiz.controller.app.score;

import android.arch.lifecycle.MutableLiveData;

import com.morozov.quiz.controller.UiViewModel;
import com.morozov.quiz.controller.models.ScoreModel;

public class ScoreViewModel extends UiViewModel {
    private MutableLiveData<ScoreModel> score;

    private MutableLiveData<Boolean> showScore;

    private MutableLiveData<Boolean> startTestAgain;
    private MutableLiveData<Boolean> startMainMenu;

    private MutableLiveData<String> topicId;
    private MutableLiveData<String> subsectionId;
    private MutableLiveData<String> sectionId;

    public ScoreViewModel() {
        score = new MutableLiveData<>();

        showScore = new MutableLiveData<>();

        startTestAgain = new MutableLiveData<>();
        startMainMenu = new MutableLiveData<>();

        topicId = new MutableLiveData<>();
        subsectionId = new MutableLiveData<>();
        sectionId = new MutableLiveData<>();
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

    MutableLiveData<String> topicId() {
        return topicId;
    }
    MutableLiveData<String> subsectionId() {
        return subsectionId;
    }
    MutableLiveData<String> sectionId() {
        return sectionId;
    }
}
