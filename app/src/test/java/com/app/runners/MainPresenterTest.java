package com.app.runners;

import com.app.runners.model.Runner;
import com.app.runners.screen.main_activity.MainModel;
import com.app.runners.screen.main_activity.MainPresenter;
import com.app.runners.screen.main_activity.MainView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainPresenterTest {
    @Mock MainModel mModel;
    @Mock MainView mView;

    private MainPresenter mPresenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mPresenter = new MainPresenter(mView, mModel);
    }

    @Test
    public void testRunnerListAdapter(){
        reset(mModel);
        List<Runner> runnerList = new ArrayList<>();
        runnerList.add(new Runner());
        runnerList.add(new Runner());
        runnerList.add(new Runner());
        when(mModel.getRunnersList()).thenReturn(runnerList);
        mPresenter.setRunnerListAdapter(runnerList);
        verify(mView).loadRunnersList(runnerList);
    }

}
