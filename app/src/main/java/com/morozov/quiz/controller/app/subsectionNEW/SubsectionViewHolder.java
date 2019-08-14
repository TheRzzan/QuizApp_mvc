package com.morozov.quiz.controller.app.subsectionNEW;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.interaction.SubsectionClickListener;
import com.morozov.quiz.controller.models.SubsectionModel;

import butterknife.BindView;
import butterknife.ButterKnife;

class SubsectionViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvRecycler)
    TextView tvSubsection;

    SubsectionViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void populate(SubsectionModel subsection) {
        tvSubsection.setText(subsection.getSubsectionName());
    }

    void setOnClick(SubsectionClickListener listener, int section, int subsection, String subsectionName) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(section, subsection, subsectionName);
            }
        });
    }
}
