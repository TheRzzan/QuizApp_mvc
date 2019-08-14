package com.morozov.quiz.controller.app.airplane;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.models.AirplaneModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AirplaneViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.btn_section)
    Button btnSection;

    public AirplaneViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void populate(AirplaneModel airplane) {
        btnSection.setText(airplane.getAirplaneName());
    }

    void setOnClick(View.OnClickListener listener, int tag) {
        btnSection.setTag(tag);
        btnSection.setOnClickListener(listener);
    }
}
