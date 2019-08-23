package com.morozov.quiz.controller.app.answer;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.models.TopicModel;
import com.morozov.quiz.utility.DataLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

class TopicViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.rvTopics)
    RecyclerView rv_topics;

    TopicViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void populate(FragmentManager fragmentManager, TopicModel topic, int position) {
        AnswerAdapter answerAdapter = new AnswerAdapter(itemView.getContext(), fragmentManager);

        rv_topics.setAdapter(answerAdapter);
        rv_topics.setLayoutManager(new LinearLayoutManager(itemView.getContext()));

        answerAdapter.setData(DataLoader.getQuestions(
                itemView.getContext().getAssets(),
                topic.getTopicId()
        ));
    }
}
