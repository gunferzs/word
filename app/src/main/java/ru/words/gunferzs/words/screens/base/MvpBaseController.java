package ru.words.gunferzs.words.screens.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by GunFerzs on 28.08.2017.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class MvpBaseController<V extends MvpView, P extends MvpPresenter<V>> extends MvpController<V , P> {

    protected static String nameOfGame;

    Unbinder unbinder;


    public MvpBaseController() {

    }

    public MvpBaseController(Bundle args) {
        super(args);
    }

    protected abstract View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container);

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflateView(inflater, container);
        unbinder = ButterKnife.bind(this,view);
        viewBound(view);
        return view;
    }

    protected abstract void viewBound(@SuppressWarnings("UnusedParameters") View view);

    public static String getNameOfGame(){
        return nameOfGame;
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
        unbinder.unbind();
        unbinder = null;
    }
}
