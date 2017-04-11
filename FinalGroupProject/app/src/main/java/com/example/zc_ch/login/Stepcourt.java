package com.example.zc_ch.login;
/**
 * Created by ZOU Zijie on 2017/4/9.
 */
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.ArcProgress;

public class Stepcourt extends Fragment implements SensorEventListener{

    private SensorManager sensorManager;
    private TextView count;
    private TextView count_cal;
    private ArcProgress arc_progress;
    boolean activityRunning;

    public Float s;
    public Integer healths;
    public String userName;
    public String getArgument;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v =inflater.inflate(R.layout.activity_stepcourt,null);
//        Abandon a=new Abandon();

        //count = (TextView) findViewById(R.id.count);
        count_cal = (TextView) v.findViewById(R.id.count_cal);
        arc_progress = (ArcProgress) v.findViewById(R.id.arc_progress);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        //Bundle bundle = getActivity().getIntent().getExtras();
        //userName = bundle.getString("userName");
//        Bundle b =getArguments();
//        userName = b.getString("userName");
//        Toast.makeText(this.getActivity(), a.getName(), Toast.LENGTH_LONG).show();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }else {
            Toast.makeText(this.getActivity(), "Count sensor not available!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public  void onPause() {
        super.onPause();
        activityRunning = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(activityRunning){
            Double cal = event.values[0]*0.031;
            s = event.values[0];

            count_cal.setText(String.valueOf(cal));
            arc_progress.setProgress((int)event.values[0]);
            if ( s > 3000 &  s < 6000){
                healths = 1;
            }
            if ( s > 6000 &  s < 9000){
                healths = 2;
            }
            if ( s > 9000 &  s < 12000){
                healths = 3;
            }
            if ( s > 12000&  s < 15000){
                healths = 4;
            }
            if ( s > 15000){
                healths = 5;
            }
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
