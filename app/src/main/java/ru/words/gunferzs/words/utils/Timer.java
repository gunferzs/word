package ru.words.gunferzs.words.utils;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by GunFerzs on 02.09.2017.
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal", "unused"})
public class Timer {

    public final static String SEPARATOR = ":";

    Disposable disposable;

    long take = 60;
    Time listener;

    public Timer(long take, Time listener) {
        this.take = take;
        this.listener = listener;
        startTimer();
    }

    public void startTimer() {
        disposable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(take + 1)
                .subscribeOn(Schedulers.single())
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return take - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onNext(Long value) {
                        if(value>0){
                            listener.onChangeTime(generateTimeString(value));
                            System.out.println(generateTimeString(value));
                        }else{
                            destroyDisposable();
                            listener.onFinish();
                            System.out.println("finish");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface Time {
        void onChangeTime(String time);
        void onFinish();
    }

    void setMinuteAndSeconds(long minute, long seconds) {
        take = minute * 60 + seconds;
    }

    String generateTimeString(long time) {
        long minute = (int) time / 60;
        long seconds = time - minute * 60;
        return addZero(String.valueOf(minute))+SEPARATOR+addZero(String.valueOf(seconds));
    }

    String addZero(String part) {
        return part.length() == 1 ? "0" + part : part;
    }

    public void destroyDisposable(){
        if(disposable!=null&&!disposable.isDisposed()){
            disposable.dispose();
        }
    }

}
