package com.ashwinisnv.sampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Button finish = findViewById(R.id.finishB);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intentA = new Intent(Activity2.this,MainActivity.class);
//                Activity2.this.startActivity(intentA);
                Activity2.this.finish();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        MainActivity.activityCounter=MainActivity.activityCounter+1;
    }
}
