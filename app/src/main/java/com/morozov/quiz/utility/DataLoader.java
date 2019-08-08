package com.morozov.quiz.utility;

import android.content.res.AssetManager;

import com.morozov.quiz.controller.models.QuestionModel;
import com.morozov.quiz.controller.models.SectionModel;
import com.morozov.quiz.controller.models.SubsectionModel;
import com.morozov.quiz.controller.models.TopicModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class DataLoader {

    public static ArrayList<SectionModel> getSections(AssetManager manager) {
        ArrayList<SectionModel> sectionList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(loadJson(manager, AppConstants.SECTION_FILE));
            JSONArray jsonArray = jsonObject.getJSONArray(AppConstants.JSON_KEY_ITEMS);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                String sectionId = object.getString(AppConstants.JSON_KEY_SECTION_ID);
                String sectionName = object.getString(AppConstants.JSON_KEY_SECTION_NAME);

                sectionList.add(new SectionModel(sectionId, sectionName));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sectionList;
    }

    public static ArrayList<SubsectionModel> getSubsections(AssetManager manager, String mSectionId) {
        ArrayList<SubsectionModel> subsectionList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(loadJson(manager, AppConstants.SUBSECTION_FILE));
            JSONArray jsonArray = jsonObject.getJSONArray(AppConstants.JSON_KEY_ITEMS);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                String sectionId = object.getString(AppConstants.JSON_KEY_SECTION_ID);
                String subsectionId = object.getString(AppConstants.JSON_KEY_SUBSECTION_ID);
                String subsectionName = object.getString(AppConstants.JSON_KEY_SUBSECTION_NAME);

                if (sectionId.equals(mSectionId))
                    subsectionList.add(new SubsectionModel(subsectionId, subsectionName));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return subsectionList;
    }

    public static ArrayList<TopicModel> getTopics(AssetManager manager, String mSubsectionId) {
        ArrayList<TopicModel> topicList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(loadJson(manager, AppConstants.TOPIC_FILE));
            JSONArray jsonArray = jsonObject.getJSONArray(AppConstants.JSON_KEY_ITEMS);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                String subsectionId = object.getString(AppConstants.JSON_KEY_SUBSECTION_ID);
                String topicId = object.getString(AppConstants.JSON_KEY_TOPIC_ID);
                String topicName = object.getString(AppConstants.JSON_KEY_TOPIC_NAME);

                if (subsectionId.equals(mSubsectionId))
                    topicList.add(new TopicModel(topicId, topicName));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return topicList;
    }

    public static ArrayList<QuestionModel> getQuestions(AssetManager manager, String mTopicId) {
        ArrayList<QuestionModel> questionList = new ArrayList<>();

        try {
            JSONObject jsonObjMain = new JSONObject(loadJson(manager, AppConstants.QUESTION_FILE));
            JSONArray jsonArray = jsonObjMain.getJSONArray(AppConstants.JSON_KEY_QUESTIONNAIRY);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                int isImageQuestion = Integer.parseInt(jsonObj.getString(AppConstants.JSON_KEY_IS_IMAGE_QUESTION));
                int isImageAnswer = Integer.parseInt(jsonObj.getString(AppConstants.JSON_KEY_IS_ANSWER_IMAGES));
                QuestionModel questionModel;

                if (isImageAnswer == 0 && isImageQuestion == 0) {


                    questionModel = new QuestionModel(false, false);

                    String question = jsonObj.getString(AppConstants.JSON_KEY_QUESTION);
                    questionModel.setQuestion(question);

                    JSONArray jsonArray2 = jsonObj.getJSONArray(AppConstants.JSON_KEY_ANSWERS);
                    ArrayList<String> contents = new ArrayList<>();
                    for (int j = 0; j < jsonArray2.length(); j++) {
                        String item_title = jsonArray2.get(j).toString();
                        contents.add(item_title);
                    }
                    questionModel.setAnswers(contents);

                    int correctAnswerInt = Integer.parseInt(jsonObj.getString(AppConstants.JSON_KEY_CORRECT_ANS));
                    questionModel.setCorrectAnswer(contents.get(correctAnswerInt));

                    String topicId = jsonObj.getString(AppConstants.JSON_KEY_TOPIC_ID);
                    if (mTopicId.equals(topicId)) {
                        questionList.add(questionModel);
                        Collections.shuffle(contents);
                    }


                } else if (isImageAnswer == 1 && isImageQuestion == 1) {


                    questionModel = new QuestionModel(true, true);

                    String question = jsonObj.getString(AppConstants.JSON_KEY_QUESTION);
                    questionModel.setQuestion(question);

                    String questionImage = jsonObj.getString(AppConstants.JSON_KEY_QUESTION_IMAGE);
                    questionModel.setQuestionImage(questionImage);

                    JSONArray jsonArray2 = jsonObj.getJSONArray(AppConstants.JSON_KEY_ANSWER_IMAGES);
                    ArrayList<String> contents = new ArrayList<>();
                    for (int j = 0; j < jsonArray2.length(); j++) {
                        String item_title = jsonArray2.get(j).toString();
                        contents.add(item_title);
                    }
                    questionModel.setAnswerImages(contents);

                    int correctAnswerInt = Integer.parseInt(jsonObj.getString(AppConstants.JSON_KEY_CORRECT_ANS));
                    questionModel.setCorrectAnswer(contents.get(correctAnswerInt));

                    String topicId = jsonObj.getString(AppConstants.JSON_KEY_TOPIC_ID);
                    if (mTopicId.equals(topicId)) {
                        questionList.add(questionModel);
                        Collections.shuffle(contents);
                    }


                } else if (isImageAnswer == 0 && isImageQuestion == 1) {


                    questionModel = new QuestionModel(false, true);

                    String question = jsonObj.getString(AppConstants.JSON_KEY_QUESTION);
                    questionModel.setQuestion(question);

                    JSONArray jsonArray2 = jsonObj.getJSONArray(AppConstants.JSON_KEY_ANSWER_IMAGES);
                    ArrayList<String> contents = new ArrayList<>();
                    for (int j = 0; j < jsonArray2.length(); j++) {
                        String item_title = jsonArray2.get(j).toString();
                        contents.add(item_title);
                    }
                    questionModel.setAnswerImages(contents);

                    int correctAnswerInt = Integer.parseInt(jsonObj.getString(AppConstants.JSON_KEY_CORRECT_ANS));
                    questionModel.setCorrectAnswer(contents.get(correctAnswerInt));

                    String topicId = jsonObj.getString(AppConstants.JSON_KEY_TOPIC_ID);
                    if (mTopicId.equals(topicId)) {
                        questionList.add(questionModel);
                        Collections.shuffle(contents);
                    }


                } else if (isImageAnswer == 1 && isImageQuestion == 0) {


                    questionModel = new QuestionModel(true, false);

                    String question = jsonObj.getString(AppConstants.JSON_KEY_QUESTION);
                    questionModel.setQuestion(question);

                    String questionImage = jsonObj.getString(AppConstants.JSON_KEY_QUESTION_IMAGE);
                    questionModel.setQuestionImage(questionImage);

                    JSONArray jsonArray2 = jsonObj.getJSONArray(AppConstants.JSON_KEY_ANSWERS);
                    ArrayList<String> contents = new ArrayList<>();
                    for (int j = 0; j < jsonArray2.length(); j++) {
                        String item_title = jsonArray2.get(j).toString();
                        contents.add(item_title);
                    }
                    questionModel.setAnswers(contents);

                    int correctAnswerInt = Integer.parseInt(jsonObj.getString(AppConstants.JSON_KEY_CORRECT_ANS));
                    questionModel.setCorrectAnswer(contents.get(correctAnswerInt));

                    String topicId = jsonObj.getString(AppConstants.JSON_KEY_TOPIC_ID);
                    if (mTopicId.equals(topicId)) {
                        questionList.add(questionModel);
                        Collections.shuffle(contents);
                    }


                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return questionList;
    }

    private static String loadJson(AssetManager manager, String fileName) {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try{
            br = new BufferedReader(new InputStreamReader(manager.open(fileName)));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
