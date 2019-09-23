package com.app.runners.screen.main_activity;

import com.app.runners.model.Runner;
import com.app.runners.screen.base.BasePresenter;

import java.util.List;

public class MainPresenter extends BasePresenter {

    private MainView mView;
    private MainModel mModel;

    public MainPresenter(MainView view, MainModel model) {
        super(view, model);
        mView = view;
        mModel = model;
    }

    @Override
    public void onCreated() {
        super.onCreated();
        mModel.getRaceDetails(() -> {
            mModel.subscribeToRaceDetails(this::updateView);
        }, error -> {
            mView.showLoading(false);
            mView.showHeader(false);
            mView.showNoListToShow(true);
            mView.showToast(error.getMessage());
        });

    }

    private void updateView() {
        mView.showLoading(false);
        mView.setTitle(mModel.getTitle());
        List<Runner> runnerList = mModel.getRunnersList();
        if (runnerList != null && runnerList.size() > 0) {
            mView.showHeader(true);
            mView.loadRunnersList(mModel.getRunnersList());
        } else {
            mView.showHeader(false);
            mView.showNoListToShow(true);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mModel.onDestroy();
    }

    public void onRefresh() {
        mView.showLoading(true);
        mModel.getRaceDetails(() -> {
            updateView();
        }, error -> {
            mView.showLoading(false);
            mView.showHeader(false);
            mView.showNoListToShow(true);
            mView.showToast(error.getMessage());
        });
    }
}
