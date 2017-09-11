package ru.words.gunferzs.words.screens.game_manager;

import java.util.ArrayList;
import java.util.List;

import ru.words.gunferzs.words.MainActivity;
import ru.words.gunferzs.words.game.GameSettings;

/**
 * Created by GunFerzs on 28.08.2017.
 */

@SuppressWarnings("CanBeFinal")
public class GameManagerModel {

    private List<GameSettings> data;

    public GameManagerModel() {
        data = new ArrayList<>(MainActivity.getGameSettings());
    }

    public List<GameSettings> getData() {
        return data;
    }
}
