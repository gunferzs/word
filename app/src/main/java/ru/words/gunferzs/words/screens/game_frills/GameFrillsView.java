package ru.words.gunferzs.words.screens.game_frills;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by GunFerzs on 28.08.2017.
 */

@SuppressWarnings("WeakerAccess")
public interface GameFrillsView extends MvpView {
    void startGame();
    void updateTimerText(String time);
    void updateWord(String word);
    void finishGame(int success, int failure);

}
