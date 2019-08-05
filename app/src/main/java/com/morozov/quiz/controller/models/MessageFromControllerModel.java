package com.morozov.quiz.controller.models;

public class MessageFromControllerModel {
    private Boolean isCorrectAnswer;
    private String correctAnswer;

    public MessageFromControllerModel(Boolean isCorrectAnswer, String correctAnswer) {
        this.isCorrectAnswer = isCorrectAnswer;
        this.correctAnswer = correctAnswer;
    }

    public Boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
