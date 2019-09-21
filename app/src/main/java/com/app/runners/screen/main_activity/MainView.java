package com.app.runners.screen.main_activity;

import com.app.runners.model.Runner;
import com.app.runners.screen.base.BaseView;

import java.util.List;

public interface MainView extends BaseView {
    void loadRunnersList(List<Runner> runnerList);
    void showLoading(boolean showLoading);

    void showHeader(boolean shouldShowHeader);

    void showNoListToShow(boolean shouldShowNoListMessage);
}
