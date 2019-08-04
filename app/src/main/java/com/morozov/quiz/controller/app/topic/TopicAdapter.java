package com.morozov.quiz.controller.app.topic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.models.TopicModel;
import com.morozov.quiz.controller.ui.ListAdapter;

public class TopicAdapter extends ListAdapter<TopicModel, TopicViewHolder> {
    private final LayoutInflater inflater;
    private final View.OnClickListener listener;

    TopicAdapter(Context context, View.OnClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TopicViewHolder(inflater.inflate(R.layout.item_text_view_recycler, null));
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int i) {
        holder.populate(data().get(i));
        holder.setOnClick(listener, i);
    }
}
