package ru.words.gunferzs.words.game;

/**
 * Created by GunFerzs on 28.08.2017.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class GameSettings {

    GameInfo gameInfo;
    boolean isTimingGame;
    long Timer;
    boolean isBluetoothMode;
    boolean needAccelerometer;

    @SuppressWarnings("SameParameterValue")
    public GameSettings(GameInfo gameInfo, boolean isTimingGame, long timer, boolean isBluetoothMode, boolean needAccelerometer) {
        this.gameInfo = gameInfo;
        this.isTimingGame = isTimingGame;
        Timer = timer;
        this.isBluetoothMode = isBluetoothMode;
        this.needAccelerometer = needAccelerometer;
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public boolean isTimingGame() {
        return isTimingGame;
    }

    public void setTimingGame(boolean timingGame) {
        isTimingGame = timingGame;
    }

    public long getTimer() {
        return Timer;
    }

    public void setTimer(long timer) {
        Timer = timer;
    }

    public boolean isBluetoothMode() {
        return isBluetoothMode;
    }

    public void setBluetoothMode(boolean bluetoothMode) {
        isBluetoothMode = bluetoothMode;
    }

    public boolean isNeedAccelerometer() {
        return needAccelerometer;
    }

    public void setNeedAccelerometer(boolean needAccelerometer) {
        this.needAccelerometer = needAccelerometer;
    }
}
