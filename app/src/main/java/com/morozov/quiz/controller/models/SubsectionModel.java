package com.morozov.quiz.controller.models;

public class SubsectionModel {
    private String subsectionId;
    private String subsectionName;
    private String answersPdf;

    public SubsectionModel(String subsectionId, String subsectionName, String answersPdf) {
        this.subsectionId = subsectionId;
        this.subsectionName = subsectionName;
        this.answersPdf = answersPdf;
    }

    public String getSubsectionId() {
        return subsectionId;
    }

    public String getSubsectionName() {
        return subsectionName;
    }

    public String getAnswersPdf() {
        return answersPdf;
    }
}
