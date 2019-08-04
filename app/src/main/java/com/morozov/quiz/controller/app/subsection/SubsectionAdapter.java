package com.morozov.quiz.controller.app.subsection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.models.SubsectionModel;
import com.morozov.quiz.controller.ui.ListAdapter;

class SubsectionAdapter extends ListAdapter<SubsectionModel, SubsectionViewHolder> {
    private final LayoutInflater inflater;
    private final View.OnClickListener listener;

    SubsectionAdapter(Context context, View.OnClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public SubsectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SubsectionViewHolder(inflater.inflate(R.layout.item_text_view_recycler, null));
    }

    @Override
    public void onBindViewHolder(@NonNull SubsectionViewHolder holder, int i) {
        holder.populate(data().get(i));
        holder.setOnClick(listener, i);
    }
}
