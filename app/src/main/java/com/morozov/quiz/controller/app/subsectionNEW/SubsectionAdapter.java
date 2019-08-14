package com.morozov.quiz.controller.app.subsectionNEW;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.interaction.SubsectionClickListener;
import com.morozov.quiz.controller.models.SubsectionModel;
import com.morozov.quiz.controller.ui.ListAdapter;

class SubsectionAdapter extends ListAdapter<SubsectionModel, SubsectionViewHolder> {

    private final LayoutInflater inflater;
    private final SubsectionClickListener listener;

    private final int sectionId;

    SubsectionAdapter(Context context, SubsectionClickListener listener, String sectionIdStr) {
        inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.sectionId = Integer.parseInt(sectionIdStr);
    }

    @NonNull
    @Override
    public SubsectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SubsectionViewHolder(inflater.inflate(R.layout.item_text_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubsectionViewHolder holder, int i) {
        holder.populate(data().get(i));
        holder.setOnClick(listener, sectionId, Integer.parseInt(data().get(i).getSubsectionId()), data().get(i).getSubsectionName());
    }

}
