package com.morozov.quiz.controller.models;

public class ScoreModel {
    private Integer correctAnswers;
    private Integer wrongAnswers;
    private Integer skippedAnswers;

    public ScoreModel(Integer correctAnswers, Integer wrongAnswers, Integer skippedAnswers) {
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
        this.skippedAnswers = skippedAnswers;
    }

    public Integer getCorrectAnswers() {
        return correctAnswers;
    }

    public Integer getWrongAnswers() {
        return wrongAnswers;
    }

    public Integer getSkippedAnswers() {
        return skippedAnswers;
    }
}
