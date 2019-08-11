package com.morozov.quiz.controller.app.login;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.morozov.quiz.R;
import com.morozov.quiz.controller.interaction.LoginClickListener;

public class LoginWebViewClient extends WebViewClient {

    private Context context;
    private LoginClickListener listener;
    private ProgressBar progressBar;

    @Override
    public void onPageCommitVisible(WebView view, String url) {
        super.onPageCommitVisible(view, url);

        if (progressBar != null)
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);

        if (progressBar != null)
            progressBar.setVisibility(View.VISIBLE);

        Toast.makeText(context, url, Toast.LENGTH_SHORT).show();

        if (url.equals(context.getResources().getString(R.string.intercept_url)))
            listener.onLogined();
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(request.getUrl().toString());
        return true;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    void setContext(Context context) {
        this.context = context;
    }

    public void setListener(LoginClickListener listener) {
        this.listener = listener;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }
}
