package com.morozov.quiz.controller.app.answer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.models.TopicModel;
import com.morozov.quiz.controller.ui.ListAdapter;

public class TopicAdapter extends ListAdapter<TopicModel, TopicViewHolder> {
    private final LayoutInflater inflater;
    private final FragmentManager fragmentManager;

    TopicAdapter(Context activity, FragmentManager fragmentManager) {
        this.inflater = LayoutInflater.from(activity);
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TopicViewHolder(inflater.inflate(R.layout.item_answer_topic, null));
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int i) {
        holder.populate(fragmentManager, data().get(i), i + 1);
    }
}
