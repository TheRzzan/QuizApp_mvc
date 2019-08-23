package com.morozov.quiz.controller.models;

public class SubsectionModel {
    private String subsectionId;
    private String subsectionName;

    public SubsectionModel(String subsectionId, String subsectionName) {
        this.subsectionId = subsectionId;
        this.subsectionName = subsectionName;
    }

    public String getSubsectionId() {
        return subsectionId;
    }

    public String getSubsectionName() {
        return subsectionName;
    }
}
