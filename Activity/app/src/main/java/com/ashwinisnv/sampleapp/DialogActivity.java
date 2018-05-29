package com.ashwinisnv.sampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    @Override
    public void onPause() {
        super.onPause();
        MainActivity.activityCounter = MainActivity.activityCounter+1;
    }

    public void finishDialog(View v) {
        DialogActivity.this.finish();
    }
}
