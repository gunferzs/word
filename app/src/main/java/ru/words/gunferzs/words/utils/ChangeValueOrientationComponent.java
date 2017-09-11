package ru.words.gunferzs.words.utils;

import android.support.annotation.NonNull;

import static java.lang.Math.*;

/**
 * Created by GunFerzs on 31.08.2017.
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal", "unused"})
public class ChangeValueOrientationComponent {


    private double sensivityChecking = 1;
    private int checkingCountChanges = 10;
    private int countLowChanges = 0;

    public double oldValue = 0, newValue = 0;
    float k = 0.02f;

    private String nameOfComponent;

    private boolean beginChanges = true;
    private ChangeValueListener<Double> listener;

    public ChangeValueOrientationComponent(String nameOfComponent, @NonNull ChangeValueListener<Double> listener) {
        this.nameOfComponent = nameOfComponent;
        this.listener = listener;
    }

    public void setOldValue(double oldValue) {
        this.oldValue = oldValue;
    }

    public void setCheckingCountChanges(int checkingCountChanges) {
        this.checkingCountChanges = checkingCountChanges;
    }

    public void setSensivityChecking(double sensivityChecking) {
        this.sensivityChecking = sensivityChecking;
    }

    public void registerChanges(double newValue) {
        this.oldValue = this.newValue;
        this.newValue = filterLowFrequency(this.newValue, newValue);
        registerChanges();
    }

    public void registerChanges() {
        if (abs(newValue - oldValue) > sensivityChecking || countLowChanges != checkingCountChanges) {
            System.out.println(nameOfComponent);
            System.out.println(newValue - oldValue);
            countLowChanges++;
            if (abs(newValue - oldValue) > sensivityChecking) {
                countLowChanges = 0;
                if (beginChanges) {
                    System.out.println("Start gesture");
                    listener.onBeginChange(nameOfComponent);
                    beginChanges = false;
                }
            }
            if(abs(newValue - oldValue)<10)
                listener.onChangeValue(newValue - oldValue, nameOfComponent);
            if (countLowChanges == checkingCountChanges) {
                beginChanges = true;
                listener.onFinish(nameOfComponent);
                System.out.println("Stop gesture");
            }
        }
    }

    double filterLowFrequency(double value, double source){
        if(value==0){
            value = source;
        }else{
            value = (1-k)*value+k*(float) source;
        }
        return value;
    }


    public void setNewValue(double newValue) {
        this.newValue = newValue;
    }
}
