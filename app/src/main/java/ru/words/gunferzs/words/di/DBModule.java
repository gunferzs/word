package ru.words.gunferzs.words.di;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.words.gunferzs.words.utils.db.WordControllerDB;

/**
 * Created by GunFerzs on 11.09.2017.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
@Module
public class DBModule {

    @Provides
    @NonNull
    @Singleton
    public WordControllerDB provideDB(Context context){
        return new WordControllerDB(context);
    }
}
