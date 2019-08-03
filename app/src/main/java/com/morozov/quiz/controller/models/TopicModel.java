package com.morozov.quiz.controller.models;

public class TopicModel {
    private String topicId;
    private String topicName;

    public TopicModel(String topicId, String topicName) {
        this.topicId = topicId;
        this.topicName = topicName;
    }

    public String getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }
}
