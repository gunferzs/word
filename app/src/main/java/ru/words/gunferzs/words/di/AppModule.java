package ru.words.gunferzs.words.di;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by GunFerzs on 11.09.2017.
 */

@SuppressWarnings({"CanBeFinal", "unused"})
@Module
public class AppModule {

    private Context appContext;

    public AppModule(@NonNull Context context){
        appContext = context;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return appContext;
    }
}
