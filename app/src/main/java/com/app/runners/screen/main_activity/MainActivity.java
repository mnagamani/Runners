package com.app.runners.screen.main_activity;

import android.os.Bundle;
import android.view.View;

import com.app.runners.R;
import com.app.runners.model.Runner;
import com.app.runners.screen.adapters.RunnerListAdapter;
import com.app.runners.screen.base.BaseActivity;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {

    private MainPresenter mPresenter;
    @BindView(R.id.loading)
    View mViewLoading;
    @BindView(R.id.runnerList)
    RecyclerView mRecyclerView;
    @BindView(R.id.header)
    View mHeader;
    @BindView(R.id.no_list_textView)
    View mNoListTextView;
    private RunnerListAdapter mRunnerListAdapter;

    @Nullable
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this, new MainModel(this));
        mPresenter.onCreated();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showLoading(boolean show) {
        if (mViewLoading != null) {
            mViewLoading.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void showHeader(boolean shouldShowHeader) {
        mHeader.setVisibility(shouldShowHeader ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showNoListToShow(boolean shouldShowNoListMessage) {
        mNoListTextView.setVisibility(shouldShowNoListMessage ? View.VISIBLE : View.GONE);
    }

    @Override
    public void loadRunnersList(List<Runner> runnerList) {
        mRunnerListAdapter = new RunnerListAdapter(runnerList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRunnerListAdapter);
    }
}