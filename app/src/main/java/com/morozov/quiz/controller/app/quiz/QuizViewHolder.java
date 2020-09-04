package com.morozov.quiz.controller.app.quiz;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.interaction.HighlightClickListener;
import com.morozov.quiz.controller.ui.ImageDialog;
import com.morozov.quiz.utility.AppConstants;
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

    private boolean containsImage;

    QuizViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void populate(FragmentManager fragmentManager, String imagePath, String text) {
        containsImage = imagePath != null && !imagePath.isEmpty();

        if (containsImage) {
            ivAnswer.setVisibility(View.VISIBLE);

            ivAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageDialog imageDialog = new ImageDialog();
                    imageDialog.setImage(AppConstants.IMAGE_DIR + imagePath);
                    imageDialog.show(fragmentManager, QuizActivity.class.getSimpleName());
                }
            });

            ivAnswer.setImageDrawable(DataLoader.loadImage(itemView.getContext(), imagePath));
            ivAnswer.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            ivAnswer.setVisibility(View.GONE);
        }
        if (text != null && !text.isEmpty()) {
            tvAnswer.setVisibility(View.VISIBLE);
            text = text.replace("\n", "<br>");
            tvAnswer.setText(Html.fromHtml(text));
        } else {
            tvAnswer.setVisibility(View.GONE);
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
//        imCheck.setVisibility(View.VISIBLE);
//        if (bool) {
//            imCheck.setImageResource(R.drawable.check);
//        } else {
//            imCheck.setImageResource(R.drawable.cancel);
//        }

        if (containsImage) {
            viewBack.setVisibility(View.VISIBLE);

            if (bool)
                viewBack.setBackgroundResource(R.drawable.rectangle_dark_green_sharp);
            else
                viewBack.setBackgroundResource(R.drawable.rectangle_dark_red_sharp);
        }
    }
}
