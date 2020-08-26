package com.morozov.quiz.controller.models;

public class TopicModel {
    private String topicId;
    private String topicName;
    private Integer percentage;

    public TopicModel(String topicId, String topicName, Integer percentage) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.percentage = percentage;
    }

    public String getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public Integer getPercentage() {
        return percentage;
    }
}
