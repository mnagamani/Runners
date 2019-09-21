package com.app.runners.screen.base;

public interface Presenter {
    void onCreated();
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
}
