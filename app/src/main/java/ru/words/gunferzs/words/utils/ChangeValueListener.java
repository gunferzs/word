package ru.words.gunferzs.words.utils;

/**
 * Created by GunFerzs on 31.08.2017.
 */

@SuppressWarnings("WeakerAccess")
public interface ChangeValueListener<T> {

    void onBeginChange(String id);
    void onChangeValue(T value, String id);
    void onFinish(String id);
}
