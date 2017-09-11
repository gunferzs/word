package ru.words.gunferzs.words.screens.game_manager.model;

/**
 * Created by GunFerzs on 28.08.2017.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class Data {
    private String nameOfGame;

    public Data() {
    }

    public Data(String nameOfGame) {
        this.nameOfGame = nameOfGame;
    }

    public String getNameOfGame() {
        return nameOfGame;
    }

    public void setNameOfGame(String nameOfGame) {
        this.nameOfGame = nameOfGame;
    }

    public static Data registerGame(String nameOfGame){
        return new Data(nameOfGame);
    }
}
