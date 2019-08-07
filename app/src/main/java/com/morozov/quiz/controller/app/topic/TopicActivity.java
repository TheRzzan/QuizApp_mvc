package com.morozov.quiz.controller.app.topic;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;

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

    @BindView(R.id.rvTopics)
    RecyclerView rvTopics;

    private TopicAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getViewModel().subsectionId().setValue(getIntent().getStringExtra(AppConstants.JSON_KEY_SUBSECTION_ID));
        getViewModel().sectionId().setValue(getIntent().getStringExtra(AppConstants.JSON_KEY_SECTION_ID));

        adapter = new TopicAdapter(getApplicationContext(), getController());
        rvTopics.setAdapter(adapter);
        rvTopics.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
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
                CustomDialog customDialog = new CustomDialog();
                customDialog.setHeadline("Выбрана тема \"" +
                        getViewModel().topics().getValue().get(integer).getTopicName() +
                        "\". Готовы начать?");
                customDialog.setListener(TopicActivity.this);
                customDialog.show(getSupportFragmentManager(), "CustomDialog");
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
    public void onBackPressed() {
        ActivityUtility.invokeSubsectionActivity(TopicActivity.this, true,
                getViewModel().sectionId().getValue());
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
