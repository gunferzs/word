package ru.words.gunferzs.words;

import android.app.Application;

import ru.words.gunferzs.words.di.AppComponent;
import ru.words.gunferzs.words.di.AppModule;
import ru.words.gunferzs.words.di.DaggerAppComponent;

/**
 * Created by GunFerzs on 28.08.2017.
 */

@SuppressWarnings("WeakerAccess")
public class MyApplication extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildComponent();
    }

    AppComponent buildComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
