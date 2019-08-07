package com.morozov.quiz.controller.app.section;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.models.SectionModel;
import com.morozov.quiz.controller.ui.ListAdapter;

class SectionAdapter extends ListAdapter<SectionModel, SectionViewHolder> {
    private final LayoutInflater inflater;
    private final View.OnClickListener listener;

    SectionAdapter(Context context, View.OnClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SectionViewHolder(inflater.inflate(R.layout.item_section, null));
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int i) {
        holder.populate(data().get(i));
        holder.setOnClick(listener, i);
    }
}
