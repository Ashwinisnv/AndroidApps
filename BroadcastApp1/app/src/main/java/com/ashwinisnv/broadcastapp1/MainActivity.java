package com.ashwinisnv.broadcastapp1;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static TextView converted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        converted = findViewById(R.id.convertedText);
        Intent intent = getIntent();
        converted.setText(intent.getStringExtra("msg3"));
        converted.setPaintFlags(converted.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public void broadcastAmount(View view){

        String unique = "com.ashwinisnv.broadcastreceivers";
        final EditText dollar =  findViewById(R.id.dollarValue);
        final Spinner convert = findViewById(R.id.convertValue);

        Intent broadcastIntent = new Intent();
        broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        broadcastIntent.setComponent((new ComponentName("com.ashwinisnv.broadcastapp2","com.ashwinisnv.broadcastapp2.MyReceiverfromApp1")));
        broadcastIntent.setAction(unique);
        broadcastIntent.putExtra("message1", dollar.getText().toString());
        broadcastIntent.putExtra("message2", convert.getSelectedItem().toString());
        sendBroadcast(broadcastIntent);
    }

    public void finishApp(View v) {
        MainActivity.this.finish();
    }
}
