package com.morozov.quiz.controller.app.login;

import android.content.Context;
import android.view.View;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.Controller;
import com.morozov.quiz.controller.interaction.LoginClickListener;

public class LoginController extends Controller<LoginViewModel> implements LoginClickListener, View.OnClickListener {

    private Context context;

    public LoginController(LoginViewModel viewModel) {
        super(viewModel);
    }

    void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void onStart() {
        loadWebView();
    }

    private void authorized() {
        //Write into shared preference

        viewModel().isLogin().setValue(true);
    }

    private void loadWebView() {
        viewModel().loadWebView().setValue(true);
    }

    @Override
    public void onLogined() {
        authorized();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonReload:
                loadWebView();
                break;
        }
    }
}
