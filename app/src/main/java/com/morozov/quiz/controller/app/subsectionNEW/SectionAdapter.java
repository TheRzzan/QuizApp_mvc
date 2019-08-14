package com.morozov.quiz.controller.app.subsectionNEW;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.interaction.SubsectionClickListener;
import com.morozov.quiz.controller.models.SectionModel;
import com.morozov.quiz.controller.ui.ListAdapter;

class SectionAdapter extends ListAdapter<SectionModel, SectionViewHolder> {

    private final LayoutInflater inflater;
    private final SubsectionClickListener listener;

    SectionAdapter(Context context, SubsectionClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SectionViewHolder(inflater.inflate(R.layout.item_subsection, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int i) {
        holder.populate(listener, data().get(i));
    }
}
