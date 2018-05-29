package com.ashwinisnv.sampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    static int activityCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button actB = findViewById(R.id.activityB);

        actB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(MainActivity.this, Activity2.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        final TextView count = findViewById(R.id.ThreadCount);
        count.setText("Thread Counter: "+String.valueOf(activityCounter));

        final Button dialog = findViewById(R.id.dialog);
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialogIntent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(dialogIntent);
            }
        });

    }

    @Override
    protected void onResume()
    {
         super.onResume();
         final TextView count = findViewById(R.id.ThreadCount);
         count.setText("Thread Counter: "+String.valueOf(MainActivity.activityCounter));
    }


    @Override
    protected void onPause()
    {
        super.onPause();
        activityCounter++;
    }

    public void finishApp(View v) {
       MainActivity.this.finish();
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        activityCounter = 0;
    }
}
