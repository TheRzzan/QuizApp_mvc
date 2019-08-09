package com.morozov.quiz.controller.app.quiz;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.interaction.HighlightClickListener;
import com.morozov.quiz.utility.DataLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

class QuizViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvRecycler)
    TextView tvAnswer;

    @BindView(R.id.ivAnswer)
    ImageView ivAnswer;

    @BindView(R.id.iv_check)
    ImageView imCheck;

    @BindView(R.id.viewBack)
    View viewBack;

    private boolean isImage;

    QuizViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void populate(String text, boolean isImage) {
        this.isImage = isImage;

        if (isImage) {
            tvAnswer.setVisibility(View.GONE);
            ivAnswer.setVisibility(View.VISIBLE);

            ivAnswer.setImageDrawable(DataLoader.loadImage(itemView.getContext(), text));
            ivAnswer.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            ivAnswer.setVisibility(View.GONE);
            tvAnswer.setVisibility(View.VISIBLE);

            tvAnswer.setText(text);
        }

        viewBack.setVisibility(View.GONE);
        imCheck.setVisibility(View.GONE);
    }

    void setOnClick(final HighlightClickListener listener, final int tag) {
        itemView.setTag(tag);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onItemClicked((Integer) itemView.getTag());
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

        if (isImage) {
            viewBack.setVisibility(View.VISIBLE);

            if (bool)
                viewBack.setBackgroundResource(R.drawable.rectangle_dark_green_sharp);
            else
                viewBack.setBackgroundResource(R.drawable.rectangle_dark_red_sharp);
        }
    }
}
