package ru.words.gunferzs.words.utils;

import android.hardware.Sensor;

/**
 * Created by GunFerzs on 30.08.2017.
 */

@SuppressWarnings({"CanBeFinal", "unused"})
public class MySensorEvent {


    private float[] values;
    private Sensor sensor;
    private int accuracy;

    public MySensorEvent(float[] values, Sensor sensor) {

        this.values = values;
        this.sensor = sensor;
    }


    public void setValues(float[] values) {
        this.values = values;
    }

    public float[] getValues() {
        return values;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public int getAccuracy() {
        return accuracy;
    }
}
