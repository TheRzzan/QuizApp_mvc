package com.morozov.quiz.controller.app.topic;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ferfalk.simplesearchview.SimpleSearchView;
import com.morozov.quiz.R;
import com.morozov.quiz.controller.ControllerActivity;
import com.morozov.quiz.controller.interaction.DialogClickListener;
import com.morozov.quiz.controller.models.TopicModel;
import com.morozov.quiz.controller.ui.CustomDialog;
import com.morozov.quiz.utility.ActivityUtility;
import com.morozov.quiz.utility.AppConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicActivity extends ControllerActivity<TopicViewModel, TopicController> implements DialogClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.searchView)
    SimpleSearchView searchView;

    @BindView(R.id.rvTopics)
    RecyclerView rvTopics;

    private TopicAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getViewModel().setIsToTest(getIntent().getBooleanExtra(AppConstants.BUNDLE_KEY_IS_TO_TEST, true));
        getViewModel().subsectionId().setValue(getIntent().getStringExtra(AppConstants.JSON_KEY_SUBSECTION_ID));
        getViewModel().sectionId().setValue(getIntent().getStringExtra(AppConstants.JSON_KEY_SECTION_ID));

        adapter = new TopicAdapter(getApplicationContext(), getController());
        rvTopics.setAdapter(adapter);
        rvTopics.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        searchView.enableVoiceSearch(true);
        searchView.setOnQueryTextListener(new SimpleSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }

            @Override
            public boolean onQueryTextCleared() {

                return false;
            }
        });

        return true;
    }

    @Override
    protected void observe(TopicViewModel viewModel) {
        super.observe(viewModel);

        viewModel.topics().observe(this, new Observer<List<TopicModel>>() {
            @Override
            public void onChanged(@Nullable List<TopicModel> topicModels) {
                adapter.setData(topicModels);
                rvTopics.setVisibility(View.VISIBLE);
            }
        });

        viewModel.selectedTopic().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (viewModel.isToTest()) {
                    CustomDialog customDialog = new CustomDialog();
                    customDialog.setHeadline("Выбрана тема \"" +
                            getViewModel().topics().getValue().get(integer).getTopicName() +
                            "\". Готовы начать?");
                    customDialog.setListener(TopicActivity.this);
                    customDialog.show(getSupportFragmentManager(), "CustomDialog");
                } else {
                    Toast.makeText(TopicActivity.this, "Open answers", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void observeClicks(TopicController controller) {
        super.observeClicks(controller);


    }

    @Override
    protected TopicController createController(TopicViewModel viewModel) {
        TopicController topicController = new TopicController(viewModel);
        topicController.setContext(getApplicationContext());
        return topicController;
    }

    @Override
    protected Class<TopicViewModel> viewModel() {
        return TopicViewModel.class;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (searchView.onActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (searchView.onBackPressed()) {
            return;
        }

        ActivityUtility.invokeSubsectionActivity(TopicActivity.this, true,
                getViewModel().sectionId().getValue(),
                getViewModel().isToTest());
    }

    @Override
    public void onCancelClicked() {

    }

    @Override
    public void onOkClicked() {
        ActivityUtility.invokeQuizActivity(TopicActivity.this, true,
                getViewModel().topics().getValue().get(getViewModel().selectedTopic().getValue()).getTopicId(),
                getViewModel().subsectionId().getValue(),
                getViewModel().sectionId().getValue());
    }
}
