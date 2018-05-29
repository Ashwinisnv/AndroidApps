package com.ashwinisnv.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoDownload(View view) {

        Intent myIntent = new Intent(MainActivity.this, pdfDownloadActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void closeApp(View view)
    {
        MainActivity.this.finish();
    }
}
