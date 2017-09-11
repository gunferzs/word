package ru.words.gunferzs.words.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.toDegrees;

/**
 * Created by GunFerzs on 31.08.2017.
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal"})
public class GestureOrientation {
    public ChangeValueOrientationComponent xy, xz, yz;
    public final static String XY_COMPONENT = "xy";
    public final static String XZ_COMPONENT = "xz";
    public final static String YZ_COMPONENT = "yz";
    boolean finishXY, finishXZ, finishYZ;

    public static int noiseTreshold = 10;
    final static int NOISE_STATE = 0;
    final static int NOD_STATE = 1;

    SpatialGestureListener spatialGestureListener;

    ArrayList<Double> gestureXY, gestureXZ, gestureYZ;

    public GestureOrientation(@NonNull SpatialGestureListener spatialGestureListener) {
        this.spatialGestureListener = spatialGestureListener;
        gestureXY = new ArrayList<>();
        gestureXZ = new ArrayList<>();
        gestureYZ = new ArrayList<>();
        xy = new ChangeValueOrientationComponent(XY_COMPONENT, listener);
        xz = new ChangeValueOrientationComponent(XZ_COMPONENT, listener);
        yz = new ChangeValueOrientationComponent(YZ_COMPONENT, listener);
    }

    ChangeValueListener<Double> listener = new ChangeValueListener<Double>() {
        @Override
        public void onBeginChange(String id) {
            if(finishXZ&&finishXY&&finishYZ){
                gestureXY.clear();
                gestureXZ.clear();
                gestureYZ.clear();
            }
            switch(id){
                case XY_COMPONENT:
                    gestureXY.clear();
                    finishXY = false;
                    break;
                case XZ_COMPONENT:
                    gestureXZ.clear();
                    finishXZ = false;
                    break;
                case YZ_COMPONENT:
                    gestureYZ.clear();
                    finishYZ = false;
                    break;
            }

        }

        @Override
        public void onChangeValue(Double value, String id) {
            switch(id){
                case XY_COMPONENT:
                    gestureXY.add(value);
                    break;
                case XZ_COMPONENT:
                    gestureXZ.add(value);
                    break;
                case YZ_COMPONENT:
                    gestureYZ.add(value);
                    break;
            }

        }

        @Override
        public void onFinish(String id) {
            switch(id){
                case XY_COMPONENT:
                    finishXY = true;
                    break;
                case XZ_COMPONENT:
                    finishXZ = true;
                    break;
                case YZ_COMPONENT:
                    finishYZ = true;
                    break;
            }
            defineGesture();
        }
    };

    public void updateComponentsWithPrepareAngles(float xy, float xz, float yz){
        updateComponents((float)toDegrees(xy), (float)toDegrees(xz), (float)toDegrees(yz));
    }


    public void updateComponents(float xy, float xz, float yz){
        this.xy.registerChanges(xy);
        this.xz.registerChanges(xz);
        this.yz.registerChanges(yz);
    }

    void defineGesture(){
        if(finishXY&&finishXZ&&finishYZ){
            int xy = defineBehaviorComponent(gestureXY, XY_COMPONENT);
            int xz = defineBehaviorComponent(gestureXZ, XZ_COMPONENT);
            int yz = defineBehaviorComponent(gestureYZ, YZ_COMPONENT);
            if(yz == NOD_STATE){
                if(xy==NOISE_STATE){
                    spatialGestureListener.onNod(YZ_COMPONENT);
                    return;
                }
            }
            if(xy == NOD_STATE){
                if(yz==NOISE_STATE){
                    spatialGestureListener.onNod(XY_COMPONENT);
                    return;
                }
            }
            if(xz == NOD_STATE){
                if(xy==NOISE_STATE&&yz==NOISE_STATE){
                    spatialGestureListener.onNod(XZ_COMPONENT);
                }
            }

        }
    }

    int defineBehaviorComponent(List<Double> list, String id){
        double maxValuePos = 0, maxValueNegative = Integer.MIN_VALUE;
        double minValuePos = Integer.MAX_VALUE, minValueNegative = 0;
        double sumPositive = 0, sumNegative = 0;
        for (Double aDouble : list) {
            if(aDouble>=0){
                sumPositive += aDouble;
                maxValuePos = Math.max(maxValuePos,aDouble);
                minValuePos = Math.min(minValuePos, aDouble);
            }else{
                sumNegative += aDouble;
                maxValueNegative = Math.max(maxValueNegative,aDouble);
                minValuePos = Math.min(minValueNegative, aDouble);
            }
        }
        int result = NOISE_STATE;
        System.out.println(id+" "+sumPositive+" "+sumNegative);
        if(sumPositive>noiseTreshold&&abs(sumNegative)>noiseTreshold) {
            if (abs(sumPositive + sumNegative) < 50) {
                result = NOD_STATE;
                showState(NOD_STATE,id);
                return result;
            }
        }
        showState(result, id);
        return result;
    }

    void showState(int state, String id){
        String result = "STATE Gesture component "+ id+": ";
        switch (state){
            case NOISE_STATE:
                result += "noise";
                break;
            case NOD_STATE:
                result += "nod";
                break;
            default:
                result += "other";
                break;
        }
        System.out.println(result);
    }
}
