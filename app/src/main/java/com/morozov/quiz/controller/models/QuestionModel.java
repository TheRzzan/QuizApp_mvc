package com.morozov.quiz.controller.models;

import java.util.ArrayList;

public class QuestionModel {
    private Boolean isImageQuestion;
    private Boolean isImageAnswer;

    private String question;
    private String questionImage;
    private ArrayList<String> answers;
    private ArrayList<String> answerImages;
    private String correctAnswer;
    private Integer correctImage;

    public QuestionModel(Boolean isImageQuestion, Boolean isImageAnswer) {
        this.isImageQuestion = isImageQuestion;
        this.isImageAnswer = isImageAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setQuestionImage(String questionImage) {
        this.questionImage = questionImage;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public void setAnswerImages(ArrayList<String> answerImages) {
        this.answerImages = answerImages;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setCorrectImage(Integer correctImage) {
        this.correctImage = correctImage;
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

    public Boolean isImageQuestion() {
        return isImageQuestion;
    }

    public Boolean isImageAnswer() {
        return isImageAnswer;
    }

    public String getQuestionImage() {
        return questionImage;
    }

    public ArrayList<String> getAnswerImages() {
        if (answerImages == null)
            return new ArrayList<>();
        else
            return answerImages;
    }

    public Integer getCorrectImage() {
        return correctImage;
    }
}
