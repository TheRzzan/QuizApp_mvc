package com.morozov.quiz.controller.app.answer_pdf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.morozov.quiz.R;
import com.morozov.quiz.controller.app.subsection_to_answer.SubsectionActivity;
import com.morozov.quiz.utility.ActivityNavigation;
import com.morozov.quiz.utility.ActivityUtility;
import com.morozov.quiz.utility.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnswerActivity extends AppCompatActivity {

    @BindView(R.id.pdfView)
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers_pdf);
        ButterKnife.bind(this);

        pdfView.fromAsset(
                AppConstants.PDF_DIR + ActivityNavigation.getInstance(getApplicationContext()
                        )
                                .getAnswersPdf()).load();
    }

    @Override
    public void onBackPressed() {
        ActivityUtility.invokeNewActivity(AnswerActivity.this, SubsectionActivity.class, true);
    }
}
