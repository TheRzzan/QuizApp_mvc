package com.morozov.quiz.controller.models;

import android.arch.lifecycle.MutableLiveData;

public class DoubleClickLiveData<T> extends MutableLiveData<T> {
    private boolean isSingleClicked = false;
    private int mClickId = 0;

    public boolean click(int clickId) {
        if (isSingleClicked) {
            if (mClickId == clickId) {
                isSingleClicked = false;
                return true;
            } else {
                mClickId = clickId;
                return false;
            }
        } else {
            isSingleClicked = true;
            mClickId = clickId;
            return false;
        }
    }
}
