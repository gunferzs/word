package ru.words.gunferzs.words.screens.game_manager;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import ru.words.gunferzs.words.screens.game_manager.list_components.GameManagerPresenterAdapter;

/**
 * Created by GunFerzs on 28.08.2017.
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal"})
public class GameManagerPresenter extends MvpBasePresenter<GameManagerView> {

    GameManagerModel model;

    public GameManagerPresenter() {
        model = new GameManagerModel();
    }

    public void updateList() {
        GameManagerPresenterAdapter presenter = new GameManagerPresenterAdapter();
        presenter.setModel(model.getData());
        getView().updateList(presenter);
    }

}
