package com.morozov.quiz.controller.app.airplane;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.models.AirplaneModel;
import com.morozov.quiz.controller.ui.ListAdapter;

public class AirplaneAdapter extends ListAdapter<AirplaneModel, AirplaneViewHolder> {
    private final LayoutInflater inflater;
    private final View.OnClickListener listener;

    AirplaneAdapter(Context context, View.OnClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public AirplaneViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AirplaneViewHolder(inflater.inflate(R.layout.item_section, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AirplaneViewHolder holder, int i) {
        holder.populate(data().get(i));
        holder.setOnClick(listener, i);
    }
}
