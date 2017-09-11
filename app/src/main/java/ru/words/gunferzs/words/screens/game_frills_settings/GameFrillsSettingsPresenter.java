package ru.words.gunferzs.words.screens.game_frills_settings;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import ru.words.gunferzs.words.screens.game_manager.GameManagerModel;

/**
 * Created by GunFerzs on 28.08.2017.
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal", "unused"})
public class GameFrillsSettingsPresenter extends MvpBasePresenter<GameFrillsSettingsView> {

    GameManagerModel model;

    public GameFrillsSettingsPresenter() {
        model = new GameManagerModel();
    }


}
