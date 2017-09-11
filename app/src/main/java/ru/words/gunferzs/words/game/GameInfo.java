package ru.words.gunferzs.words.game;

/**
 * Created by GunFerzs on 28.08.2017.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class GameInfo {
    String nameOfGame;
    String tag;
    String rules;

    @SuppressWarnings("SameParameterValue")
    public GameInfo(String nameOfGame, String rules, String tag) {
        this.nameOfGame = nameOfGame;
        this.rules = rules;
        this.tag = tag;
    }

    public String getNameOfGame() {
        return nameOfGame;
    }

    public void setNameOfGame(String nameOfGame) {
        this.nameOfGame = nameOfGame;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }
}
