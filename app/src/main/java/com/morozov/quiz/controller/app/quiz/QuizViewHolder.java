package com.morozov.quiz.controller.app.quiz;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.interaction.HighlightClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

class QuizViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvRecycler)
    TextView tvAnswer;

    @BindView(R.id.iv_check)
    ImageView imCheck;

    QuizViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void populate(String answer) {
        tvAnswer.setText(answer);
        imCheck.setVisibility(View.GONE);
    }

    void setOnClick(final HighlightClickListener listener, final int tag) {
        tvAnswer.setTag(tag);
        tvAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onItemClicked((Integer) tvAnswer.getTag());
            }
        });
    }

    void setCheck(boolean bool) {
        imCheck.setVisibility(View.VISIBLE);
        if (bool) {
            imCheck.setImageResource(R.drawable.check);
        } else {
            imCheck.setImageResource(R.drawable.cancel);
        }
    }
}
