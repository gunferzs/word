package ru.words.gunferzs.words.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GunFerzs on 29.08.2017.
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal", "unused"})
public class SensorsManage implements SensorEventListener {

    SensorManager manager;
    Context context;
    List<SensorPair> sensors;
    public static final int TYPE_ORIENTATION = 3;
    Map<Integer, List<SensorListener>> eventListeners;

    //region for orientation
    private float[] rotationMatrix, magnetData, accelData, orientationData;
    private boolean orientation;
    //endregion


    public SensorsManage(Context context) {
        this.context = context;
        manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensors = new ArrayList<>();
        eventListeners = new HashMap<>();
    }

    public void initOrientationBundle() {
        rotationMatrix = new float[16];
        orientationData = new float[3];
        magnetData = new float[3];
        accelData = new float[3];
        registerSensors(new SensorPair(TYPE_ORIENTATION));
    }

    public void registerSensors(SensorPair... pairs) {
        if (!sensors.isEmpty()) {
            for (SensorPair pair : pairs) {
                boolean exist = false;
                for (SensorPair sensor : sensors) {
                    if (pair.equals(sensor)) {
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    processPair(pair);
                }
            }
        } else {
            for (SensorPair pair : pairs) {
                processPair(pair);
            }
        }
    }

    public void processPair(SensorPair pair) {
        sensors.add(pair);
        eventListeners.put(pair.getType(), new ArrayList<SensorListener>());
        if (pair.getType() != TYPE_ORIENTATION) {
            manager.registerListener(this, manager.getDefaultSensor(pair.getType()), pair.getDelay());
        } else {
            manager.registerListener(this, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), pair.getDelay());
            manager.registerListener(this, manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), pair.getDelay());
            orientation = true;
        }
    }

    @SuppressWarnings("SameParameterValue")
    public void registerListenerSensor(int typeSensor, SensorListener listener){
        SensorPair temp = new SensorPair(typeSensor);
        if(sensors.contains(temp)){
            eventListeners.get(typeSensor).add(listener);
        }
    }

    public void unregisterSensors() {
        manager.unregisterListener(this);
    }

    public void destroySensorManager(){
        sensors.clear();
        manager=null;

    }


    private void loadNewSensorData(SensorEvent event) {
        final int type = event.sensor.getType();
        if (type == Sensor.TYPE_ACCELEROMETER) {
            accelData = event.values.clone();
        }

        if (type == Sensor.TYPE_MAGNETIC_FIELD) {
            magnetData = event.values.clone();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        final int type = event.sensor.getType();
        if (orientation) {
            if (type == Sensor.TYPE_ACCELEROMETER||type == Sensor.TYPE_MAGNETIC_FIELD) {
                loadNewSensorData(event);
                SensorManager.getRotationMatrix(rotationMatrix, null, accelData, magnetData);
                SensorManager.getOrientation(rotationMatrix, orientationData);
                MySensorEvent sensorEvent = new MySensorEvent(orientationData,event.sensor);
                for (SensorListener sensorListener : eventListeners.get(TYPE_ORIENTATION)) {
                    sensorListener.OnChangeSensorData(sensorEvent);
                }
                return;
            }
        }
        MySensorEvent sensorEvent = new MySensorEvent(event.values, event.sensor);
        for (SensorListener sensorListener : eventListeners.get(type)) {
            sensorListener.OnChangeSensorData(sensorEvent);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        System.out.println("accuracy");
        switch(accuracy){
            case SensorManager.SENSOR_STATUS_UNRELIABLE:
                System.out.println("SENSOR_STATUS_UNRELIABLE");
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                System.out.println("SENSOR_STATUS_ACCURACY_LOW");
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                System.out.println("SENSOR_STATUS_ACCURACY_MEDIUM");
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                System.out.println("SENSOR_STATUS_ACCURACY_HIGH");
                break;
        }
    }
}
