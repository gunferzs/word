package ru.words.gunferzs.words.screens.game_frills;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.reactivex.observers.DisposableObserver;
import ru.words.gunferzs.words.utils.Timer;

/**
 * Created by GunFerzs on 28.08.2017.
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal"})
public class GameFrillsPresenter extends MvpBasePresenter<GameFrillsView> {

    GameFrillsModel model;
    Timer timer;

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        timer.destroyDisposable();
    }

    public GameFrillsPresenter() {
        model = new GameFrillsModel();
    }

    public void startGame(long time) {
        System.out.println("start");

        clearStatistics();
        getView().startGame();
        getNewWord();
        timer = new Timer(time, new Timer.Time() {
            @Override
            public void onChangeTime(String time) {
                System.out.println(time);
                getView().updateTimerText(time);
            }

            @Override
            public void onFinish() {
                getView().finishGame(model.getSuccess(), model.getFailure());
            }
        });
    }

    public void addSuccess(){
        model.addSuccess();
        getNewWord();
    }

    public void getNewWord(){
        model.getRandomString().subscribeWith(new DisposableObserver<String>() {
            @Override
            public void onNext(String value) {
                System.out.println(value);
                getView().updateWord(value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });
    }

    public void clearStatistics(){
        model.setSuccess(0);
        model.setFailure(0);
    }

    public void addFailure(){
        model.addFailure();
        getNewWord();
    }

}
