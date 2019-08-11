package com.morozov.quiz.controller.app.login;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.ControllerActivity;
import com.morozov.quiz.controller.app.section.SectionActivity;
import com.morozov.quiz.utility.ActivityUtility;
import com.morozov.quiz.utility.DetectConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends ControllerActivity<LoginViewModel, LoginController> {

    @BindView(R.id.webView)
    WebView webView;

    @BindView(R.id.buttonReload)
    Button reload;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    private void initWebView() {
        if (DetectConnection.checkInternetConnection(getApplicationContext())){
            reload.setVisibility(View.GONE);

            LoginWebViewClient client = new LoginWebViewClient();
            client.setContext(getApplicationContext());
            client.setListener(getController());
            client.setProgressBar(progressBar);

            webView.setWebViewClient(client);
            client.shouldOverrideUrlLoading(webView, getString(R.string.main_url));
        } else
            reload.setVisibility(View.VISIBLE);
    }

    @Override
    protected void observe(LoginViewModel viewModel) {
        super.observe(viewModel);

        viewModel.loadWebView().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean)
                    initWebView();
            }
        });

        viewModel.isLogin().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean)
                    ActivityUtility.invokeNewActivity(LoginActivity.this, SectionActivity.class, true);
            }
        });
    }

    @Override
    protected void observeClicks(LoginController controller) {
        super.observeClicks(controller);

        reload.setOnClickListener(controller);
    }

    @Override
    protected LoginController createController(LoginViewModel viewModel) {
        LoginController loginController = new LoginController(viewModel);
        loginController.setContext(getApplicationContext());
        return loginController;
    }

    @Override
    protected Class<LoginViewModel> viewModel() {
        return LoginViewModel.class;
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }
}
