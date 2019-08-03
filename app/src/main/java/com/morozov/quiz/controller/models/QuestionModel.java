package com.morozov.quiz.controller.models;

import java.util.ArrayList;

public class QuestionModel {
    private String question;
    private ArrayList<String> answers;
    private String correctAnswer;

    public QuestionModel(String question, ArrayList<String> answers, String correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
