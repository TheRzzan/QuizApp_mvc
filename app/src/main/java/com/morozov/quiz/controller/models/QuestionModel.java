package com.morozov.quiz.controller.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuestionModel {
    private Boolean isImageQuestion;
    private Boolean isImageAnswer;

    private String question;
    private String questionImage;
    private ArrayList<String> answers;
    private ArrayList<String> answerImages;
    private String correctAnswer;
    private Integer correctImage;

    private ArrayList<Integer> indexArray;

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
        indexArray = new ArrayList<>();
        for (int i = 0; i < answers.size(); i++) {
            indexArray.add(i);
        }
        Collections.shuffle(indexArray);
        this.answers = answers;
    }

    public void setAnswerImages(ArrayList<String> answerImages) {
        indexArray = new ArrayList<>();
        for (int i = 0; i < answerImages.size(); i++) {
            indexArray.add(i);
        }
        Collections.shuffle(indexArray);
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
        ArrayList<String> newArs = new ArrayList<>();
        for (Integer integer : indexArray) {
            newArs.add(answers.get(integer));
        }
        return newArs;
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
        else {
            ArrayList<String> newArs = new ArrayList<>();
            for (Integer integer : indexArray) {
                newArs.add(answerImages.get(integer));
            }
            return newArs;
        }
    }

    public Integer getCorrectImage() {
        return correctImage;
    }
}
