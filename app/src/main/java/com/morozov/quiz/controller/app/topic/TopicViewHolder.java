package com.morozov.quiz.controller.app.topic;

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

    TopicViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void populate(TopicModel topic) {
        tvTopic.setText(topic.getTopicName());
    }

    void setOnClick(View.OnClickListener listener, int tag) {
        tvTopic.setTag(tag);
        tvTopic.setOnClickListener(listener);
    }
}
