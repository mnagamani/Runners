package com.app.runners.screen.base;

public interface BaseView {
    void setTitle(String title);
    void showDialog(String title, String message);
    void showToast(String message);
}
