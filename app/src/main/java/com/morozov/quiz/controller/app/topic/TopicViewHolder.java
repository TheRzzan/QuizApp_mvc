package com.morozov.quiz.controller.app.topic;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.models.TopicModel;

import butterknife.BindView;
import butterknife.ButterKnife;

class TopicViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvRecycler)
    TextView tvTopic;

    @BindView(R.id.text_percentage)
    TextView tvPercentage;

    TopicViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("SetTextI18n")
    void populate(TopicModel topic) {
        tvTopic.setText(topic.getTopicName());
        if (topic.getPercentage() != null) {
            tvPercentage.setVisibility(View.VISIBLE);
            tvPercentage.setText(topic.getPercentage() + "%");
        } else {
            tvPercentage.setVisibility(View.GONE);
        }
    }

    void setOnClick(View.OnClickListener listener, int tag) {
        itemView.setTag(tag);
        itemView.setOnClickListener(listener);
    }
}
