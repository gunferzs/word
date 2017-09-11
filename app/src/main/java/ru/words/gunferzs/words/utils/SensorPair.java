package ru.words.gunferzs.words.utils;

import android.hardware.SensorManager;
import android.support.annotation.NonNull;

/**
 * Created by GunFerzs on 29.08.2017.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class SensorPair {
    int type, delay;

    public SensorPair(int type, int delay) {
        this.type = type;
        this.delay = delay;
    }

    public SensorPair(int type) {
        this.type = type;
        this.delay = SensorManager.SENSOR_DELAY_NORMAL;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(@NonNull Object obj) {
        SensorPair compareObj = (SensorPair) obj;
        return type == compareObj.type && delay == compareObj.delay;
    }
}
