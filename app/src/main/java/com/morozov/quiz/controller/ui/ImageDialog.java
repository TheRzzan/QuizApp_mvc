package com.morozov.quiz.controller.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.morozov.quiz.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageDialog extends DialogFragment {

    @BindView(R.id.imageView)
    ImageView imageView;

    private Drawable drawable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_image, container, false);
        ButterKnife.bind(this, view);

        if (drawable != null)
            imageView.setImageDrawable(drawable);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }

    public void setImage(Drawable image) {
        this.drawable = image;
    }
}
