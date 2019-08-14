package com.morozov.quiz.utility;

import android.content.Context;

import com.morozov.quiz.R;

public class ActivityTitles {

    private static ActivityTitles instance;
    private Context context;

    private String airplaneName;
    private String sectionName;
    private String subsectionName;
    private String topicName;

    private ActivityTitles(Context context) {
        this.context = context;
    }

    public static ActivityTitles getInstance(Context context) {
        if (instance == null)
            instance = new ActivityTitles(context);
        return instance;
    }

    public String getSectionName() {
        if (sectionName == null)
            sectionName = context.getResources().getString(R.string.answers);

        return sectionName;
    }

    public void setSectionName(String section) {
        this.sectionName = section;
    }

    public String getSubsectionName() {
        if (subsectionName == null)
            subsectionName = context.getResources().getString(R.string.subsection);

        return subsectionName;
    }

    public void setSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
    }

    public String getTopicName() {
        if (topicName == null)
            topicName = context.getResources().getString(R.string.topic);

        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getAirplaneName() {
        if (airplaneName == null)
            airplaneName = context.getString(R.string.airplane);
        
        return airplaneName;
    }

    public void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }
}
