package com.morozov.quiz.controller.app.section_to_answer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.models.SectionModel;

import butterknife.BindView;
import butterknife.ButterKnife;

class SectionViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvRecycler)
    TextView tvSection;

    SectionViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void populate(SectionModel section) {
        tvSection.setText(section.getSectionName());
    }

    void setOnClick(View.OnClickListener listener, int tag) {
        itemView.setTag(tag);
        itemView.setOnClickListener(listener);
    }
}
