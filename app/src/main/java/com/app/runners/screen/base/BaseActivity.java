package com.app.runners.screen.base;

import android.os.Bundle;
import android.widget.Toast;

import com.app.runners.R;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    private static final String ACTION_TITLE = "actionTitle";
    
    private String mTitle;
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            handleExtras();
        } else {
            loadSavedState(savedInstanceState);
        }
    }

    @CallSuper
    public void loadSavedState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {

        }
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        
    }
    
    @Override
    protected void onPause() {
        super.onPause();
    
    }

    @Override
    protected void onResume() {
        super.onResume();
       
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        
    }
    
    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
      
    }

    private void handleExtras() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {

        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResId) {
        super.setContentView(layoutResId);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(ACTION_TITLE, mTitle);
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mTitle = savedInstanceState.getString(ACTION_TITLE);

            if (mTitle != null) {
                setTitle(mTitle);
            }
        }
    }


    public void showDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void setTitle(String title) {
        mTitle = title;
        super.setTitle(title);
    }

}
