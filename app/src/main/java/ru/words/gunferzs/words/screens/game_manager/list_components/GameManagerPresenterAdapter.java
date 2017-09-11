package ru.words.gunferzs.words.screens.game_manager.list_components;

import ru.words.gunferzs.words.game.GameSettings;
import ru.words.gunferzs.words.screens.base.BasePresenterAdapter;

/**
 * Created by GunFerzs on 28.08.2017.
 */

public class GameManagerPresenterAdapter extends BasePresenterAdapter<GameSettings, GameManagerItemView> {


    public GameManagerPresenterAdapter() {
    }

    public GameManagerPresenterAdapter(GameManagerPresenterAdapter presenter) {
        super();
        model.clear();
        model.addAll(presenter.getModel());
    }

    @Override
    public void updateView(GameManagerItemView view, int position) {
        view.updateNameOfGame(model.get(position).getGameInfo().getNameOfGame());
        view.initClickItem(model.get(position).getGameInfo().getTag());
    }

    @Override
    public int size() {
        return model.size();
    }
}
