package com.imam2trk.belajarsensoraccel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sm;
    ImageView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.imageView);
        sm = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);

        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            float value[]=sensorEvent.values;
            float x = value[0];
            float y = value[1];
            float z = value[2];

            float movement_shake = ((x*x+y*y+z+z)/(SensorManager.GRAVITY_EARTH*
                    SensorManager.GRAVITY_EARTH));

            if (movement_shake>=2){
                btn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.laughing));
                Toast.makeText(this, "Shake terdeteksi", Toast.LENGTH_SHORT).show();
            } else {
                btn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.vain));
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            }
        }
    }
}