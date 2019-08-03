package com.morozov.quiz.controller.app.section;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.models.SectionModel;

import butterknife.BindView;
import butterknife.ButterKnife;

class SectionViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.btn_section)
    Button btnSection;

    SectionViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void populate(SectionModel section) {
        btnSection.setText(section.getSectionName());
    }

    void setOnClick(View.OnClickListener listener, int tag) {
        btnSection.setTag(tag);
        btnSection.setOnClickListener(listener);
    }
}
