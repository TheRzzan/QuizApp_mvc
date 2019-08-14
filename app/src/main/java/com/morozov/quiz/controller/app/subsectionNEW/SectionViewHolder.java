package com.morozov.quiz.controller.app.subsectionNEW;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.interaction.SubsectionClickListener;
import com.morozov.quiz.controller.models.SectionModel;
import com.morozov.quiz.utility.DataLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

class SectionViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_section)
    TextView section;

    @BindView(R.id.rvSubsections)
    RecyclerView rvSubsections;

    public SectionViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void populate(SubsectionClickListener listener, SectionModel sectionModel) {
        section.setText(sectionModel.getSectionName());

        SubsectionAdapter subsectionAdapter = new SubsectionAdapter(itemView.getContext(), listener, sectionModel.getSectionId());

        rvSubsections.setAdapter(subsectionAdapter);
        rvSubsections.setLayoutManager(new LinearLayoutManager(itemView.getContext()));

        subsectionAdapter.setData(DataLoader.getSubsections(
                itemView.getContext().getAssets(),
                sectionModel.getSectionId()
        ));
    }
}
