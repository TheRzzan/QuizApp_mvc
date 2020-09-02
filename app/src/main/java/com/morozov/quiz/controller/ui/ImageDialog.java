package com.morozov.quiz.controller.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.morozov.quiz.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageDialog extends DialogFragment {

    @BindView(R.id.imageView)
    SubsamplingScaleImageView imageView;

    private static String imageName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.Theme_App_Dialog_FullScreen);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_image, container, false);
        ButterKnife.bind(this, view);

        imageView.setMaxScale(15);

        if (imageName != null)
            imageView.setImage(ImageSource.asset(imageName));

        imageView.setOnClickListener(v -> dismiss());

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getDialog().dismiss();
//            }
//        });

        view.findViewById(R.id.dialog_image_back).setOnClickListener(v -> Objects.requireNonNull(getActivity()).onBackPressed());

        return view;
    }

    public void setImage(String image) {
        this.imageName = image;
    }
}
