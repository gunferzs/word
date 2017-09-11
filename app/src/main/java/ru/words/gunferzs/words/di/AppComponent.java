package ru.words.gunferzs.words.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.words.gunferzs.words.MainActivity;
import ru.words.gunferzs.words.screens.game_frills.GameFrillsController;
import ru.words.gunferzs.words.screens.game_frills.GameFrillsModel;

/**
 * Created by GunFerzs on 11.09.2017.
 */

@Component(modules = {AppModule.class, SensorModule.class, DBModule.class})
@Singleton
public interface AppComponent {
    void inject(GameFrillsModel gameFrillsModel);
    void inject(MainActivity mainActivity);
    void inject(GameFrillsController gameFrillsController);
}
