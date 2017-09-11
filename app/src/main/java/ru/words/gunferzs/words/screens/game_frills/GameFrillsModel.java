package ru.words.gunferzs.words.screens.game_frills;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.words.gunferzs.words.MyApplication;
import ru.words.gunferzs.words.utils.db.WordControllerDB;

/**
 * Created by GunFerzs on 04.09.2017.
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal", "unused"})
public class GameFrillsModel {

    GameLogic gameLogic;
    int success, failure;

    final static long TIME_DELAY = 500;

    @Inject
    WordControllerDB wordControllerDB;

    public GameFrillsModel() {
        MyApplication.getAppComponent().inject(this);
        gameLogic = new GameLogic(wordControllerDB.getWords());
    }

    public Observable<String> getRandomString(){
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return gameLogic.getRandomWord();
            }
        }).delay(TIME_DELAY, TimeUnit.MILLISECONDS).
                subscribeOn(Schedulers.computation()).
                observeOn(AndroidSchedulers.mainThread());
    }

    public int getSuccess() {
        return success;
    }

    @SuppressWarnings("SameParameterValue")
    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFailure() {
        return failure;
    }

    @SuppressWarnings("SameParameterValue")
    public void setFailure(int failure) {
        this.failure = failure;
    }


    public void addSuccess(){
        success++;
        System.out.println("plus");
    }

    public void addFailure(){
        failure++;
    }
}
