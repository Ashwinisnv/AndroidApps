package com.ashwinisnv.clapapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor mProximity;
    MediaPlayer mp;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text1);

        //Sensor Manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mProximity =  sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mp = MediaPlayer.create(MainActivity.this, R.raw.clap);

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        sensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {

        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float dist = event.values[0];
        if(dist < 3.1)
        {
          mp.start();
          text.setText("Clapping!!");
          return;
        }
        text.setText("Not Clapping!!");
    }

}
