package com.example.zc_ch.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.ArcProgress;

public class Stepcourt extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;
    private TextView count;
    private TextView count_cal;
    private ArcProgress arc_progress;
    boolean activityRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepcourt);

        count = (TextView) findViewById(R.id.count);
        count_cal = (TextView) findViewById(R.id.count_cal);
        arc_progress = (ArcProgress) findViewById(R.id.arc_progress);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }else {
            Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected  void onPause() {
        super.onPause();
        activityRunning = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(activityRunning){
            Double cal = event.values[0]*0.031;
            count.setText(String.valueOf(event.values[0]));
            count_cal.setText(String.valueOf(cal));
            arc_progress.setProgress((int)event.values[0]);
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
