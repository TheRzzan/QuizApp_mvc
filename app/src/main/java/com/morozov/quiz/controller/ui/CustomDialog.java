package com.morozov.quiz.controller.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.interaction.DialogClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomDialog extends DialogFragment {

    @BindView(R.id.heading)
    TextView headline;

    @BindView(R.id.action_cancel)
    TextView cancel;
    private String cancelStr;

    @BindView(R.id.action_ok)
    TextView ok;
    private String okStr;

    private String headlineStr;

    private DialogClickListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_layout, container, false);
        ButterKnife.bind(this, view);

        headline.setText(headlineStr);

        if (cancelStr != null)
            cancel.setText(cancelStr);

        if (okStr != null)
            ok.setText(okStr);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onCancelClicked();
                getDialog().dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onOkClicked();
                getDialog().dismiss();
            }
        });

        return view;
    }

    public void setHeadline(String headlineStr) {
        this.headlineStr = headlineStr;
    }

    public void setListener(DialogClickListener listener) {
        this.listener = listener;
    }

    public void setOkText(String text) {
        okStr = text;
    }

    public void setCancelText(String text) {
        cancelStr = text;
    }
}
