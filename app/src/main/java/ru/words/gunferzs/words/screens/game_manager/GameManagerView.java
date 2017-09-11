package ru.words.gunferzs.words.screens.game_manager;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import ru.words.gunferzs.words.screens.game_manager.list_components.GameManagerPresenterAdapter;

/**
 * Created by GunFerzs on 28.08.2017.
 */

@SuppressWarnings("WeakerAccess")
public interface GameManagerView extends MvpView {
    void updateList(GameManagerPresenterAdapter presenter);
}
